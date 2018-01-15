package com.vk_2.ImageLoader.cache;

import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;

public interface DiskCache {

    File get(String imageUri) throws IOException;

    void save(String imageUri, Bitmap bitmap) throws IOException;
}
