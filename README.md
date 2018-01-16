# GuideView
一款新手引导的View，支持View高亮功能，添加View功能
下面是个简单的例子：
        mTextView = findViewById(R.id.tv);
        HighLight highLight = new HighLight(mTextView, HighLight.Type.ROUND_RECTANGLE);
        highLight.setRound(12);
        highLight.setPadding(12);
        HighLight highLight2 = new HighLight(mTextView, HighLight.Type.ROUND_RECTANGLE);
        highLight2.setRound(20);
        highLight2.setPadding(50);
        View view = LayoutInflater.from(this).inflate(R.layout.item_test, null,false);
        GuideView guideView = new GuideView(view);
        guideView.setRelativeView(mTextView);
        guideView.setYInterval(100);
        GuideView guideView1 = new GuideView(view);
        guideView1.setRelativeView(mTextView);
        guideView1.setYInterval(100);
        guideView.setRelative(GuideView.RELATIVE_BOTTOM | GuideView.RELATIVE_RIGHT);
        guideView1.setRelative(GuideView.RELATIVE_TOP);
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
