package com.example.loginpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    API api;
    ArrayList<User> arr = new ArrayList<>();

    private EditText mEmail;
    private EditText mPassword;
    private Button mlogin;
    private TextView mtextshow;
    private String e;
    private String emails;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mtextshow = (TextView) findViewById(R.id.textshow);

        //try {
        //getinfor();//拿取資料
        //postinfor();//新增資料
        //deleteinfor();
        //changeinfor();
        //} catch (Exception e) {
            //mtextshow.setText(e.toString()+ " hello");
            //Log.e("MainActivity", e.getMessage());
        //}


        Button mCreateResAccountsButton = findViewById(R.id.login);
        mCreateResAccountsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent intent=new Intent();intent.setClass(Login.this,MainActivity.class);
                //startActivity(intent);

                //EditText em = (EditText) findViewById(R.id.email);
                //String input = em.getText().toString();
                //TextView tv = (TextView) findViewById(R.id.textshow);
                //tv.setText(input);
                getinfor();
            }
        });
    }

    public void getinfor() {
        api = RetrofitManager.getInstance().getAPI();
        Call<User> call = api.getInfor();
        final String einput = mEmail.getText().toString();
        final String pinput = mPassword.getText().toString();

        mEmail.setError(null);
        mPassword.setError(null);

        call.enqueue(new Callback<User>() {//成功透過onresponse回傳 失敗用onfailure回傳
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                boolean imin = false;

                for(int i=0; i<response.body().getarr().length;i++){
                    //TextUtils.equals(e,emails);
                    emails = response.body().getfieldsEmail(i);
                    pass = response.body().getfieldsPassword(i);
                    //mtextshow.append(emails + "\n");
                    if(einput.equals(emails) && pinput.equals(pass)){
                        //mtextshow.setText("我成功了!!!" + "\n");
                        //Intent intent=new Intent();intent.setClass(MainActivity.this,IndexActivity.class);
                        //startActivity(intent);
                        //Toast.makeText(getApplicationContext(),"登入成功!!",Toast.LENGTH_LONG).show();
                        imin = true;
                        break;
                    }
                    else{
                        imin = false;
                        //mtextshow.setText("失敗!!!" + "\n");
                        //Toast.makeText(getApplicationContext(),"失敗!!",Toast.LENGTH_LONG).show();
                    }
                }
                //response.body().getfieldsEmail(2);
                //mEmail.getText().toString();

                boolean cancel = false;
                View focusView = null;

                // Check for a valid password, if the user entered one.
                if (!TextUtils.isEmpty(pinput) && !isPasswordValid(pinput)) {
                    mPassword.setError(getString(R.string.error_invalid_password));
                    focusView = mPassword;
                    cancel = true;
                }

                // Check for a valid email address.
                if (TextUtils.isEmpty(einput)) {
                    mEmail.setError(getString(R.string.error_field_required));
                    focusView = mEmail;
                    cancel = true;
                } else if (!isEmailValid(einput)) {
                    mEmail.setError(getString(R.string.error_invalid_email));
                    focusView = mEmail;
                    cancel = true;
                }

                if(imin==true){
                    Intent intent=new Intent();intent.setClass(MainActivity.this,IndexActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"登入成功!!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"失敗!!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mtextshow.setText(t.getMessage());//我要傳上github
            }
        });
    }

    private boolean isEmailValid(String einput) {
        //TODO: Replace this with your own logic
        return einput.contains("@");
    }

    private boolean isPasswordValid(String pinput) {
        //TODO: Replace this with your own logic
        return pinput.length() > 4;
    }
}
