package com.gduf.a84412.guideview;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by 84412 on 2018/1/7.
 */

public class Guide implements GuideLayout.OnResetListener{

    private Activity mActivity;
    private GuideLayout mGuideLayout;
    private Queue<GuidePage> mGuidePages;
    private int mIndex = 0;

    private Guide(Activity activity){
        mGuideLayout = new GuideLayout(activity);
        mActivity = activity;
        mGuidePages = new ArrayDeque<>();
    }

    public void show(){
        mGuideLayout.setGuidePage(mGuidePages.remove());
        View root = mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        root.post(new Runnable() {
            @Override
            public void run() {
                ((FrameLayout)mActivity.getWindow().getDecorView()).addView(mGuideLayout,FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
            }
        });
    }

    @Override
    public void onReset() {
        if(!mGuidePages.isEmpty()){
            mGuideLayout.reset();
            mGuideLayout.setGuidePage(mGuidePages.remove());
        }else{
            ((FrameLayout)mActivity.getWindow().getDecorView()).removeView(mGuideLayout);
        }

    }

    public static final class Builder{

        private GuidePage mCurrentPage;
        private Guide mGuide;


        public Builder(Activity activity){
            mGuide = new Guide(activity);
            mGuide.mActivity = activity;
            mCurrentPage = new GuidePage();
        }

        public Builder addGuideView(GuideView guideView){
            this.mCurrentPage.addGuideView(guideView);
            return this;
        }

        public Builder addGuideViews(List<GuideView> guideViews){
            this.mCurrentPage.addGuideViews(guideViews);
            return this;
        }

        public Builder setOffset(int offset){
            this.mCurrentPage.setOffset(offset);
            return this;
        }

        public Builder addHightLight(HighLight highLight){
            this.mCurrentPage.addHighLight(highLight);
            return this;
        }

        public Builder addHightLights(List<HighLight> highLights){
            this.mCurrentPage.addHighLights(highLights);
            return this;
        }

        public Builder setFullScreen(boolean isFullScreen){
            this.mCurrentPage.setFullScreen(isFullScreen);
            return this;
        }

        public Builder setOutsideCancelable(boolean cancelable){
            this.mCurrentPage.setOutsideCancelable(cancelable);
            return this;
        }

        public Builder asPage(){
            addGuidePage();
            return this;
        }

        private void addGuidePage(){
            mGuide.mGuidePages.add(mCurrentPage);
            mCurrentPage = new GuidePage();
        }

        public Guide build(){
            addGuidePage();
            mGuide.mGuideLayout.setOnResetListener(mGuide);
            return mGuide;
        }
    }
}
