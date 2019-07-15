package net.simplifiedcoding.bottomnavigationexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText field1;
    EditText field2;
    EditText field3;
    EditText field4;
    EditText field5;
    EditText field6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        field1 = findViewById(R.id.editText15);
        field2 = findViewById(R.id.editText14);
        field3 = findViewById(R.id.editText13);
        field4 = findViewById(R.id.editText16);
        field5 = findViewById(R.id.editText17);
        field6 = findViewById(R.id.editText18);
    }

    public void cancelClick(View v){
        Intent intent = new Intent(this, Signin.class);
        startActivity(intent);
    }

    public void regiterClick(View v){
        if(field1.getText().toString().equals("USERNAME")||
                field1.getText().toString().equals("PASSWORD")||
                field1.getText().toString().equals("CONFIRM PASSWORD")||
                field1.getText().toString().equals("POLICY NUMBER")||
                field1.getText().toString().equals("EMAIL ADDRESS")||
                field1.getText().toString().equals("TEL NUMBER")){
            Toast.makeText(Signup.this,"Please complete all the fields",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Signup.this,"Register success! You wil receive a email regarding login",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Signin.class);
            startActivity(intent);
        }
    }
}
