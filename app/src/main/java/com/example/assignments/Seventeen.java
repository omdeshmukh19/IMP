package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Seventeen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventeen);


                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                Button next = (Button) findViewById(R.id.next);
                Button prev = (Button) findViewById(R.id.prev);
                final int[] count = {0};

                //Next Button
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (count[0]) {
                            case -1:
                                imageView.setImageResource(R.drawable.img1);
                                count[0]+=1;
                                break;
                            case 0:
                                imageView.setImageResource(R.drawable.img2);
                                count[0]+=1;
                                break;
                            case 1:
                                imageView.setImageResource(R.drawable.img3);
                                count[0]+=1;
                                break;
                            case 2:
                                imageView.setImageResource(R.drawable.img4);
                                count[0]+=1;
                                break;
                            case 3:
                                imageView.setImageResource(R.drawable.img5);
                                count[0]=-1;
                                break;
                        }
                    }
                });

                //Prev Button
                prev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (count[0]) {
                            case -1:
                                imageView.setImageResource(R.drawable.img4);
                                count[0]=3;
                                break;
                            case 0:
                                imageView.setImageResource(R.drawable.img5);
                                count[0]=-1;
                                break;
                            case 1:
                                imageView.setImageResource(R.drawable.img1);
                                count[0]-=1;
                                break;
                            case 2:
                                imageView.setImageResource(R.drawable.img2);
                                count[0]-=1;
                                break;
                            case 3:
                                imageView.setImageResource(R.drawable.img3);
                                count[0]-=1;
                                break;
                        }
                    }
                });

            }
        }
