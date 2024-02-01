package com.example.contentprovidertest;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
    public void onClickAddDetails(View view) {
        EditText userNameText = findViewById(R.id.textID);
        EditText DesignationText = findViewById(R.id.textDes);
        EditText nameText = findViewById(R.id.textName);
        EditText salaryText = findViewById(R.id.textSal);
// class to add values in the database
        ContentValues values = new ContentValues();
// fetching text from user
        values.put(MyContentProvider.username,userNameText.getText().toString());
        values.put(MyContentProvider.designation,DesignationText.getText().toString());
        values.put(MyContentProvider.name,nameText.getText().toString());
        values.put(MyContentProvider.salary,salaryText.getText().toString());
// inserting into database through content URI
        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
// displaying a toast message
        Toast.makeText(this, "New Record Inserted", Toast.LENGTH_LONG).show();
        nameText.setText("");
    }
    @SuppressLint("Range")
    public void onClickShowDetails(View view) {
// inserting complete table details in this text field
        TextView resultView= (TextView) findViewById(R.id.res);
// creating a cursor object of the
// content URI
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                null, null, null, null);
// iteration of the cursor
// to print whole table
        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n").
                        append(cursor.getString(cursor.getColumnIndex(MyContentProvider.id))).
                        append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.username))).
                        append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.designation))).
                        append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.name))).
                        append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.salary)));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        }
        else {
            resultView.setText("No Records Found");
        }
    }
}

