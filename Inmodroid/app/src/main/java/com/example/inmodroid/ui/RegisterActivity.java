package com.example.inmodroid.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inmodroid.R;
import com.example.inmodroid.models.Register;
import com.example.inmodroid.responses.AuthAndRegisterResponse;
import com.example.inmodroid.retrofit.generator.ServiceGenerator;
import com.example.inmodroid.retrofit.services.AuthAndRegisterService;
import com.example.inmodroid.util.Util;
import com.example.inmodroid.util.UtilToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText nombre, correo, pass;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Fijar pantalla vertical

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nombre = findViewById(R.id.nombreRegistro);
        correo = findViewById(R.id.emailRegistro);
        pass = findViewById(R.id.passwordRegistro);
        btnRegister = findViewById(R.id.buttonRegistrarUsuario);

        doRegister();

    }


    public void onRegisterSuccess(Call<AuthAndRegisterResponse> call, Response<AuthAndRegisterResponse> response){

        Util.setData(RegisterActivity.this, response.body().getToken(),response.body().getUser().getId(),
                response.body().getUser().getEmail(),response.body().getUser().getName(), response.body().getUser().getPicture());

        startActivity(new Intent(RegisterActivity.this,DashboardActivity.class));

    }

    public void onRegisterFail(int tipoError){
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setIcon(R.drawable.ic_cancelar);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        builder.setMessage(tipoError)
                .setTitle(R.string.error);

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void doRegister(){

        /*btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Registrando...");
                progressDialog.show();

                String name = nombre.getText().toString().trim();
                String email = correo.getText().toString().trim();
                String password = pass.getText().toString().trim();


                if(password.length() < 6){
                    onRegisterFail(R.string.register_contraseña_no_segura);
                }


            }
        });
    }*/

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Recoger los datos del formulario
                String email = correo.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String name = nombre.getText().toString().trim();



                Register registro = new Register(name, email, password);

                AuthAndRegisterService service = ServiceGenerator.createService(AuthAndRegisterService.class);

                Call<AuthAndRegisterResponse> loginReponseCall = service.register(registro);
                //.doRegister("lNeTI8waAqmpUZa7QSiLv53rqSnlsldv",
                //        registro);

                loginReponseCall.enqueue(new Callback<AuthAndRegisterResponse>() {
                    @Override
                    public void onResponse(Call<AuthAndRegisterResponse> call, Response<AuthAndRegisterResponse> response) {
                        if (response.code() == 201) {
                            // éxito
                            /*
                                Pasos:
                                    1) Almacenar el token donde corresponda.
                                    2) Lanzar el siguiente Activity.
                             */
                            //ServiceGenerator.jwtToken = response.body().getToken();
                            UtilToken.setToken(RegisterActivity.this, response.body().getToken());
                            startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));                            // Toast.makeText(RegistroActivity.this, "Usuario registrado y logeado con éxito", Toast.LENGTH_LONG).show();
                            // Log.d("token", response.body().getToken());

                        } else {
                            // error
                            Toast.makeText(RegisterActivity.this, "Error en el registro. Revise los datos introducidos", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthAndRegisterResponse> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(RegisterActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }

    /*public void crearUsuarioNuevo(Register registro, final ProgressDialog progressDialog){
        AuthAndRegisterService service = ServiceGenerator.createService(AuthAndRegisterService.class);

        Call<AuthAndRegisterResponse> registerResponseCall = service.register(registro);

        registerResponseCall.enqueue(new Callback<AuthAndRegisterResponse>() {

            @Override
            public void onResponse(Call<AuthAndRegisterResponse> call, Response<AuthAndRegisterResponse> response) {

                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    onRegisterSuccess(call, response);

                } else {
                    progressDialog.dismiss();
                    onRegisterFail(R.string.error);
                }
            }

            @Override
            public void onFailure(Call<AuthAndRegisterResponse> call, Throwable t) {
                Log.e("NetworkFail", t.getMessage());
                Toast.makeText(RegisterActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
