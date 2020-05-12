package tik.pnj.laporanodp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import tik.pnj.laporanodp.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener  {

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
    }
}
