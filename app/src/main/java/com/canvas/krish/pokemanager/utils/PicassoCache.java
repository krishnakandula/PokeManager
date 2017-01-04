package com.canvas.krish.pokemanager.utils;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

/**
 * Created by Krishna Chaitanya Kandula on 1/3/2017.
 * Singleton instance of Picasso
 */

public class PicassoCache {
    private static Picasso sPicasso;

    private PicassoCache(Context context){
        Downloader downloader = new OkHttp3Downloader(context);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(downloader);
        sPicasso = builder.build();
        sPicasso.setSingletonInstance(sPicasso);
    }

    public static Picasso getPicassoInstance(Context context){
        if(sPicasso == null)
            new PicassoCache(context);
        return sPicasso;
    }
}
