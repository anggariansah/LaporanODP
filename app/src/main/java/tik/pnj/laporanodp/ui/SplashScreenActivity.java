package tik.pnj.laporanodp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.util.UserPreference;

public class SplashScreenActivity extends AppCompatActivity {

    private UserPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        preference = new UserPreference(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkSession();
            }
        }, 3000);

    }

    private void checkSession() {

        Intent intent;

        if (preference.isUserLoggedIn()) {
            if(preference.getUserRole().equals("pasien")){
                intent = new Intent(SplashScreenActivity.this, DashboardPasienActivity.class);
            }else{
                intent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
            }
        } else {
            intent = new Intent(SplashScreenActivity.this, LoginMenuActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
