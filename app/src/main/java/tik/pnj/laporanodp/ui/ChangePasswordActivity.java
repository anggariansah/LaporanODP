package tik.pnj.laporanodp.ui;

import android.app.ProgressDialog;
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
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.data.PasienResponse;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;

public class ChangePasswordActivity extends AppCompatActivity {

    private TextInputEditText mEdtPasswordLama, mEdtPasswordBaru, mEdtKonfirmPasswordBaru;
    private Button mBtnChange;

    private ProgressDialog progressDialog;


    String id, passwordLama, dataPasswordLama, passwordBaru, konfirmasiPasswordBaru;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mEdtPasswordLama = findViewById(R.id.text_input_edit_password_lama);
        mEdtPasswordBaru = findViewById(R.id.text_input_edit_password_baru);
        mEdtKonfirmPasswordBaru = findViewById(R.id.text_input_edit_konfirmasi_password_baru);
        mBtnChange = findViewById(R.id.btn_change);

        progressDialog = new ProgressDialog(this);

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
        Call<PasienResponse> getData = api.updatePassword(id, passwordBaru);
        getData.enqueue(new Callback<PasienResponse>() {
            @Override
            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {

                boolean error = response.body().isError();

                if (!error) {

                    Toast.makeText(ChangePasswordActivity.this, "Ubah password Berhasil!", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PasienResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChangePasswordActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void validassiUpdate() {

        passwordLama = mEdtPasswordLama.getText().toString();
        passwordBaru = mEdtPasswordBaru.getText().toString();
        konfirmasiPasswordBaru = mEdtKonfirmPasswordBaru.getText().toString();

        if (!passwordLama.equals("") || !passwordBaru.equals("") || !konfirmasiPasswordBaru.equals("")) {

            if (passwordLama.equals(dataPasswordLama)) {

                if (passwordBaru.equals(konfirmasiPasswordBaru)) {
                    updatePassword();
                } else {
                    Toast.makeText(this, "Password Baru Berbeda, Silahkan Periksa Lagi!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Password lama salah!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Data Tidak Boleh Kosong !!", Toast.LENGTH_SHORT).show();
        }

    }


}
