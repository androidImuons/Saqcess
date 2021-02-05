package com.saqcess.notification.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.saqcess.notification.R;

public class MessageDetailsActivity extends AppCompatActivity {

    ImageView iv_close;
    TextView txt_title;
    TextView txt_msg;
    TextView txt_date;
    SimpleDraweeView spb_image;
    SimpleDraweeView image_view;
    String image;
    String date;
    String title;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        image = getIntent().getExtras().getString("image");
        date = getIntent().getExtras().getString("date");
        title = getIntent().getExtras().getString("title");
        msg = getIntent().getExtras().getString("msg");

        initUI();
    }

    private void initUI() {
        iv_close = findViewById(R.id.iv_close);
        txt_title = findViewById(R.id.tv_title);
        txt_msg = findViewById(R.id.tv_msg);
        txt_date = findViewById(R.id.tv_date);
        spb_image = findViewById(R.id.svg_image);
        image_view = findViewById(R.id.image);

        txt_date.setText(date);
        txt_title.setText(title);
        txt_msg.setText(msg);

        txt_msg.setMovementMethod(LinkMovementMethod.getInstance());
        txt_msg.setLinkTextColor(getResources().getColor(R.color.colorPrimary));
        Linkify.addLinks(txt_msg, Linkify.WEB_URLS);

        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setBorder(getResources().getColor(R.color.colorBlack), 1.0f);
        roundingParams.setRoundAsCircle(true);
        spb_image.getHierarchy().setRoundingParams(roundingParams);
        spb_image.setImageResource(R.drawable.ic_logo);
        if (image.equals("")){
            image_view.setVisibility(View.GONE);
        }else {
            image_view.setVisibility(View.VISIBLE);
            image_view.setImageURI(image);
        }

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        image_view.setOnClickListener(view -> {

            Intent intent=new Intent(getApplicationContext(),ImageViewActivity.class);
            intent.putExtra("image",image);
            intent.putExtra("date",date);
            intent.putExtra("title",title);
            intent.putExtra("msg",msg);
            startActivity(intent);
        });
    }
}