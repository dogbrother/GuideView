package com.gduf.a84412.guideview;

import android.graphics.RectF;
import android.view.View;

/**
 * Created by 84412 on 2018/1/2.
 */

public class HighLight {

    private int mPaddingTop;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;

    private View mHole;
    private Type mType;
    private int round;

    public HighLight(View hole, Type type) {
        this.mHole = hole;
        this.mType = type;
        mPaddingTop = 0;
        mPaddingBottom = 0;
        mPaddingLeft = 0;
        mPaddingRight = 0;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRadius() {
        return mHole != null ? Math.max(mHole.getWidth() / 2, mHole.getHeight() / 2) : 0;
    }

    public RectF getRectF() {
        RectF rectF = new RectF();
        if (mHole != null) {
            int[] location = new int[2];
            mHole.getLocationOnScreen(location);
            rectF.left = location[0] - mPaddingLeft;
            rectF.top = location[1] - mPaddingTop;
            rectF.right = location[0] + mHole.getWidth() + mPaddingRight;
            rectF.bottom = location[1] + mHole.getHeight() + mPaddingBottom;
        }
        return rectF;
    }

    public Type getType() {
        return mType;
    }

    public enum Type {
        CIRCLE,//圆形
        RECTANGLE, //矩形
        OVAL,//椭圆
        ROUND_RECTANGLE;//圆角矩形
    }

    public void setPaddingTop(int paddingTop) {
        mPaddingTop = paddingTop;
    }

    public void setPaddingBottom(int paddingBottom) {
        mPaddingBottom = paddingBottom;
    }

    public void setPaddingLeft(int paddingLeft) {
        mPaddingLeft = paddingLeft;
    }

    public void setPaddingRight(int paddingRight) {
        mPaddingRight = paddingRight;
    }

    public void setPadding(int paddingAll){
        this.mPaddingTop = this.mPaddingBottom = this.mPaddingLeft = this.mPaddingRight = paddingAll;
    }

    public void setPadding(int paddingLeft,int paddingTop,int paddingRight,int paddingBottom){
        this.mPaddingLeft = paddingLeft;
        this.mPaddingTop = paddingTop;
        this.mPaddingRight = paddingRight;
        this.mPaddingBottom = paddingBottom;
    }
}
