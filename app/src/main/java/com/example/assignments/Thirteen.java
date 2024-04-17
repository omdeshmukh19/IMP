package com.example.assignments;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Thirteen extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private EditText empNameEditText, empAddressEditText, empPhoneEditText, empSalaryEditText,
            deptNameEditText, deptLocationEditText;
    private Spinner deleteEmpSpinner;
    private List<String> employeeList;
    private ArrayAdapter<String> employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirteen);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        empNameEditText = findViewById(R.id.empNameEditText);
        empAddressEditText = findViewById(R.id.empAddressEditText);
        empPhoneEditText = findViewById(R.id.empPhoneEditText);
        empSalaryEditText = findViewById(R.id.empSalaryEditText);
        deptNameEditText = findViewById(R.id.deptNameEditText);
        deptLocationEditText = findViewById(R.id.deptLocationEditText);
        deleteEmpSpinner = findViewById(R.id.deleteEmpSpinner);

        Button addEmpButton = findViewById(R.id.addEmpButton);
        Button addDeptButton = findViewById(R.id.addDeptButton);
        Button deleteEmpButton = findViewById(R.id.deleteEmpButton);

        addEmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });

        addDeptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDepartment();
            }
        });

        deleteEmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });

        setupDeleteEmpSpinner();
    }



    public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "company.db";
        private static final int DATABASE_VERSION = 1;

        public static final String TABLE_EMP = "Emp";
        public static final String COLUMN_EMP_NO = "emp_no";
        public static final String COLUMN_EMP_NAME = "emp_name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_SALARY = "salary";

        public static final String TABLE_DEPT = "Dept";
        public static final String COLUMN_DEPT_NO = "dept_no";
        public static final String COLUMN_DEPT_NAME = "dept_name";
        public static final String COLUMN_LOCATION = "location";

        private static final String CREATE_EMP_TABLE = "CREATE TABLE " + TABLE_EMP + "(" +
                COLUMN_EMP_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMP_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_SALARY + " REAL" + ")";

        private static final String CREATE_DEPT_TABLE = "CREATE TABLE " + TABLE_DEPT + "(" +
                COLUMN_DEPT_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DEPT_NAME + " TEXT, " +
                COLUMN_LOCATION + " TEXT" + ")";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_EMP_TABLE);
            db.execSQL(CREATE_DEPT_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMP);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPT);
            onCreate(db);
        }

        // Additional methods for CRUD operations if needed
    }


    private void setupDeleteEmpSpinner() {
        employeeList = new ArrayList<>();
        employeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, employeeList);
        employeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deleteEmpSpinner.setAdapter(employeeAdapter);

        deleteEmpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle item selection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        populateEmployeeSpinner();
    }

    private void populateEmployeeSpinner() {
        // Fetch employees from database and populate spinner
        employeeList.clear();

        Cursor cursor = db.query(DatabaseHelper.TABLE_EMP, new String[]{DatabaseHelper.COLUMN_EMP_NAME}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String empName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMP_NAME));
            employeeList.add(empName);
        }
        cursor.close();

        employeeAdapter.notifyDataSetChanged();
    }

    private void addEmployee() {
        String name = empNameEditText.getText().toString().trim();
        String address = empAddressEditText.getText().toString().trim();
        String phone = empPhoneEditText.getText().toString().trim();
        double salary = Double.parseDouble(empSalaryEditText.getText().toString().trim());

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EMP_NAME, name);
        values.put(DatabaseHelper.COLUMN_ADDRESS, address);
        values.put(DatabaseHelper.COLUMN_PHONE, phone);
        values.put(DatabaseHelper.COLUMN_SALARY, salary);

        long newRowId = db.insert(DatabaseHelper.TABLE_EMP, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Failed to add employee", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Employee added successfully", Toast.LENGTH_SHORT).show();
            populateEmployeeSpinner(); // Update spinner after adding employee
        }
    }

    private void addDepartment() {
        String name = deptNameEditText.getText().toString().trim();
        String location = deptLocationEditText.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DEPT_NAME, name);
        values.put(DatabaseHelper.COLUMN_LOCATION, location);

        long newRowId = db.insert(DatabaseHelper.TABLE_DEPT, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Failed to add department", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Department added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteEmployee() {
        String selectedEmployee = deleteEmpSpinner.getSelectedItem().toString();
        int deletedRows = db.delete(DatabaseHelper.TABLE_EMP, DatabaseHelper.COLUMN_EMP_NAME + "=?", new String[]{selectedEmployee});

        if (deletedRows > 0) {
            Toast.makeText(this, "Employee deleted successfully", Toast.LENGTH_SHORT).show();
            populateEmployeeSpinner(); // Update spinner after deleting employee
        } else {
            Toast.makeText(this, "Failed to delete employee", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
