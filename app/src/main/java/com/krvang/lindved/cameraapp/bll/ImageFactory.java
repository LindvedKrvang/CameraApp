package com.krvang.lindved.cameraapp.bll;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;

import com.krvang.lindved.cameraapp.be.Image;

import java.io.File;

/**
 * Created by Lindved on 07-03-2018.
 */

public class ImageFactory implements IImageFactory{

    int mDesiredWidth = 600;

    @Override
    public Image createImage(File file) {
        Bitmap image = BitmapFactory.decodeFile(file.getAbsolutePath());
        image = scaleBitmap(image);
        String name = file.getName();
//        String date = getTimestamp(name);

        return new Image(image, name, getSize(), "TimeStamp: ");
    }

    private String getSize(){
        return "Size: ";
    }

    private String getTimestamp(String name){
        String[] first = name.split(".");
        String[] second = first[0].split("_");
        char[] dateArray = second[1].toCharArray();
        String year = "", month = "", day = "";
        for (int i = 0; i < dateArray.length; i++){
            if(i < 4){
                year += dateArray[i];
                break;
            }
            if(i < 6){
                month += dateArray[i];
                break;
            }
            day += dateArray[i];
        }
        String date = day + "/" + month + "/" + year;
        return date;
    }

    private Bitmap scaleBitmap(Bitmap bitmap){
//        mDesiredWidth = mDesiredWidth == 0 ? mImageView.getWidth() : mDesiredWidth;
        int origWidth = bitmap.getWidth();
        int origHeight = bitmap.getHeight();
        if(origWidth > mDesiredWidth){
            int desHeight = origHeight/(origWidth / mDesiredWidth);
            bitmap = Bitmap.createScaledBitmap(bitmap, mDesiredWidth, desHeight, true);
        }
        return bitmap;
    }
}
