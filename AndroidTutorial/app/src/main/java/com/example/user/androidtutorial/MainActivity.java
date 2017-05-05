package com.example.user.androidtutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText id;
    private EditText pwd;
    private EditText pwdConfirm;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.loginId);
        pwd = (EditText) findViewById(R.id.registerPwd);
        pwdConfirm = (EditText) findViewById(R.id.registerPwdConfirm);
        register = (Button) findViewById(R.id.registerBtn);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(pwd.getText().toString().equals(pwdConfirm.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), SecondaryActivity.class);
                    intent.putExtra("id", id.getText().toString());
                    intent.putExtra("pwd", pwd.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Non-matching passwords, try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
