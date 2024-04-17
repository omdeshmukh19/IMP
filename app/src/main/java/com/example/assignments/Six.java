package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toolbar;
import android.widget.ViewSwitcher;

public class Six extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);

        Button next = findViewById(R.id.next);

        //ImageArray
        int imageSwitcherImages[] =
                {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
                        R.drawable.img5, R.drawable.img6, R.drawable.img7};

        final int[] counter = {1};

        //ImageSwitcher
        ImageSwitcher img = (ImageSwitcher) findViewById(R.id.img);
        img.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setImageResource(R.drawable.img1);
                return imageView;
            }
        });

        //NextButtonListener
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[0] == imageSwitcherImages.length){
                    counter[0] = 0;
                    img.setImageResource(imageSwitcherImages[0]);
                    counter[0]+=1;
                }else{
                    img.setImageResource(imageSwitcherImages[counter[0]]);
                    counter[0]+=1;
                }
            }
        });
    }
}