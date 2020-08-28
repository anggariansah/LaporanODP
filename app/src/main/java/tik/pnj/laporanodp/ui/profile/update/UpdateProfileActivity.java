package tik.pnj.laporanodp.ui.profile.update;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.data.PasienResponse;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;
import tik.pnj.laporanodp.util.UserPreference;

public class UpdateProfileActivity extends AppCompatActivity {

    TextInputEditText  mEdtNoKtp, mEdtNama, mEdtAlamat, mEdtTglLahir;
    Button mButtonUpdate, mBtnTgl;
    RadioButton mRadioLaki, mRadioPerempuan;

    String id, noKTP, nama, alamat, jenisKelamin, tglLahir;

    private ProgressDialog progressDialog;
    private UserPreference preference;

    private List<PasienEntity> listPasien;

    Calendar dateNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        preference = new UserPreference(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
        }else{
            id = preference.getUserId();
        }

        mEdtNoKtp = findViewById(R.id.text_inpute_edit_no_ktp);
        mEdtNama = findViewById(R.id.text_input_edit_nama);
        mEdtAlamat = findViewById(R.id.text_input_edit_alamat);
        mEdtTglLahir = findViewById(R.id.text_input_edit_tgl_lahir);
        mButtonUpdate = findViewById(R.id.btn_update);
        mBtnTgl = findViewById(R.id.btn_tgl);
        mRadioLaki = findViewById(R.id.radio_laki);
        mRadioPerempuan = findViewById(R.id.radio_perempuan);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Update Data Pasien");
        }

        dateNow = Calendar.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Harap Tunggu");

        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validationUpdate();
            }
        });

        mBtnTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        getListProfile(id);

    }

    private void getListProfile(String id) {

        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienResponse> getData = api.detailProfile(id);
        getData.enqueue(new Callback<PasienResponse>() {
            @Override
            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {

                boolean error = response.body().isError();

                if (!error) {

                    listPasien = response.body().getListDataPasien();

                    setData();

                } else {
                    Toast.makeText(UpdateProfileActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PasienResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateProfileActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void setData() {
        PasienEntity pasien = listPasien.get(0);

        mEdtNoKtp.setText(pasien.getNik());
        mEdtNama.setText(pasien.getNama());
        mEdtAlamat.setText(pasien.getAlamat());
        mEdtTglLahir.setText(pasien.getTglLahir());

        jenisKelamin = pasien.getJenkel();

        if (jenisKelamin.equals("P")) {
            mRadioPerempuan.setChecked(true);
            mRadioLaki.setChecked(false);
        } else {
            mRadioPerempuan.setChecked(false);
            mRadioLaki.setChecked(true);
        }
    }


    private void updateProfile() {

        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienResponse> getData = api.updateProfile(id, noKTP, nama, alamat, jenisKelamin, tglLahir);
        getData.enqueue(new Callback<PasienResponse>() {
            @Override
            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {

                boolean error = response.body().isError();

                if (!error) {

                    Toast.makeText(UpdateProfileActivity.this, "Update Data Berhasil!", Toast.LENGTH_SHORT).show();
                    finish();


                } else {
                    Toast.makeText(UpdateProfileActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PasienResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateProfileActivity.this, "Gagal Mengupdate data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validationUpdate() {
        noKTP = mEdtNoKtp.getText().toString();
        nama = mEdtNama.getText().toString();
        alamat = mEdtAlamat.getText().toString();
        tglLahir = mEdtTglLahir.getText().toString();

        if (mRadioLaki.isChecked())
            jenisKelamin = "L";
        else
            jenisKelamin = "P";

        if (noKTP.equals("") || nama.equals("") || alamat.equals("") || jenisKelamin.equals("") || tglLahir.equals("")) {
            Toast.makeText(UpdateProfileActivity.this, "Data Tidak Boleh Kosong!!", Toast.LENGTH_SHORT).show();
        } else {
            updateProfile();
        }
    }

    private void showDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar dateSelected = Calendar.getInstance();
                dateSelected.set(Calendar.YEAR, year);
                dateSelected.set(Calendar.MONTH, month);
                dateSelected.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                mEdtTglLahir.setText(sdf.format(dateSelected.getTime()));
            }
        };

        new DatePickerDialog(this, dateSetListener,
                dateNow.get(Calendar.YEAR),
                dateNow.get(Calendar.MONTH),
                dateNow.get(Calendar.DAY_OF_MONTH)).show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
