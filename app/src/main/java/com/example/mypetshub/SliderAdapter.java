package com.example.mypetshub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private final List<SliderItem> sliderItems; // Made final
    private final Context context; // Made final

    public SliderAdapter(List<SliderItem> sliderItems, Context context) {
        this.sliderItems = sliderItems;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_item, parent, false); // Ensure the layout name matches
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        SliderItem sliderItem = sliderItems.get(position);
        holder.sliderImage.setImageResource(sliderItem.getImage()); // Fixed getImage() and getText()
        holder.sliderText.setText(sliderItem.getText());
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    // Made the class static, which is acceptable within this context
    public static class SliderViewHolder extends RecyclerView.ViewHolder {
        ImageView sliderImage;
        TextView sliderText;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            sliderImage = itemView.findViewById(R.id.sliderImage);
            sliderText = itemView.findViewById(R.id.sliderText);
        }
    }
}
