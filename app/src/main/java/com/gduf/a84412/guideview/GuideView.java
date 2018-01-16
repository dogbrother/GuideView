package com.gduf.a84412.guideview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 84412 on 2018/1/2.
 * 引导页面中放置的View
 */

public class GuideView {

    private static final int RELATVIE_MASK = 0xff;
    public static final int RELATIVE_LEFT = 1;
    public static final int RELATIVE_RIGHT = 1 << 1;
    public static final int RELATIVE_TOP = 1 << 2;
    public static final int RELATIVE_BOTTOM = 1 << 3;

    private int mXInterval;
    private int mYInterval;
    private View mView;
    private View mRelativeView;
    private int mXPosition;
    private int mYPosition;
    private int mRelative;
    private Paint mPaint;


    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public GuideView(View view){
        this.mView = view;
        if(mView.getParent() != null){
            ((ViewGroup)mView.getParent()).removeView(mView);
        }
    }

    public GuideView setXPosition(int x){
        this.mXPosition = x;
        return this;
    }

    public GuideView setYPosition(int y){
        this.mYPosition = y;
        return this;

    }

    public GuideView setRelativeView(View view){
        mRelativeView = view;
        return this;

    }

    public GuideView setRelative(int relative){
        this.mRelative = relative;
        return this;

    }

    public int getXInterval() {
        return mXInterval;
    }

    public GuideView setXInterval(int XInterval) {
        mXInterval = XInterval;
        return this;
    }

    public int getYInterval() {
        return mYInterval;
    }

    public GuideView setYInterval(int YInterval) {
        mYInterval = YInterval;
        return this;
    }

    public View getView() {
        return mView;
    }

    public View getRelativeView() {
        return mRelativeView;
    }

    public int getXPosition() {
        return mXPosition;
    }

    public int getYPosition() {
        return mYPosition;
    }

    public int getRelative() {
        return mRelative;
    }

    public static GuideView with(View view){
        return new GuideView(view);
    }

    private void caculatePosition(){
        if(mRelativeView == null){
            return;
        }
        final int viewWidth = mView.getWidth();
        final int viewHeight = mView.getHeight();
        final int relativeWidth = mRelativeView.getWidth();
        final int relativeHeight = mRelativeView.getHeight();
        final int[] location = new int[2];
        mRelativeView.getLocationOnScreen(location);
        switch (mRelative){
            //Left
            case RELATIVE_LEFT:
                mXPosition = location[0] - viewWidth - mXInterval;
                mYPosition = location[1] - mYInterval;
                break;
            //Right
            case RELATIVE_RIGHT:
                mXPosition = location[0] + relativeWidth + mXInterval;
                mYPosition = location[1] - mYInterval;
                break;
            //Top
            case RELATIVE_TOP:
                mXPosition = location[0] - mXInterval;
                mYPosition = location[1] - viewHeight  - mYInterval;
                break;
            //Bottom
            case RELATIVE_BOTTOM:
                mXPosition = location[0] - mXInterval;
                mYPosition = location[1] + relativeHeight + mYInterval;
                break;
            //Left + Top
            case RELATIVE_LEFT + RELATIVE_TOP:
                mXPosition = location[0] - viewWidth - mXInterval;
                mYPosition = location[1] - viewHeight - mYInterval;
                break;
            //Right + Top
            case RELATIVE_RIGHT + RELATIVE_TOP:
                mXPosition = location[0] + relativeWidth+ mXInterval;
                mYPosition = location[1] - viewHeight - mYInterval;
                break;
            //Left + Bottom
            case RELATIVE_LEFT + RELATIVE_BOTTOM:
                mXPosition = location[0] - viewWidth - mXInterval;
                mYPosition = location[1] + relativeHeight + mYInterval;
                break;
            //Right + Bottom
            case RELATIVE_RIGHT + RELATIVE_BOTTOM:
                mXPosition = location[0] + relativeWidth+ mXInterval;
                mYPosition = location[1] + relativeHeight + mYInterval;
                break;
        }

    }


    public RectF getRectF(){
        caculatePosition();
        RectF rectF = new RectF();
        rectF.top = mYPosition;
        rectF.left = mXPosition;
        rectF.bottom = mYPosition + mView.getHeight();
        rectF.right = mXPosition + mView.getWidth();
        return rectF;
    }
}
