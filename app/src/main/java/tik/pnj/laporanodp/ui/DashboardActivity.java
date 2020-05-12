package tik.pnj.laporanodp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.ui.laporan.input.InsertLaporanActivity;
import tik.pnj.laporanodp.ui.laporan.listodp.ListOdpActivity;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener  {

    private CardView mCardProfile, mCardLaporan, mCardChangePassword, mCardAbout;

    private CardView cvLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

<<<<<<< HEAD
        cvLaporan = findViewById(R.id.cv_laporan);

        cvLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ListOdpActivity.class));
            }
        });
=======
        mCardProfile = findViewById(R.id.cv_profile);
        mCardLaporan = findViewById(R.id.cv_laporan);
        mCardChangePassword = findViewById(R.id.cv_ganti_password);
        mCardAbout = findViewById(R.id.cv_tentang);

        mCardProfile.setOnClickListener(this);
        mCardLaporan.setOnClickListener(this);
        mCardChangePassword.setOnClickListener(this);
        mCardAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_profile:

                break;

            case R.id.cv_laporan:

                break;

            case R.id.cv_ganti_password:

                Intent intentPassword = new Intent(DashboardActivity.this, ChangePasswordActivity.class);
                startActivity(intentPassword);

                break;

            case R.id.cv_tentang:

                break;
        }
>>>>>>> c4b1ecbb1a25fdf4d9d681102bb33993023593ee
    }
}
