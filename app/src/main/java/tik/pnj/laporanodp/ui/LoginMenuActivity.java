package tik.pnj.laporanodp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tik.pnj.laporanodp.R;

public class LoginMenuActivity extends AppCompatActivity {

    Button btnLoginPasien, btnLoginPj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);

        btnLoginPasien = findViewById(R.id.btn_login_pasien);
        btnLoginPj = findViewById(R.id.btn_login_pj);

        btnLoginPj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMenuActivity.this, LoginPjActivity.class));

            }
        });

        btnLoginPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMenuActivity.this, LoginPasienActivity.class));
            }
        });
    }
}
