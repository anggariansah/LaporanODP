package tik.pnj.laporanodp.ui.profile.update;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;

public class UpdateProfileActivity extends AppCompatActivity {

    TextInputEditText mEdtNoKk, mEdtNoKtp, mEdtNama, mEdtAlamat;
    Button mButtonUpdate;
    RadioButton mRadioLaki, mRadioPerempuan;

    String id, jenisKelamin;

    private ProgressDialog progressDialog;

    private List<PasienEntity> listPasien;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
        }

        mEdtNoKk = findViewById(R.id.text_input_edit_nokk);
        mEdtNoKtp = findViewById(R.id.text_inpute_edit_no_ktp);
        mEdtNama = findViewById(R.id.text_input_edit_nama);
        mEdtAlamat = findViewById(R.id.text_input_edit_alamat);
        mButtonUpdate = findViewById(R.id.btn_update);
        mRadioLaki = findViewById(R.id.radio_laki);
        mRadioPerempuan = findViewById(R.id.radio_perempuan);

        progressDialog = new ProgressDialog(this);

        getListProfile(id);

    }

    private void getListProfile(String id) {

        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienEntity> getData = api.detailProfile(id);
        getData.enqueue(new Callback<PasienEntity>() {
            @Override
            public void onResponse(Call<PasienEntity> call, Response<PasienEntity> response) {

                boolean error = response.body().isError();

                if (!error) {

                    listPasien = response.body().getListPasien();

                    setData();

                } else {
                    Toast.makeText(UpdateProfileActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PasienEntity> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateProfileActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void setData(){
        PasienEntity pasien = listPasien.get(0);

        mEdtNoKk.setText(pasien.getNomorKK());
        mEdtNoKtp.setText(pasien.getNomorKTP());
        mEdtNama.setText(pasien.getNama());
        mEdtAlamat.setText(pasien.getAlamat());

        jenisKelamin = pasien.getJenisKelamin();

        if(jenisKelamin.equals("Perempuan")){
            mRadioPerempuan.setChecked(true);
            mRadioLaki.setChecked(false);
        }else {
            mRadioPerempuan.setChecked(false);
            mRadioLaki.setChecked(true);
        }
    }
}
