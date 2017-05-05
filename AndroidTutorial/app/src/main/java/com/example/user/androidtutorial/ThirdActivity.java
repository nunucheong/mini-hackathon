package com.example.user.androidtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private String id;
    private TextView idTxt;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        db = new SQLiteHandler(this);
        id = db.getLoginId();

        idTxt = (TextView) findViewById(R.id.id);
        idTxt.setText(id);
    }
}
