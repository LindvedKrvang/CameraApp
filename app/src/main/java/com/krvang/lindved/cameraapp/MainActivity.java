package com.krvang.lindved.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Test";
    private final String DIRECTORY = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES) + "/CameraApp";
    private final String PRE_FIX = "IMG_";
    private final String POST_FIX =".jpg";

    private final int REQUEST_IMAGE_CAPTURE = 1;
    
    private ImageView mImageView;
    private File mFile;
    private int mDesiredWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.imgView);

        findViewById(R.id.btnTakePicture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera();
            }
        });

        mDesiredWidth = mImageView.getWidth();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            if(mFile.exists()){
                Bitmap image = BitmapFactory.decodeFile(mFile.getAbsolutePath());
                image = scaleBitmap(image);
                mImageView.setImageBitmap(image);
            }else
                Log.d(TAG, "onActivityResult: mFile doesn't exits...");
        }
    }

    private Bitmap scaleBitmap(Bitmap bitmap){
        mDesiredWidth = mDesiredWidth == 0 ? mImageView.getWidth() : mDesiredWidth;
        int origWidth = bitmap.getWidth();
        int origHeight = bitmap.getHeight();
        if(origWidth > mDesiredWidth){
            int desHeight = origHeight/(origWidth / mDesiredWidth);
            bitmap = Bitmap.createScaledBitmap(bitmap, mDesiredWidth, desHeight, true);
        }
        return bitmap;
    }

    private void startCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mFile = createFileSimple();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    private File createFileSimple(){
        File folder = new File(DIRECTORY);
        if(!folder.exists()){
            folder.mkdir();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = PRE_FIX + timeStamp + POST_FIX;
        return new File(folder, fileName);
    }
}
