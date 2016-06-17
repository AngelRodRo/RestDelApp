package com.example.restdeliveryapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.ResultListener;

public class LoginActivity extends AppCompatActivity{


    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    Context ctx;

    Meteor mMeteor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ctx = this;
        mMeteor = App.getInstance();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }


    public void login(){

        String email = edtEmail.getText().toString(),
                password = edtPassword.getText().toString();

        mMeteor.loginWithEmail(email,password,new ResultListener(){
            @Override
            public void onError(String error, String reason, String details) {
            }

            @Override
            public void onSuccess(String result) {
                Helpers.closeAndGoToActivity(ctx,MainActivity.class);
            }
        });

/*

        final JSONObject data = new JSONObject();
        try{
            data.put("email",email);
            data.put("password",password);
        }catch (JSONException e){

        }

        new AsyncTask<Void,Void,Integer>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... params) {
                int code = 0;
                try{
                    code = okHttpRequest.post("",data.toString());
                }catch (IOException e){
                }

                return code;
            }

            @Override
            protected void onPostExecute(Integer code) {
                super.onPostExecute(code);
                if(code==200) Toast.makeText(LoginActivity.this,"Llevando a la actividad principal",Toast.LENGTH_LONG).show();
            }
        }.execute(null,null,null);

*/


    }

}
