package com.example.instagramclone.view.profileView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.view.activity.SetNameActivity;
import com.example.instagramclone.viewmodel.authViewModel.AuthViewModel;
import com.example.instagramclone.viewmodel.profileViewModel.ProfileViewModel;


public class SetNameFragment extends Fragment {

    private ImageView editUsernameBack;
    private ProgressBar progressBar;
    private EditText editText;
    private Button name,finalNameButton;
    private TextView textView,finalNameText;
    private ProfileViewModel profileViewModel;
    private AuthViewModel authViewModel;


    public SetNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initialViewModel();
        setInfo();
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_set_name, container, false);

        progressBar= view.findViewById(R.id.editNameProgressId);
        progressBar.setVisibility(View.GONE);
        editText= view.findViewById(R.id.editNameEditTextId);
        editUsernameBack= view.findViewById(R.id.editUsernameBackId);
        name= view.findViewById(R.id.editNameButtonId);
        textView= view.findViewById(R.id.user);
        textView.setVisibility(View.GONE);
        finalNameButton= view.findViewById(R.id.editFinalNameSubmitButtonId);
        finalNameButton.setVisibility(View.GONE);
        finalNameText= view.findViewById(R.id.editFinalUserNameId);
        finalNameText.setVisibility(View.GONE);

        editUsernameBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String name= editText.getText().toString();
                name= name.replace(" ","");
                String newName= name.toLowerCase().trim();
                if(!TextUtils.isEmpty(newName)){
                    authViewModel.userNameExist(newName);
                    authViewModel.checkName.observe(getActivity(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s.equals("Exist")){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "This name is already Exist", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                progressBar.setVisibility(View.GONE);
                                editText.setText("");
                                finalNameText.setVisibility(View.VISIBLE);
                                textView.setVisibility(View.VISIBLE);
                                finalNameButton.setVisibility(View.VISIBLE);
                                finalNameText.setText(newName);

                            }
                        }
                    });

                }
                else {
                    Toast.makeText(getActivity(), "Enter Username", Toast.LENGTH_SHORT).show();
                }
            }
        });

        finalNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String name= finalNameText.getText().toString().toLowerCase().trim();
                profileViewModel.UpdateUserName(name);
                profileViewModel.updateName.observe(getActivity(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if(s.equals("true")){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_SHORT).show();
                            getActivity().onBackPressed();
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        return view;
    }

    private void setInfo() {
        profileViewModel.getUserName();
        profileViewModel.userName.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editText.setText(s);
            }
        });
    }

    private void initialViewModel() {
        profileViewModel= new ViewModelProvider(getActivity(),ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(ProfileViewModel.class);
        authViewModel= new ViewModelProvider(getActivity(),ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
    }
}