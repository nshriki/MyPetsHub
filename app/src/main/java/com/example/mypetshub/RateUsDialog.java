//package com.example.mypetshub;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.ScaleAnimation;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.AppCompatButton;
//
//public class RateUsDialog extends Dialog {
//
//    private float userRate = 0;
//
//    public RateUsDialog(@NonNull Context context) {
//        super(context);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.rate_us_dialog_layout);
//
//        final AppCompatButton rateNowBtn = findViewById(R.id.rateNowBtn);
//        final AppCompatButton laterBtn = findViewById(R.id.laterBtn);
//        final RatingBar ratingBar = findViewById(R.id.ratingBar);
//        final ImageView ratingImage = findViewById(R.id.ratingImage);
//
//        rateNowBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                userRate
//                // code to save rating to mysql db
//
//            }
//        });
//
//        laterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // hide rating dialog
//                dismiss();
//            }
//        });
//
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//
//                if (rating <= 1) {
//                   ratingImage.setImageResource(R.drawable.one_star);
//                } else if (rating <= 2) {
//                    ratingImage.setImageResource(R.drawable.two_star);
//                } else if (rating <= 3) {
//                    ratingImage.setImageResource(R.drawable.three_star);
//                } else if (rating <= 4) {
//                    ratingImage.setImageResource(R.drawable.four_star);
//                } else if (rating <= 5){
//                    ratingImage.setImageResource(R.drawable.five_star);
//                }
//
//                // animate emoji image
//                animateImage(ratingImage);
//
//                // selected rating by user
//                userRate = rating;
//
//            }
//        });
//
//
//    }
//
//    private void animateImage(ImageView ratingImage){
//        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0,1f,
//                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//
//        scaleAnimation.setFillAfter(true);
//        scaleAnimation.setDuration(200);
//        ratingImage.startAnimation(scaleAnimation);
//    }
//
//
//}


package com.example.mypetshub;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RateUsDialog extends Dialog {

    private float userRate = 0;
    private String feedbackUrl = "https://hamugaway.scarlet2.io/feedback.php";  // Your feedback submission URL

    public RateUsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rate_us_dialog_layout);

        final AppCompatButton rateNowBtn = findViewById(R.id.rateNowBtn);
        final AppCompatButton laterBtn = findViewById(R.id.laterBtn);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final ImageView ratingImage = findViewById(R.id.ratingImage);
        final EditText ratingComment = findViewById(R.id.ratingComment);

        rateNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = ratingComment.getText().toString().trim();

                // Validate that both rating and comment are provided
                if (userRate == 0) {
                    Toast.makeText(getContext(), "Please select a rating.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(comment)) {
                    Toast.makeText(getContext(), "Please enter a comment.", Toast.LENGTH_SHORT).show();
                } else {
                    submitFeedback(userRate, comment);
                }
            }
        });

        laterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hide rating dialog
                dismiss();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 1) {
                    ratingImage.setImageResource(R.drawable.one_star);
                } else if (rating <= 2) {
                    ratingImage.setImageResource(R.drawable.two_star);
                } else if (rating <= 3) {
                    ratingImage.setImageResource(R.drawable.three_star);
                } else if (rating <= 4) {
                    ratingImage.setImageResource(R.drawable.four_star);
                } else if (rating <= 5) {
                    ratingImage.setImageResource(R.drawable.five_star);
                }

                // animate emoji image
                animateImage(ratingImage);

                // selected rating by user
                userRate = rating;
            }
        });
    }

    private void animateImage(ImageView ratingImage) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.startAnimation(scaleAnimation);
    }

    // Method to submit the feedback to the server
    private void submitFeedback(final float rating, final String comment) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, feedbackUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Show success message
                        Toast.makeText(getContext(), "Thanks for your feedback!", Toast.LENGTH_SHORT).show();
                        dismiss();  // Close the dialog after feedback is submitted
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Show error message
                        Toast.makeText(getContext(), "Failed to submit feedback. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("rating", String.valueOf(rating));  // Send the rating
                params.put("comment", comment);  // Send the comment
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
