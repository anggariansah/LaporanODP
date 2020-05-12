package tik.pnj.laporanodp.ui.laporan.input;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Locale;

import tik.pnj.laporanodp.R;

public class InsertLaporanActivity extends AppCompatActivity implements View.OnClickListener {

    // widget
    private Button mBtnKirim, mBtnBatal;
    private RadioGroup mRgDemam, mRgSesak, mRgNyeri, mRgBatuk, mRgPilek, mRgDiare;
    private TextView mTvTodayDate, mTvName;

    // vars
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String todayDate;

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

        Toast.makeText(this,
                data[0] + "\n" +
                        data[1] + "\n" +
                        data[2] + "\n" +
                        data[3] + "\n" +
                        data[4] + "\n" +
                        data[5] + "\n" +
                        timeStamp
                , Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
