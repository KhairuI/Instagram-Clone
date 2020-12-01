package com.example.instagramclone.utils;

import android.Manifest;

public class Permissions {

    public static String[] PERMISSIONS= {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public static String[] CAMERA_PERMISSIONS= {
            Manifest.permission.CAMERA
    };

    public static String[] READ_STORAGE_PERMISSIONS= {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static String[] WRITE_STORAGE_PERMISSIONS= {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
}
