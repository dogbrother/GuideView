package com.blackdog.guide;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84412 on 2018/1/2.
 * 每个引导都对应着一个页面
 */
public class GuidePage {

    private List<HighLight> mHighLights;
    private List<GuideView> mGuideViews;
    private boolean mOutsideCancelable;
    private int mBgColor;
    private boolean mIsFullScreen;
    //由于有些机子的View的getLocationOnScreen会出现异常，同时也没有包含statusBar高度
    private int mOffset;

    public GuidePage(){
        mHighLights = new ArrayList<>();
        mGuideViews = new ArrayList<>();
        mOutsideCancelable = false;
        mBgColor = -1;
        mOffset = 0;
        mIsFullScreen = true;
    }

    public List<HighLight> getHighLights() {
        return mHighLights;
    }

    public void addHighLights(List<HighLight> highLights) {
            mHighLights.addAll(highLights);
    }

    public void addGuideView(GuideView guideView){
        mGuideViews.add(guideView);
    }

    public void addGuideViews(List<GuideView> guideViews){
        mGuideViews.addAll(guideViews);
    }

    public void addHighLight(HighLight highLight){
            mHighLights.add(highLight);
    }

    public boolean isOutsideCancelable() {
        return mOutsideCancelable;
    }

    public void setOutsideCancelable(boolean outsideCancelable) {
        mOutsideCancelable = outsideCancelable;
    }


    public int getBgColor() {
        return mBgColor;
    }

    public void setBgColor(int bgColor) {
        mBgColor = bgColor;
    }

    public boolean isFullScreen() {
        return mIsFullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        mIsFullScreen = fullScreen;
    }

    public List<GuideView> getGuideViews() {
        return mGuideViews;
    }

    public void setGuideViews(List<GuideView> guideViews) {
        mGuideViews = guideViews;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }
}
