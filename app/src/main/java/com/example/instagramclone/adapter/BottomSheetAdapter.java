package com.example.instagramclone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.R;

import java.util.List;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.MyViewHolder> {

    private List<String> directoryList;
    private ClickInterface clickInterface;

    public BottomSheetAdapter(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    public void getDirectoryList(List<String> directoryList){
        this.directoryList= directoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.bottom_sheet_single_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText(directoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return directoryList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.bottomSheetSingleTextId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickInterface.onItemClick(getAdapterPosition());
        }
    }

    public interface ClickInterface {
        // for on Click....
        void onItemClick(int position);

    }
}
