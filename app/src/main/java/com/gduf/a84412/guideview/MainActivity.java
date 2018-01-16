package com.gduf.a84412.guideview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        guideView.setYInterval(12);
        GuideView guideView1 = new GuideView(view);
        guideView1.setRelativeView(mTextView);
        guideView1.setYInterval(12);
        guideView.setRelative(GuideView.RELATIVE_TOP);
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
    }
}
