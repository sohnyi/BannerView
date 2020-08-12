package com.nasduck.lib.loader;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.io.Serializable;

public interface ImageLoaderInterface<T extends ImageView> extends Serializable {

    void displayImage(Context context, Object path, T imageView);
    T createImageView(Context context);
}
