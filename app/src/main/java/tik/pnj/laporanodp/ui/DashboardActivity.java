package tik.pnj.laporanodp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.ui.laporan.listodp.ListOdpActivity;
import tik.pnj.laporanodp.ui.profile.ProfileActivity;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView mCardProfile, mCardLaporan, mCardChangePassword, mCardAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                break;

            case R.id.cv_laporan:
                startActivity(new Intent(DashboardActivity.this, ListOdpActivity.class));
                break;

            case R.id.cv_ganti_password:

                Intent intentPassword = new Intent(DashboardActivity.this, ChangePasswordActivity.class);
                startActivity(intentPassword);

                break;

            case R.id.cv_tentang:

                break;
        }
    }
}
