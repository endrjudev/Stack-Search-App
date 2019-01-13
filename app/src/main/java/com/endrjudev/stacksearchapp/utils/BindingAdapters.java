package com.endrjudev.stacksearchapp.utils;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.endrjudev.stacksearchapp.R;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    private BindingAdapters() {
    }

    @BindingAdapter("isVisible")
    public static void isVisible(View view, final boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter("imageFromUrl")
    public static void loadImage(ImageView view, final String url) {
        GlideApp.with(view)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .placeholder(R.drawable.ic_user_account)
                .error(R.drawable.ic_user_account)
                .into(view);
    }
}
