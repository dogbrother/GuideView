package com.gduf.a84412.guideview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        Button button = new Button(this);
        button.setWidth(90);
        button.setHeight(30);
        button.setText("test");
        button.setBackgroundColor(Color.BLACK);
        GuideView guideView = new GuideView(button);
        guideView.setRelativeView(mTextView);
        guideView.setRelative(GuideView.RELATIVE_TOP | GuideView.RELATIVE_RIGHT);
        Guide guide = new Guide.Builder(this)
                .addHightLight(highLight)
                .setOutsideCancelable(true)
                .addGuideView(guideView)
                .asPage()
                .addHightLight(highLight2)
                .setOutsideCancelable(true)
                .build();
        guide.show();
    }
}
