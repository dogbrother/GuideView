package com.gduf.a84412.guideview;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84412 on 2018/1/7.
 */

public class Guide implements GuideLayout.OnRemoveListener{

    private Activity mActivity;
    private List<GuideLayout> mGuideLayouts;
    private int mIndex = 0;

    private Guide(){
        mGuideLayouts = new ArrayList<>();
    }

    public void show(){
        View root = mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        root.post(new Runnable() {
            @Override
            public void run() {
                ((FrameLayout)mActivity.getWindow().getDecorView()).addView(mGuideLayouts.get(mIndex++),FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
            }
        });
    }

    @Override
    public void onRemove() {
        if(mIndex <= mGuideLayouts.size() - 1){
            ((FrameLayout)mActivity.getWindow().getDecorView()).addView(mGuideLayouts.get(mIndex++),FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        }
    }

    public static final class Builder{

        private GuidePage mCurrentPage;
        private Guide mGuide;

        public Builder(Activity activity){
            mGuide = new Guide();
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
            addGuideLayout();
            return this;
        }

        private void addGuideLayout(){
            GuideLayout layout = new GuideLayout(mGuide.mActivity);
            layout.setGuidePage(mCurrentPage);
            layout.setOnRemoveListener(mGuide);
            mCurrentPage = new GuidePage();
            mGuide.mGuideLayouts.add(layout);
        }

        public Guide build(){
            addGuideLayout();
            return mGuide;
        }
    }
}
