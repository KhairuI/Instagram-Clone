package com.example.instagramclone.utils;

import android.os.Environment;

public class FilePath {

    public String rootDirectory= Environment.getExternalStorageDirectory().getPath();
    public String pictures= rootDirectory+"/Pictures";
    public String camera = rootDirectory+"/DCIM/Camera";
}
