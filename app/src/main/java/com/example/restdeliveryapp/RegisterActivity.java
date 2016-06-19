package com.example.restdeliveryapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.ResultListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {


    EditText edtName;
    EditText edtLastname;
    EditText edtPhone;
    EditText edtPassword;
    EditText edtEmail;

//    OkHttpRequest okHttpRequest;
    Meteor mMeteor;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mMeteor = App.getInstance();
        ctx = this;

        edtName = (EditText) findViewById(R.id.edtName);
        edtLastname = (EditText) findViewById(R.id.edtLastname);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
    }


    public void register(){


        String name = edtName.getText().toString(),
                lastname = edtLastname.getText().toString(),
                phone = edtPhone.getText().toString(),
                password = edtPassword.getText().toString(),
                email = edtEmail.getText().toString();


        HashMap<String, Object> profile = new HashMap<String, Object>();
        profile.put("name", name);
        profile.put("lastname",lastname);
        profile.put("phone",phone);

        mMeteor.registerAndLogin(name, email, password, profile, new ResultListener() {
            @Override
            public void onSuccess(String result) {
                Helpers.closeAndGoToActivity(ctx,MainActivity.class);
            }

            @Override
            public void onError(String error, String reason, String details) {

            }
        });

/*        new AsyncTask<Void,Void,Integer>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... params) {

                int code = 0;

                try {
                    code = okHttpRequest.post("http://restdel.herokuapp.com/usuarios",data.toString());
                }catch (IOException e){

                }
                return code;
            }


            @Override
            protected void onPostExecute(Integer code) {
                super.onPostExecute(code);

                if(code==200) Toast.makeText(RegisterActivity.this,"Se registro correctamente",Toast.LENGTH_LONG).show();
                else Toast.makeText(RegisterActivity.this,"No se registro correctamente",Toast.LENGTH_LONG).show();

            }
        }.execute(null,null,null);*/
    }


}
