package tik.pnj.laporanodp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienResponse;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;
import tik.pnj.laporanodp.util.UserPreference;

public class LoginPjActivity extends AppCompatActivity {

    // widget
    TextInputEditText mEdtKtp, mEdtPassword;
    Button mBtnLogin;
    private ProgressDialog progressDialog;

    // vars
    private UserPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pj);

        // casting widget
        mEdtKtp = findViewById(R.id.text_input_edit_ktp);
        mEdtPassword = findViewById(R.id.text_input_edit_password);
        mBtnLogin = findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);
        preference = new UserPreference(this);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
    }


    private void verify() {
        String username = mEdtKtp.getText().toString().trim();
        String password = mEdtPassword.getText().toString().trim();

        if (username.equals("") && password.equals("")) {
            Toast.makeText(this, "Mohon Lengkapi !", Toast.LENGTH_SHORT).show();
        } else {
            checkLogin(username, password);
        }
    }

    private void checkLogin(String username, String password) {
        progressDialog.setMessage("Harap Tunggu ..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienResponse> login = api.loginPJ(username, password);
        login.enqueue(new Callback<PasienResponse>() {
            @Override
            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {
                progressDialog.dismiss();

                boolean error = response.body().isError();

                if (!error) {
                    String nik = response.body().getUser().get(0).getNik();
                    preference.createLoginSession(nik, "pj");
                    Toast.makeText(LoginPjActivity.this, "Login Success!!", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(LoginPjActivity.this, DashboardActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginPjActivity.this, "Login failed!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PasienResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginPjActivity.this, "Network Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void loginSuccess(final String nik) {
//
//        progressDialog.setMessage("Harap Tunggu ..");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//
//        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
//        Call<PasienResponse> getProfile = api.detailProfile(nik);
//        getProfile.enqueue(new Callback<PasienResponse>() {
//            @Override
//            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {
//                progressDialog.dismiss();
//
//                boolean error = response.body().isError();
//
//                if (!error) {
//                    String nomorKk = response.body().getListDataPasien().get(0).getNomorKK();
//
//                } else {
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<PasienResponse> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(LoginPjActivity.this, "Network Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
