package com.example.inmodroid.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.inmodroid.R;
import com.example.inmodroid.responses.AuthAndRegisterResponse;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.AuthAndRegisterService;
import com.example.inmodroid.util.Util;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button btnGoRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.buttonLoginIniciarSesion);
        btnGoRegister = findViewById(R.id.buttonGoRegisterLogin);

        doLogin();


        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }


    public void onLoginSuccess(Call<AuthAndRegisterResponse> call, Response<AuthAndRegisterResponse> response){

        Util.setData(LoginActivity.this,response.body().getToken(), response.body().getUser().getId(),
                response.body().getUser().getEmail(), response.body().getUser().getName(),response.body().getUser().getPicture());


        startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
        finish();

    }


    public void onLoginFail(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        builder.setIcon(R.drawable.ic_cancelar);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        builder.setMessage(R.string.login_error)
            .setTitle(R.string.error);


        AlertDialog dialog = builder.create();
        dialog.show();

    }


    private void doLogin() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autenticando...");
                progressDialog.show();


                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                String credentialsLogin = Credentials.basic(emailText,passwordText);

                AuthAndRegisterService loginService = ServiceGenerator.createService(AuthAndRegisterService.class);

                final Call<AuthAndRegisterResponse> callLogin = loginService.login(credentialsLogin);

                callLogin.enqueue(new Callback<AuthAndRegisterResponse>() {
                    @Override
                    public void onResponse(Call<AuthAndRegisterResponse> call, final Response<AuthAndRegisterResponse> response) {
                        if(response.isSuccessful()){
                            Runnable progressRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.cancel();
                                    onLoginSuccess(callLogin,response);
                                }
                            };

                            Handler pdCanceller = new Handler();
                            pdCanceller.postDelayed(progressRunnable,2000);
                        }else{
                            progressDialog.cancel();
                            onLoginFail();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthAndRegisterResponse> call, Throwable t) {

                    }
                });

            }
        });




    }
}
