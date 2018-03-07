package com.krvang.lindved.cameraapp.bll;

import com.krvang.lindved.cameraapp.be.Image;

import java.io.File;

/**
 * Created by Lindved on 07-03-2018.
 */

public interface IImageFactory {

    Image createImage(File file);
}
