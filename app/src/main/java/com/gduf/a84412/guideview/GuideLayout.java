package com.gduf.a84412.guideview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by blackdog on 2018/1/2.
 */
public class GuideLayout extends FrameLayout {

    private static final int DEFAULT_BACKGROUND_COLOR = 0xb2000000;

    private int mBgColor;
    private Paint mHightLightPaint;
    private GuidePage mGuidePage;

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
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setClickable(true);
        //使ViewGroup可以绘制
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(mBgColor);
        if (mGuidePage != null) {
            int offset = mGuidePage.getOffset();
            final List<HighLight> highLights = mGuidePage.getHighLights();
            for (HighLight highLight : highLights) {
                RectF rectF = highLight.getRectF();
                rectF.top = rectF.top + offset;
                rectF.bottom = rectF.bottom + offset;
                switch (highLight.getType()) {
                    case CIRCLE:
                        canvas.drawCircle(rectF.centerX(), rectF.centerY(), highLight.getRadius(), mHightLightPaint);
                        break;
                    case OVAL:
                        canvas.drawOval(rectF, mHightLightPaint);
                        break;
                    case ROUND_RECTANGLE:
                        canvas.drawRoundRect(rectF, highLight.getRound(), highLight.getRound(), mHightLightPaint);
                        break;
                    case RECTANGLE:
                    default:
                        canvas.drawRect(rectF, mHightLightPaint);
                        break;
                }
            }
            final List<GuideView> guideViews = mGuidePage.getGuideViews();
            for (GuideView guideView : guideViews) {
                RectF rectF = guideView.getRectF();
                rectF.top = rectF.top + offset;
                rectF.bottom = rectF.bottom + offset;
                canvas.drawBitmap(guideView.getBitmap(), rectF.left, rectF.top, guideView.getPaint());
            }
        }
    }

    public void setGuidePage(GuidePage guidePage){
        this.mGuidePage =  guidePage;
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
