package com.example.contentproviderapp;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    ContentAdapter adapter;
    RecyclerView recyclerView;
    ClickListener listener;
    List<EmployeeData> list = new ArrayList<>();
    List<EmployeeData> temp_list = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//list for recycler view

        recyclerView
                = (RecyclerView) findViewById(
                R.id.recyclerView);
        listener = new ClickListener() {
            @Override
            public void click(int index) {
                Toast.makeText(getApplicationContext(), "Clicked item index is " + index, Toast.LENGTH_SHORT).show();
            }
        };
        adapter = new ContentAdapter(list, getApplication(), listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(MainActivity.this));


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public void onClickAddDetails(View view) {
        EditText userNameText = findViewById(R.id.textUserName);
        EditText DesignationText = findViewById(R.id.textDesignation);
        EditText nameText = findViewById(R.id.textName);
        EditText salaryText = findViewById(R.id.textSal);
// class to add values in the database
        ContentValues values = new ContentValues();
// fetching text from user
        values.put(MyContentProvider.username, userNameText.getText().toString());
        values.put(MyContentProvider.designation, DesignationText.getText().toString());
        values.put(MyContentProvider.name, nameText.getText().toString());
        values.put(MyContentProvider.salary, salaryText.getText().toString());
// inserting into database through content URI
        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
// displaying a toast message
        Toast.makeText(this, "New Record Inserted", Toast.LENGTH_LONG).show();
        nameText.setText("");
    }

// private EmployeeData getDataFromUser() {
// name = findViewById(R.id.nameTV);
// id = findViewById(R.id.idTV);
// dept = findViewById(R.id.deptTV);
// nameT = name.getText().toString();
// idT = id.getText().toString();
// deptT = dept.getText().toString();
// return new EmployeeData(nameT, idT, deptT);
// }


    @SuppressLint("Range")
    public void onClickShowDetails(View view) {
// inserting complete table details in this text field


// TextView resultView= (TextView) findViewById(R.id.res);
// creating a cursor object of the
// content URI
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                null, null, null, null);
// iteration of the cursor
// to print whole table
        if (cursor.moveToFirst()) {
            StringBuilder strBuild = new StringBuilder();
            while (!cursor.isAfterLast()) {
// strBuild.append("\n").
// append(cursor.getString(cursor.getColumnIndex(MyContentProvider.id))).
// append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.username))).
// append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.designation))).
// append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.name))).
// append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.salary)));

                String usernameVal = cursor.getString(cursor.getColumnIndex(MyContentProvider.username));
                String designationVal = cursor.getString(cursor.getColumnIndex(MyContentProvider.designation));
                String nameVal = cursor.getString(cursor.getColumnIndex(MyContentProvider.name));
                String salaryVal = cursor.getString(cursor.getColumnIndex(MyContentProvider.salary));

                list.add(new EmployeeData(usernameVal, nameVal,designationVal, salaryVal));
                adapter.notifyItemInserted(list.size() - 1);
                cursor.moveToNext();
            }
        }
    }
}