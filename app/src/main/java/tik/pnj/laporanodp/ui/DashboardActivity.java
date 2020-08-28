package tik.pnj.laporanodp.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.NotifResponse;
import tik.pnj.laporanodp.data.PasienResponse;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;
import tik.pnj.laporanodp.receiver.AlarmReceiverPagi;
import tik.pnj.laporanodp.receiver.AlarmReceiverSiang;
import tik.pnj.laporanodp.ui.laporan.listodp.ListOdpActivity;
import tik.pnj.laporanodp.ui.profile.ProfileActivity;
import tik.pnj.laporanodp.util.UserPreference;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    CardView mCardProfile, mCardLaporan, mCardChangePassword, mCardAbout;
    private Toolbar mToolbar;

    final static int RQS_1 = 1;

    private ProgressDialog progressDialog;

    private List<PasienResponse> listPasien;

    private UserPreference preference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mCardProfile = findViewById(R.id.cv_profile);
        mCardLaporan = findViewById(R.id.cv_laporan);
        mCardChangePassword = findViewById(R.id.cv_ganti_password);
        mCardAbout = findViewById(R.id.cv_tentang);
        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        mCardProfile.setOnClickListener(this);
        mCardLaporan.setOnClickListener(this);
        mCardChangePassword.setOnClickListener(this);
        mCardAbout.setOnClickListener(this);

        preference = new UserPreference(this);
        progressDialog = new ProgressDialog(this);


        String idkasus = preference.getUserId();


        setTimeAlarm();


        getStatus(idkasus);
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
                startActivity(new Intent(DashboardActivity.this, ChangePasswordActivity.class));
                break;

            case R.id.cv_tentang:
                startActivity(new Intent(DashboardActivity.this, AboutActivity.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout :
                preference.deleteLoginSession();

                Intent intent = new Intent(DashboardActivity.this, LoginMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                break;
        }
        return true;
    }


    private void setTimeAlarm(){

        Calendar calNow = Calendar.getInstance();
        Calendar calPagi = (Calendar) calNow.clone();
        Calendar calSiang = (Calendar) calNow.clone();

        calPagi.set(Calendar.HOUR_OF_DAY, 9);
        calPagi.set(Calendar.MINUTE, 0);
        calPagi.set(Calendar.SECOND, 0);
        calPagi.set(Calendar.MILLISECOND, 0);

        calSiang.set(Calendar.HOUR_OF_DAY, 13);
        calSiang.set(Calendar.MINUTE, 0);
        calSiang.set(Calendar.SECOND, 0);
        calSiang.set(Calendar.MILLISECOND, 0);


        if (calPagi.compareTo(calNow) <= 0) {
            // Today Set time passed, count to tomorrow
            calPagi.add(Calendar.DATE, 1);
            Log.i("hasil", " =<0");
        } else if (calPagi.compareTo(calNow) > 0) {
            Log.i("hasil", " > 0");
        } else {
            Log.i("hasil", " else ");
        }


        if (calSiang.compareTo(calNow) <= 0) {
            // Today Set time passed, count to tomorrow
            calSiang.add(Calendar.DATE, 1);
            Log.i("hasil", " =<0");
        } else if (calSiang.compareTo(calNow) > 0) {
            Log.i("hasil", " > 0");
        } else {
            Log.i("hasil", " else ");
        }

        setAlarmPagi(calPagi);
        setAlarmSiang(calSiang);
    }


    private void setAlarmPagi(Calendar targetCal) {

        Intent intent = new Intent(getBaseContext(), AlarmReceiverPagi.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }


    private void setAlarmSiang(Calendar targetCal) {


        Intent intent = new Intent(getBaseContext(), AlarmReceiverSiang.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }

    private void getStatus(String nikpj) {

        progressDialog.setMessage("Harap Tunggu");
        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<NotifResponse> getData = api.statusNotifPj(nikpj);
        getData.enqueue(new Callback<NotifResponse>() {
            @Override
            public void onResponse(Call<NotifResponse> call, Response<NotifResponse> response) {

                boolean error = response.body().isError();
                boolean status = response.body().isStatusNotif();

                if (!error) {

                    Toast.makeText(DashboardActivity.this, "Status = "+status, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(DashboardActivity.this, "Alarm Set", Toast.LENGTH_SHORT).show();
                    setTimeAlarm();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<NotifResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DashboardActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

