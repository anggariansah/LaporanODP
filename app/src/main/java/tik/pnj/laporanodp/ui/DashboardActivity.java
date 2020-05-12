package tik.pnj.laporanodp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.ui.laporan.input.InsertLaporanActivity;
import tik.pnj.laporanodp.ui.laporan.listodp.ListOdpActivity;

public class DashboardActivity extends AppCompatActivity {

    private CardView cvLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cvLaporan = findViewById(R.id.cv_laporan);

        cvLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ListOdpActivity.class));
            }
        });
    }
}
