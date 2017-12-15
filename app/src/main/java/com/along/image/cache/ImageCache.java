package com.along.image.cache;

import android.graphics.Bitmap;

/**
 * Created by mip on 2017/12/14.\
 *
 * @author Along
 */

public interface ImageCache {
    void put(String url, Bitmap bitmap);
    Bitmap get(String url);
}
