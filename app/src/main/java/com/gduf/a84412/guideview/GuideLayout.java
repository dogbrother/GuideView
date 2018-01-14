package com.gduf.a84412.guideview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by blackdog on 2018/1/2.
 */
public class GuideLayout extends RelativeLayout {

    private static final int DEFAULT_BACKGROUND_COLOR = 0xb2000000;

    private int mBgColor;
    private Paint mHightLightPaint;
    private GuidePage mGuidePage;
    private Canvas mCanvas;
    private Bitmap mBitmap;

    public interface OnRemoveListener{
        void onRemove();
    }

    private OnRemoveListener mRemoveListener;

    public void setOnRemoveListener(OnRemoveListener listener){
        this.mRemoveListener = listener;
    }

    public GuideLayout(Context context) {
        this(context, null);
    }

    public GuideLayout(Activity activity){
        this(activity,null);
    }

    public GuideLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //HightLightPaint
        mHightLightPaint = new Paint();
        mHightLightPaint.setAntiAlias(true);
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        mHightLightPaint.setXfermode(xfermode);
        mBgColor = DEFAULT_BACKGROUND_COLOR;
        mBitmap = Bitmap.createBitmap(ScreenUtils.getScreenWidth(getContext()),ScreenUtils.getScreenHeight(getContext()), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mBgColor);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setClickable(true);
        //使ViewGroup可以绘制
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("blackdog", "onDraw: " );
        canvas.drawBitmap(mBitmap,0,0,null);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        drawHight();
    }

    public void setGuidePage(GuidePage guidePage){
        this.mGuidePage =  guidePage;
        addViews();
    }

    private void addViews(){
        final List<GuideView> guideViews = mGuidePage.getGuideViews();
        for (GuideView guideView : guideViews) {
            addView(guideView.getView());
            RectF rectF = guideView.getRectF();
            int offset = mGuidePage.getOffset();
            rectF.top = rectF.top + offset;
            rectF.bottom = rectF.bottom + offset;
            LayoutParams params = (LayoutParams) guideView.getView().getLayoutParams();
            params.leftMargin = (int) rectF.left;
            params.topMargin = (int) rectF.top;
        }
    }

    private void drawHight() {
        if (mGuidePage != null) {
            int offset = mGuidePage.getOffset();
            final List<HighLight> highLights = mGuidePage.getHighLights();
            for (HighLight highLight : highLights) {
                RectF rectF = highLight.getRectF();
                rectF.top = rectF.top + offset;
                rectF.bottom = rectF.bottom + offset;
                switch (highLight.getType()) {
                    case CIRCLE:
                        mCanvas.drawCircle(rectF.centerX(), rectF.centerY(), highLight.getRadius(), mHightLightPaint);
                        break;
                    case OVAL:
                        mCanvas.drawOval(rectF, mHightLightPaint);
                        break;
                    case ROUND_RECTANGLE:
                        mCanvas.drawRoundRect(rectF, highLight.getRound(), highLight.getRound(), mHightLightPaint);
                        break;
                    case RECTANGLE:
                    default:
                        mCanvas.drawRect(rectF, mHightLightPaint);
                        break;
                }
            }
            invalidate();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN
                && mGuidePage.isOutsideCancelable()
                && checkOutSide(event)){
            ((FrameLayout)getParent()).removeView(this);
        }
        return super.onTouchEvent(event);
    }

    private boolean checkOutSide(MotionEvent event){
        int offset = mGuidePage.getOffset();

        final List<HighLight> highLights = mGuidePage.getHighLights();
        for (HighLight highLight : highLights) {
            if(highLight.getRectF().contains(event.getX() - offset,event.getY() - offset)){
                return false;
            }
        }
        final List<GuideView> guideViews = mGuidePage.getGuideViews();
        for (GuideView guideView : guideViews) {
            if(guideView.getRectF().contains(event.getX() - offset,event.getY() - offset)){
                return false;
            }
        }
        return true;
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mRemoveListener != null){
            mRemoveListener.onRemove();
        }
    }
}
