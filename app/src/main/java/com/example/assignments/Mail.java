package com.example.assignments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.net.PasswordAuthentication;
import java.util.Properties;




public class Mail extends AppCompatActivity {
    private static final int PICK_FROM_GALLERY = 101;
    Message msg;
    EditText Recipient, Subject, Body;
    Button Send, attachment;
    TextView tvAttachment;
    String email, subject, message, path;
    Uri URI = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mail);

                Recipient = findViewById(R.id.editText1);
                Subject = findViewById(R.id.editText2);
                Body = findViewById(R.id.editText3);
                attachment = findViewById(R.id.attachfiles);
                tvAttachment = findViewById(R.id.attachment);
                Send = findViewById(R.id.send);

                Send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendEmail();
                    }
                });

                attachment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openFolder();
                    }
                });
            }

//            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//                if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
//                    URI = data.getData();
//                    File file = new File(URI.getPath());
//                    path = file.getPath();
//                    tvAttachment.setText(" \uD83D\uDCCE " + URI.getLastPathSegment());
//                    tvAttachment.setVisibility(View.VISIBLE);
//                }
//            }

            public void sendEmail() {
                email = Recipient.getText().toString();
                subject = Subject.getText().toString();
                message = Body.getText().toString();
                final String username = "213341003@moderncollegegk.org";
                final String password = "Pratiksha@14";
                Properties prop = new Properties();
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.starttls.enable", "true");
                prop.put("mail.smtp.host", "smtp.gmail.com");
                prop.put("mail.smtp.port", "587");
//                Session session = Session.getInstance(prop, new javax.Mail.Authenticator() {
//
////                    protected PasswordAuthentication getPasswordAuthentication() {
////                        return new PasswordAuthentication(username, password);
////                    }
//                });
//                try {
//                    msg = new MimeMessage(session);
//                    msg.setFrom(new InternetAddress(username));
//                    msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
//                    msg.setSubject(subject);
//                    msg.setText(message);
//                    new Extras().execute(msg);
//                } catch (Throwable t) {
//                    Log.i("Status", t.toString());
//                }
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            public void openFolder() {
                Intent intent = new Intent();
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("return-data", true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
            }

            public class Extras extends AsyncTask<Message, String, String> {

                private ProgressDialog processDialog;

                @Override
                protected String doInBackground(Message... messages) {
//                    try {
//                        Transport.send(msg);
//                        return "Success";
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                        return "Error";
//                    }
//
                    return null;
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    processDialog = ProgressDialog.show(Mail.this, "Please Wait", "Sending mail...");
                }
//
//                @Override
//                protected String doInBackground(Message... messages) {
//                    try {
//                        Transport.send(msg);
//                        return "Success";
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                        return "Error";
//                    }
//                }

                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    processDialog.dismiss();
                    if (s.equals("Success")) {
                        Toast.makeText(Mail.this, "Mail sent Successfully!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Mail.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }





