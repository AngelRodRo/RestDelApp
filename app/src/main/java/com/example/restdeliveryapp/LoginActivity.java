package com.example.restdeliveryapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;

public class LoginActivity extends Activity {


    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    Context ctx;

    Meteor mMeteor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        mMeteor = MeteorSingleton.getInstance();
        ctx = this;

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        Log.i("logeado",""+mMeteor.isLoggedIn());

        if(mMeteor.isLoggedIn())    Helpers.closeAndGoToActivity(ctx,MainActivity.class);

    }



    @Override
    protected void onStart() {
        super.onStart();
    }


    public void login(){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String email = edtEmail.getText().toString(),
                        password = edtPassword.getText().toString();
                Log.i("params",email + " " + password);
                mMeteor.loginWithEmail(email,password,new ResultListener(){


                    @Override
                    public void onError(String error, String reason, String details) {
                        if(error.equals(reason)) Toast.makeText(ctx,"Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();

//                        Log.i("Error-Login",error);
//                        Log.i("Reason-Login",reason);
//                        Log.i("Details-login",details);
                    }

                    @Override
                    public void onSuccess(String result) {
                        Log.i("Result",result);
                        Helpers.closeAndGoToActivity(ctx,MainActivity.class);
                    }
                });

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
