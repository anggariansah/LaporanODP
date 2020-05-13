package tik.pnj.laporanodp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // widget
    TextInputEditText mEdtKtp, mEdtPassword;
    Button mBtnLogin;
    private ProgressDialog progressDialog;


    // vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // casting widget
        mEdtKtp = findViewById(R.id.text_input_edit_ktp);
        mEdtPassword = findViewById(R.id.text_input_edit_password);
        mBtnLogin = findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);


        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                verify();
                break;
        }
    }

    private void verify() {
        progressDialog.setMessage("Harap Tunggu ..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String username = mEdtKtp.getText().toString();
        String password = mEdtPassword.getText().toString();

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienEntity> login = api.loginUser(username, password);
        login.enqueue(new Callback<PasienEntity>() {
            @Override
            public void onResponse(Call<PasienEntity> call, Response<PasienEntity> response) {
                progressDialog.hide();

                boolean error = response.body().isError();

                if (error == false) {

                    loginSuccess();

                } else {
                    Toast.makeText(LoginActivity.this, "Login failed!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PasienEntity> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loginSuccess() {
        Toast.makeText(this, "Login Success!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish();
    }
}
