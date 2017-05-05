package com.example.user.androidtutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondaryActivity extends AppCompatActivity {

    private String id;
    private String pwd;
    private TextView idTxt;
    private Button confirm;
    private Button back;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        db = new SQLiteHandler(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id = extras.getString("id");
            pwd = extras.getString("pwd");
        }

        idTxt = (TextView) findViewById(R.id.id);
        confirm = (Button) findViewById(R.id.registerConfirm);
        back = (Button) findViewById(R.id.back);

        idTxt.setText(id);

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                db.addId(id,pwd);
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Going back", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
}
