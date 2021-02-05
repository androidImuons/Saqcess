package com.saqcess.notification.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.saqcess.notification.R;

public class ImageViewActivity extends AppCompatActivity {
SimpleDraweeView image;
    private String imageurl;
    ImageView iv_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        image=(SimpleDraweeView)findViewById(R.id.image);
        imageurl = getIntent().getExtras().getString("image");
        iv_close = findViewById(R.id.iv_close);

        if (imageurl.equals("")){
            image.setVisibility(View.GONE);
        }else {
            image.setVisibility(View.VISIBLE);
            image.setImageURI(imageurl);
        }
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}