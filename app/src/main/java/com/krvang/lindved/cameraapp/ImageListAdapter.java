package com.krvang.lindved.cameraapp;

import android.content.Context;
import android.graphics.Bitmap;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.krvang.lindved.cameraapp.be.Image;

import java.io.File;
import java.util.List;

/**
 * Created by Lindved on 07-03-2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.RecyclerViewHolder> {
    private final String TAG = "Test";

    Context mContext;
    List<Image> mImages;

    public ImageListAdapter(Context context, List<Image> images){
        mContext = context;
        mImages = images;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RecyclerViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Image image = mImages.get(position);
        holder.bind(image);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mFileName, mFileSize, mFileTimestamp;


        public RecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
            super(layoutInflater.inflate(R.layout.image_list_item, parent, false));

            mImageView = itemView.findViewById(R.id.ivImage);
            mFileName = itemView.findViewById(R.id.txtFileName);
            mFileSize = itemView.findViewById(R.id.txtFileSize);
            mFileTimestamp = itemView.findViewById(R.id.txtFileTimestamp);
            Log.d(TAG, "RecyclerViewHolder: " + mImageView.getWidth());

        }

        public void bind(Image image){

            mFileName.setText(image.getName());
            mImageView.setImageBitmap(image.getBitmap());

        }


    }
}
