package com.krvang.lindved.cameraapp.be;

import android.graphics.Bitmap;

/**
 * Created by Lindved on 07-03-2018.
 */

public class Image {
    private Bitmap mBitmap;
    private String mName, mSize, mTimestamp;

    public Image(Bitmap bitmap, String name, String size, String timestamp) {
        mBitmap = bitmap;
        mName = name;
        mSize = size;
        mTimestamp = timestamp;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public String getName() {
        return mName;
    }

    public String getSize() {
        return mSize;
    }

    public String getTimestamp() {
        return mTimestamp;
    }
}
