package tik.pnj.laporanodp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import tik.pnj.laporanodp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // widget
    TextInputEditText mEdtKtp, mEdtPassword;
    Button mBtnLogin;

    // vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // casting widget
        mEdtKtp = findViewById(R.id.text_input_edit_ktp);
        mEdtPassword = findViewById(R.id.text_input_edit_password);
        mBtnLogin = findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginSuccess();
                break;
        }
    }

    private void verify() {

    }

    private void loginSuccess() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish();
    }
}
