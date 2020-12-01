package com.example.instagramclone.utils;

import java.io.File;
import java.util.ArrayList;

public class FileSearch {

    /*
    * search a directory and return a list of all directories.....
    * */
    public static ArrayList<String> getDirectoryPath(String directory){
        ArrayList<String> list= new ArrayList<>();
        File file= new File(directory);
        File[] listFile= file.listFiles();
        for(int i=0;i <listFile.length; i++){
            if(listFile[i].isDirectory()){
                list.add(listFile[i].getAbsolutePath());
            }
        }
        return list;
    }

    /*
     * search a directory and return a list of all Files.....
     * */
    public static ArrayList<String> getFilePath(String directory){
        ArrayList<String> list= new ArrayList<>();
        File file= new File(directory);
        File[] listFile= file.listFiles();
        for(int i=0;i <listFile.length; i++){
            if(listFile[i].isFile()){
                list.add(listFile[i].getAbsolutePath());
            }
        }
        return list;
    }

}
