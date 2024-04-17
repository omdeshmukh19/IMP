package com.example.assignments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Eleven extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private TextView textView;
    private EditText nameEditText, addressEditText, phnoEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleven);

                dbHelper = new DatabaseHelper(this);
                db = dbHelper.getWritableDatabase();

                textView = findViewById(R.id.textView);
                nameEditText = findViewById(R.id.nameEditText);
                addressEditText = findViewById(R.id.addressEditText);
                phnoEditText = findViewById(R.id.phnoEditText);
                Button insertButton = findViewById(R.id.insertButton);
                Button showButton = findViewById(R.id.showButton);
                Button delete=findViewById(R.id.deleteButton);
                Button deleteAllButton = findViewById(R.id.deleteAllButton);


        insertButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = nameEditText.getText().toString().trim();
                        String address = addressEditText.getText().toString().trim();
                        String phno = phnoEditText.getText().toString().trim();

                        if (!name.isEmpty() && !address.isEmpty() && !phno.isEmpty()) {
                            insertCustomer(name, address, phno);
                            nameEditText.setText("");
                            addressEditText.setText("");
                            phnoEditText.setText("");
                        } else {
                            Toast.makeText(Eleven.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                showButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCustomers();
                    }
                });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCustomer();
            }
        });
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllCustomers();
            }
        });
            }


    public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "customer.db";
        private static final int DATABASE_VERSION = 1;

        public static final String TABLE_CUSTOMER = "Customer";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHNO = "phno";

        private static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_CUSTOMER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PHNO + " TEXT" + ")";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_CUSTOMER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
            onCreate(db);
        }
    }

        private void insertCustomer(String name, String address, String phno) {
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_NAME, name);
                values.put(DatabaseHelper.COLUMN_ADDRESS, address);
                values.put(DatabaseHelper.COLUMN_PHNO, phno);

                db.insert(DatabaseHelper.TABLE_CUSTOMER, null, values);
            }

    private void showCustomers() {
        Cursor cursor = db.query(DatabaseHelper.TABLE_CUSTOMER, null, null, null, null, null, null);

        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            String address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ADDRESS));
            String phno = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHNO));

            builder.append("ID: ").append(id).append(", Name: ").append(name)
                    .append(", Address: ").append(address).append(", Phone: ").append(phno)
                    .append("\n");
        }
        cursor.close();

        textView.setText(builder.toString());
    }

            @Override
            protected void onDestroy() {
                dbHelper.close();
                super.onDestroy();
            }

    private void deleteCustomer() {
        // Fetch all customers from database
        Cursor cursor = db.query(DatabaseHelper.TABLE_CUSTOMER, null, null, null, null, null, null);
        final ArrayList<String> customerNames = new ArrayList<>();
        final ArrayList<Integer> customerIds = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            customerNames.add(name);
            customerIds.add(id);
        }
        cursor.close();

        // Show dialog with list of customer names
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Customer to Delete");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, customerNames);
        ListView listView = new ListView(this);
        listView.setAdapter(adapter);
        builder.setView(listView);

        final AlertDialog dialog = builder.create();
        dialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int customerId = customerIds.get(position);

                AlertDialog.Builder confirmDialogBuilder = new AlertDialog.Builder(Eleven.this);
                confirmDialogBuilder.setTitle("Confirm Delete");
                confirmDialogBuilder.setMessage("Are you sure you want to delete this customer?");
                confirmDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Delete customer from database
                        db.delete(DatabaseHelper.TABLE_CUSTOMER, DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(customerId)});
                        Toast.makeText(Eleven.this, "Customer deleted", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                confirmDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                confirmDialogBuilder.show();
            }
        });
    }

    private void deleteAllCustomers() {
        AlertDialog.Builder confirmDialogBuilder = new AlertDialog.Builder(this);
        confirmDialogBuilder.setTitle("Confirm Delete All");
        confirmDialogBuilder.setMessage("Are you sure you want to delete all customers?");
        confirmDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Delete all customers from database
                db.delete(DatabaseHelper.TABLE_CUSTOMER, null, null);
                Toast.makeText(Eleven.this, "All customers deleted", Toast.LENGTH_SHORT).show();
            }
        });
        confirmDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing
            }
        });
        confirmDialogBuilder.show();
    }
        }


