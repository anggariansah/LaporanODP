package tik.pnj.laporanodp.ui.laporan.input;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;

public class InsertLaporanActivity extends AppCompatActivity implements View.OnClickListener {

    // widget
    private Button mBtnKirim, mBtnBatal;
    private RadioGroup mRgDemam, mRgSesak, mRgNyeri, mRgBatuk, mRgPilek, mRgDiare;
    private TextView mTvTodayDate, mTvName;

    // vars
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String todayDate, noKtp;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_laporan);

        //casting widget
        mTvTodayDate = findViewById(R.id.tv_today_date);
        mTvName = findViewById(R.id.tv_name);
        mBtnKirim = findViewById(R.id.btn_kirim);
        mBtnBatal = findViewById(R.id.btn_batal);
        mRgDemam = findViewById(R.id.radio_group_demam);
        mRgSesak = findViewById(R.id.radio_group_sesak);
        mRgNyeri = findViewById(R.id.radio_group_nyeri);
        mRgBatuk = findViewById(R.id.radio_group_batuk);
        mRgPilek = findViewById(R.id.radio_group_pilek);
        mRgDiare = findViewById(R.id.radio_group_diare);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEEE, d MMM yyyy");
        todayDate = dateFormat.format(calendar.getTime());
        mTvTodayDate.setText(todayDate);

        progressDialog = new ProgressDialog(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mTvName.setText(extras.getString("name"));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Laporan Harian");
        }

        mBtnKirim.setOnClickListener(this);
        mBtnBatal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_kirim:
                checkSelectedRadio();
                break;
            case R.id.btn_batal:
                finish();
                break;
        }
    }

    private void checkSelectedRadio() {
        int idDemam = mRgDemam.getCheckedRadioButtonId();
        int idSesak = mRgSesak.getCheckedRadioButtonId();
        int idNyeri = mRgNyeri.getCheckedRadioButtonId();
        int idBatuk = mRgBatuk.getCheckedRadioButtonId();
        int idPilek = mRgPilek.getCheckedRadioButtonId();
        int idDiare = mRgDiare.getCheckedRadioButtonId();

        if (idDemam != -1 && idSesak != -1 && idNyeri != -1 && idBatuk != -1 && idPilek != -1 && idDiare != -1) {
            RadioButton rbDemam = findViewById(idDemam);
            RadioButton rbSesak = findViewById(idSesak);
            RadioButton rbNyeri = findViewById(idNyeri);
            RadioButton rbBatuk = findViewById(idBatuk);
            RadioButton rbPilek = findViewById(idPilek);
            RadioButton rbDiare = findViewById(idDiare);

            String[] data = new String[]{
                    rbDemam.getText().toString(),
                    rbSesak.getText().toString(),
                    rbNyeri.getText().toString(),
                    rbBatuk.getText().toString(),
                    rbPilek.getText().toString(),
                    rbDiare.getText().toString()
            };

            saveData(data);

        } else {
            Toast.makeText(this, "Harap Lengkapi Data !", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveData(String[] data) {
        String timeStamp = new Timestamp(System.currentTimeMillis()).toString();

        progressDialog.setMessage("Harap Tunggu ..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienEntity> login = api.insertLaporan(noKtp, data[0], data[1], data[2], data[3], data[4], data[5]);
        login.enqueue(new Callback<PasienEntity>() {
            @Override
            public void onResponse(Call<PasienEntity> call, Response<PasienEntity> response) {
                progressDialog.hide();

                boolean error = response.body().isError();

                if (error == false) {

                    Toast.makeText(InsertLaporanActivity.this, "Insert Sukses!!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(InsertLaporanActivity.this, "Insert failed!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PasienEntity> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(InsertLaporanActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
