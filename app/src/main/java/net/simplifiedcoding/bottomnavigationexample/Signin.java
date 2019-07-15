package net.simplifiedcoding.bottomnavigationexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class Signin extends AppCompatActivity{
    EditText userName;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        userName = findViewById(R.id.editText2);
        password = findViewById(R.id.editText);
    }

    public void loginClick(View v){
        String userNameText = userName.getText().toString();
        String passwordText = password.getText().toString();
        if(userNameText.equals("admin")&&passwordText.equals("123")){
            openWelcome();
        }
        else{
            Toast.makeText(Signin.this,"Login fail, please check username and password",Toast.LENGTH_SHORT).show();
        }
    }

    public void signupClick(View v){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    public void openWelcome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
