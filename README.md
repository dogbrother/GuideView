#<center> Andorid引导View </center>
### 引入库
>#####1. 从https://github.com/dogbrother/GuideView Download代码.
>![download.PNG](https://upload-images.jianshu.io/upload_images/1861998-471dd747368153c1.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
>#####2. 引入guide
>![refrence.PNG](https://upload-images.jianshu.io/upload_images/1861998-31bbfd05fc120426.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 如何使用?
<code>

         待高亮的View
        mTextView = findViewById(R.id.tv);
        /**
         * 高亮区域1
         */
        HighLight highLight = new HighLight(mTextView, HighLight.Type.ROUND_RECTANGLE);
        highLight.setRound(12);
        highLight.setPadding(12);
        /**
         * 高亮区域2
         */
        HighLight highLight2 = new HighLight(mTextView, HighLight.Type.ROUND_RECTANGLE);
        highLight2.setRound(20);
        highLight2.setPadding(50);
        /**
         * 待加入引导的View
         */
        View view = LayoutInflater.from(this).inflate(R.layout.item_test, null,false);
        /**
         * 创建高亮View
         */
        GuideView guideView = new GuideView(view);
        guideView.setRelativeView(mTextView);
        guideView.setYInterval(100);
        guideView.setOnGuideViewClickListener(new GuideView.OnGuideViewClickListener() {
            @Override
            public void onClick(Guide guide, View view) {
                guide.nextOrRemove();
            }
        });
        /**
         * 创建引导类
         */
        GuideView guideView1 = new GuideView(view);
        //引导类的相对View
        guideView1.setRelativeView(mTextView);
        //引导类相对于相对View的位置
        guideView.setRelative(GuideView.RELATIVE_BOTTOM | GuideView.RELATIVE_RIGHT);
        //引导类相对相对View的Y的高度
        guideView1.setYInterval(100);
        guideView1.setRelative(GuideView.RELATIVE_TOP);
        /**
         * 创建引导
         */
        Guide guide = new Guide.Builder(this)
                .addHightLight(highLight)
                .setOutsideCancelable(true)
                .addGuideView(guideView)
                .asPage()
                .addHightLight(highLight2)
                .addGuideView(guideView1)
                .setOutsideCancelable(true)
                .build();
        guide.show();
</code>
