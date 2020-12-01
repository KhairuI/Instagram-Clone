package com.example.instagramclone.view.insertView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.adapter.BottomSheetAdapter;
import com.example.instagramclone.adapter.GridImageAdapter;
import com.example.instagramclone.utils.FilePath;
import com.example.instagramclone.utils.FileSearch;
import com.example.instagramclone.utils.UniversalImageLoader;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;


public class GalleryFragment extends Fragment implements BottomSheetAdapter.ClickInterface {

    private ImageView galleryImage,closeImage,rightArrow;
    private ProgressBar galleryProgress;
    private GridView gridView;
    private TextView folderText;
    private BottomSheetDialog bottomSheetDialog;
    private ArrayList<String> directoryList;
    ArrayList<String> directoryName;
    private static final int gridColumn=3;
    private String append="file:/";
    private String selectedImage;
    private BottomSheetAdapter adapter;
    private RecyclerView recyclerView;
    public GalleryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=  inflater.inflate(R.layout.fragment_gallery, container, false);

        galleryImage= rootView.findViewById(R.id.galleryImageId);
        galleryProgress= rootView.findViewById(R.id.galleryProgressId);
        galleryProgress.setVisibility(View.GONE);
        gridView= rootView.findViewById(R.id.galleryGridViewId);
        closeImage= rootView.findViewById(R.id.galleryCloseId);
        rightArrow= rootView.findViewById(R.id.galleryRightId);
        folderText= rootView.findViewById(R.id.folderTextId);
        adapter= new BottomSheetAdapter(this);
        directoryList= new ArrayList<>();
         directoryName = new ArrayList<>();

        initImageLoader();
        initPaths();
        setUpGridView(directoryList.get(0));
        folderText.setText(directoryName.get(0));

        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();
            }
        });


        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getActivity(),PostActivity.class);
                intent.putExtra("select",selectedImage);
                startActivity(intent);
            }
        });

        folderText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog = new BottomSheetDialog(getActivity(),R.style.BottomSheetTheme);
                View v =  LayoutInflater.from(getActivity()).inflate(R.layout.gallery_bottom_sheet,(ViewGroup) rootView.findViewById(R.id.bottomSheet));
                recyclerView= v.findViewById(R.id.sheetRecycleId);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter.getDirectoryList(directoryName);
                recyclerView.setAdapter(adapter);
                bottomSheetDialog.setContentView(v);
                bottomSheetDialog.show();
            }
        });


        return rootView;
    }

    private void initImageLoader() {
        UniversalImageLoader universalImageLoader= new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfiguration());
    }

    private void initPaths(){

        FilePath filePath= new FilePath();
        // check for the other folder inside " /storage/emulated/0/pictures "

        if(FileSearch.getDirectoryPath(filePath.pictures) != null){
            directoryList= FileSearch.getDirectoryPath(filePath.pictures);
        }

        for(int i=0; i<directoryList.size();i++){
            int index= directoryList.get(i).lastIndexOf("/")+1;
            String name= directoryList.get(i).substring(index);
            directoryName.add(name);
        }
        directoryList.add(filePath.camera);

      /*  ArrayAdapter<String> adapter= new ArrayAdapter<String >(getActivity(), android.R.layout.simple_spinner_item,directoryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // setup select grid image....
                setUpGridView(directoryList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

    }

    // set grid view here...

    private void setUpGridView(String selectDirectory){

        final ArrayList<String> imageUrls= FileSearch.getFilePath(selectDirectory);
        // set the grid column width..
        int gridWidth= getResources().getDisplayMetrics().widthPixels;
        int imageWidth= gridWidth/gridColumn;
        gridView.setColumnWidth(imageWidth);

        // set Grid Adapter....
        GridImageAdapter adapter= new GridImageAdapter(getActivity(),R.layout.profile_single_grid_item,append,imageUrls);
        gridView.setAdapter(adapter);

        try {

            //set the first image of the directory by default....
            setImage(imageUrls.get(0),galleryImage,append);
            selectedImage= imageUrls.get(0);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    setImage(imageUrls.get(position),galleryImage,append);
                    selectedImage= imageUrls.get(position);
                }
            });

        }catch (NullPointerException e){
            Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void setImage(String imageUrl,ImageView imageView,String append){

        ImageLoader imageLoader= ImageLoader.getInstance();
        imageLoader.displayImage(append + imageUrl, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                galleryProgress.setVisibility(View.VISIBLE);

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                galleryProgress.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                galleryProgress.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                galleryProgress.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {

        setUpGridView(directoryList.get(position));
        folderText.setText(directoryName.get(position));
        bottomSheetDialog.dismiss();
    }
}