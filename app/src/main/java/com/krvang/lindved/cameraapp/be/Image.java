package com.krvang.lindved.cameraapp.be;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Lindved on 07-03-2018.
 */

public class Image {
    private Bitmap mBitmap;
    private String mName, mSize;
    private Date mLastModified;

    public Image(Bitmap bitmap, String name, String size, Date lastModified) {
        mBitmap = bitmap;
        mName = name;
        mSize = size;
        mLastModified = lastModified;
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


    public Date getLastModified() {
        return mLastModified;
    }
}
