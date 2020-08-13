package com.nasduck.lib.transform;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

/**
 * 效果：
 * 切换时，沿中央轴线翻转
 */

public class WindmillTransformer implements ViewPager.PageTransformer {
    private static final float BASE_ROTATION = 180.0f;

    @Override
    public void transformPage(@NonNull View view, float position) {
       if (position <= 0) {
            view.setTranslationX(-view.getWidth() * position);
            float rotation = (BASE_ROTATION * position);
            view.setRotationY(rotation);

            if (position > -0.5) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.INVISIBLE);
            }
        } else if (position <= 1) {
            view.setTranslationX(-view.getWidth() * position);
            float rotation = (BASE_ROTATION * position);
            view.setRotationY(rotation);

            if (position < 0.5) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }
}
