package com.example.jains.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR,age INT(3))");
            //myDatabase.execSQL("INSERT INTO users VALUES('Shrey',20)");
            //myDatabase.execSQL("INSERT INTO users VALUES('Shreyansh',20)");

            Cursor c = myDatabase.rawQuery("SELECT * FROM users",null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();

            myDatabase.execSQL("UPDATE users SET age = 2 WHERE name ='Shreyansh'");
            //myDatabase.execSQL("DELETE FROM users WHERE name = 'Shrey'");

            while(c!=null)
            {
                Log.i("Names",c.getString(nameIndex));
                Log.i("Age",c.getString(ageIndex));

                c.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
