package com.example.mypetshub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PetAdapter2 extends RecyclerView.Adapter<PetAdapter2.PetViewHolder> {

    private List<Pet2> petList;  // Holds the list of Pet objects
    private Context context;

    // Constructor modified to accept petList as the first parameter
    public PetAdapter2(Context context, List<Pet2> petList) {
        this.context = context;
        this.petList = petList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pet_profile_dashboard, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet2 pet = petList.get(position);
        holder.petNameTextView.setText(pet.getName());

        // Load pet image using Picasso
        Picasso.get()
                .load(pet.getImageUrl())
                .placeholder(R.drawable.pet_profile_storage) // Placeholder image
                .error(R.drawable.avatar) // Error image
                .into(holder.petImageView);

        // Set click listener to open PetProfileDisplay activity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PetProfileDisplay.class);
            intent.putExtra("user_id", pet.getUserId());  // Pass user_id
            intent.putExtra("pet_id", pet.getPetId());    // Pass pet_id
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    // Inner ViewHolder class
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
