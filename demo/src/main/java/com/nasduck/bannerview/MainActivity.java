package com.nasduck.bannerview;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nasduck.bannerview.loader.GlideImageLoader;
import com.nasduck.bannerview.loader.ResourceImageLoader;
import com.nasduck.lib.BannerScaleType;
import com.nasduck.lib.BannerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> mUrlStrings;
    private List<Drawable> mDrawables;

    private Button mStopBtn;
    private BannerView mBannerViewGlide;
    private BannerView mBannerViewResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mStopBtn = findViewById(R.id.btn_stop);
        mStopBtn.setOnClickListener(this);

        mBannerViewGlide = findViewById(R.id.banner_view_glide);
        mBannerViewResource = findViewById(R.id.banner_view_resource);
        getLifecycle().addObserver(mBannerViewGlide);
        getLifecycle().addObserver(mBannerViewResource);

        mBannerViewGlide.setImageLoader(new GlideImageLoader())
                .hasIndicator(true)
                .setImageUrls(mUrlStrings)
                .setScaleType(BannerScaleType.CENTER_CROP)
                .setLoop(false)
                .init();

        mBannerViewResource.setImageLoader(new ResourceImageLoader())
                .setImageUrls(mDrawables)
                .setAutoPlay(true)
                .hasIndicator(true)
                .setScaleType(BannerScaleType.CENTER_CROP)
                .setIntervalTime(5000)
                .init();

        mBannerViewResource.setClickListener(position -> Toast.makeText(MainActivity.this,
                " clicked at " + position, Toast.LENGTH_SHORT).show());
        mBannerViewResource.setScrolledListener( position -> Toast.makeText(MainActivity.this, "current position: " + position, Toast.LENGTH_SHORT).show());

    }

    private void initData() {

        mUrlStrings = new ArrayList<>();
        mUrlStrings.add("https://images.freeimages.com/images/large-previews/cd8/autumn-1362126.jpg");
        mUrlStrings.add("https://images.freeimages.com/images/large-previews/ea3/autumn-colors-1172540.jpg");
        mUrlStrings.add("https://images.freeimages.com/images/large-previews/1d9/winter-5-1383783.jpg");
        mUrlStrings.add("https://images.freeimages.com/images/large-previews/a06/spring-buds-1173653.jpg");
        mUrlStrings.add("https://images.freeimages.com/images/large-previews/120/wheat-in-summer-1566168.jpg");

        mDrawables = new ArrayList<>();
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.d1));
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.d2));
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.d3));
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.d4));
        mDrawables.add(ContextCompat.getDrawable(this, R.drawable.d5));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_stop) {
            if (mBannerViewGlide.isPlaying()) {
                mBannerViewGlide.stop();
                mBannerViewResource.stop();
                mStopBtn.setText(getResources().getString(R.string.start));
            } else {
                mBannerViewGlide.play();
                mBannerViewResource.play();
                mStopBtn.setText(getResources().getString(R.string.stop));
            }
        }
    }
}
