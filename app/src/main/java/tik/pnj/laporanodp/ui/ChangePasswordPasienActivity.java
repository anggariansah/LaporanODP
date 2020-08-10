package tik.pnj.laporanodp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienResponse;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;
import tik.pnj.laporanodp.util.UserPreference;

public class ChangePasswordPasienActivity extends AppCompatActivity {

    private TextInputEditText mEdtPasswordLama, mEdtPasswordBaru, mEdtKonfirmPasswordBaru;
    private Button mBtnChange;

    private ProgressDialog progressDialog;
    private UserPreference preferences;


    String id, passwordLama, passwordBaru, konfirmasiPasswordBaru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_pasien);

        mEdtPasswordLama = findViewById(R.id.text_input_edit_password_lama);
        mEdtPasswordBaru = findViewById(R.id.text_input_edit_password_baru);
        mEdtKonfirmPasswordBaru = findViewById(R.id.text_input_edit_konfirmasi_password_baru);
        mBtnChange = findViewById(R.id.btn_change);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Ganti Password");
        }

        progressDialog = new ProgressDialog(this);
        preferences = new UserPreference(this);

        id = preferences.getUserId();

        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validassiUpdate();
            }
        });
    }

    private void updatePassword() {

        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienResponse> getData = api.updatePasswordPasien(id, passwordLama, passwordBaru);
        getData.enqueue(new Callback<PasienResponse>() {
            @Override
            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {

                boolean error = response.body().isError();
                String pesan  = response.body().getMessage();

                if (!error) {

                    Toast.makeText(ChangePasswordPasienActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(ChangePasswordPasienActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PasienResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChangePasswordPasienActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void validassiUpdate() {

        passwordLama = mEdtPasswordLama.getText().toString();
        passwordBaru = mEdtPasswordBaru.getText().toString();
        konfirmasiPasswordBaru = mEdtKonfirmPasswordBaru.getText().toString();

        if (!passwordLama.equals("") || !passwordBaru.equals("") || !konfirmasiPasswordBaru.equals("")) {

            if (passwordBaru.equals(konfirmasiPasswordBaru)) {
                updatePassword();
            } else {
                Toast.makeText(this, "Password Baru Berbeda, Silahkan Periksa Lagi!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Data Tidak Boleh Kosong !!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
