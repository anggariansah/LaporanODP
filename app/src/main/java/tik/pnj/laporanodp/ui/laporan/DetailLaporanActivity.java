package tik.pnj.laporanodp.ui.laporan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.LaporanEntity;

public class DetailLaporanActivity extends AppCompatActivity {

    // widget
    private TextView tvName, tvDate, tvSesak, tvNyeri, tvBatuk, tvPilek, tvDiare, tvSuhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);

        tvName = findViewById(R.id.tv_name);
        tvDate = findViewById(R.id.tv_date);
        tvSesak = findViewById(R.id.tv_sesak);
        tvNyeri = findViewById(R.id.tv_nyeri);
        tvBatuk = findViewById(R.id.tv_batuk);
        tvPilek = findViewById(R.id.tv_pilek);
        tvDiare = findViewById(R.id.tv_diare);
        tvSuhu = findViewById(R.id.tv_suhu);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detail Laporan");
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            LaporanEntity data = extras.getParcelable("data");
            tvName.setText(extras.getString("name"));
            if (data != null) {
                setDataToView(data);
            } else {
                Toast.makeText(this, "Data tidak ada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setDataToView(LaporanEntity data) {
        SimpleDateFormat formatFromDb = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatFromDb.parse(data.getTanggal());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatDisplay = new SimpleDateFormat("EEEE, dd MMMM y");
        String dateConvert = formatDisplay.format(date);


        tvDate.setText(dateConvert);
        tvDiare.setText(data.getDiare());
        tvPilek.setText(data.getPilek());
        tvBatuk.setText(data.getBatuk());
        tvNyeri.setText(data.getNyeriTenggorokan());
        tvSesak.setText(data.getSesak());
        tvSuhu.setText(getResources().getString(R.string.suhu, data.getSuhu()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}