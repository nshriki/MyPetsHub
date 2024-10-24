package com.example.mypetshub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private List<Pet> petList;
    private Context context;

    public PetAdapter(List<Pet> petList, Context context) {
        this.petList = petList;
        this.context = context;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pet_profile, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.petNameTextView.setText(pet.getName());

        Picasso.get()
                .load(pet.getImageUrl())
                .placeholder(R.drawable.pet_profile_storage) // Placeholder image
                .error(R.drawable.avatar) // Error image
                .into(holder.petImageView);

        holder.itemView.setOnClickListener(v -> {
            // Handle item click event
            Toast.makeText(context, "Clicked on: " + pet.getName(), Toast.LENGTH_SHORT).show();
            // You can start a new activity or perform any action here

        });
    }


    @Override
    public int getItemCount() {
        return petList.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        ImageView petImageView;
        TextView petNameTextView;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            petImageView = itemView.findViewById(R.id.imgPet);
            petNameTextView = itemView.findViewById(R.id.txtPetName);
        }
    }
}
