package com.example.mypetshub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapterPetDashboard extends RecyclerView.Adapter<RecyclerViewAdapterPetDashboard.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterPetDashboard(Context context, ArrayList<String> names, ArrayList<String> imageUrls) {
        this.mNames = names;
        this.mImageUrls = imageUrls;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        String imageUrl = mImageUrls.get(position);

        if (imageUrl == null || imageUrl.isEmpty()) {
            Glide.with(mContext)
                    .load(R.drawable.avatar)
                    .into(holder.petImageView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .load(imageUrl)
                    .into(holder.petImageView);
        }

        holder.petTextName.setText(mNames.get(position));

        holder.petImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView petImageView;
        TextView petTextName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            petImageView = itemView.findViewById(R.id.imageView_PetDashboard);
            petTextName = itemView.findViewById(R.id.petName_petDashboard);
        }
    }
}
