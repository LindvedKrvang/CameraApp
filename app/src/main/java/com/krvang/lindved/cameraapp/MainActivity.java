package com.krvang.lindved.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;

import com.krvang.lindved.cameraapp.be.Image;
import com.krvang.lindved.cameraapp.bll.IImageFactory;
import com.krvang.lindved.cameraapp.bll.ImageFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Test";
    private final String DIRECTORY = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES) + "/CameraApp";
    private final String PRE_FIX = "IMG_";
    private final String POST_FIX =".jpg";

    private final int REQUEST_IMAGE_CAPTURE = 1;

    private RecyclerView mImagesRecyclerView;

    private File mFile;
    private List<Image> mImages;
    private IImageFactory mImageFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageFactory = new ImageFactory();

        loadFiles();

        initializeListView();
    }

    private void loadFiles(){
        mImages = new ArrayList<>();

        File dir = new File(DIRECTORY);
        File[] files = dir.listFiles();
        if(files != null){
            for(File file: files){
                mImages.add(mImageFactory.createImage(file));
            }
        }
    }


    private void initializeListView(){
        mImagesRecyclerView = findViewById(R.id.lstImages);
        mImagesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ImageListAdapter adapter = new ImageListAdapter(this, mImages);
        mImagesRecyclerView.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            if(mFile.exists()){
                Image image = mImageFactory.createImage(mFile);
                mImages.add(image);
            }else
                Log.d(TAG, "onActivityResult: mFile doesn't exits...");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.camera_action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_take_picture:{
                startCamera();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
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
