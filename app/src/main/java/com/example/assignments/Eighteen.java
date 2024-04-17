package com.example.assignments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Eighteen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighteen);
        String gfgurl = "https://www.geeksforgeeks.org/";
        String googleurl="https://www.google.com";
        String yturl = "https://www.youtube.com/";
        ImageView google = (ImageView) findViewById(R.id.google);
        ImageView gfg = (ImageView) findViewById(R.id.geeksforgeeks);
        ImageView yt = (ImageView) findViewById(R.id.youtube);



                // google url listener
                google.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        redirect(googleurl);
                    }

                    public void redirect(String url) {

                        AlertDialog.Builder alert = new AlertDialog.Builder(Eighteen.this);
                        alert.setMessage("We r redirecting to official site.you wanna continue?");
                        alert.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent  = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog d = alert.create();
                        d.show();
                    }
                });

                // geeksforgeeks url listener
                gfg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        redirect(gfgurl);
                    }
                    public void redirect(String url) {

                        AlertDialog.Builder alert = new AlertDialog.Builder(Eighteen.this);
                        alert.setMessage("We r redirecting to official site.you wanna continue?");
                        alert.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent  = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog d = alert.create();
                        d.show();
                    }
                });

                // youtube url listener
                yt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        redirect(yturl);
                    }
                    public void redirect(String url) {

                        AlertDialog.Builder alert = new AlertDialog.Builder(Eighteen.this);
                        alert.setMessage("We r redirecting to official site.you wanna continue?");
                        alert.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent  = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog d = alert.create();
                        d.show();
                    }
                });
            }
        }
