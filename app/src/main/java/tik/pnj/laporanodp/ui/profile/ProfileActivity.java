package tik.pnj.laporanodp.ui.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.data.PasienResponse;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;
import tik.pnj.laporanodp.ui.profile.update.UpdateProfileActivity;
import tik.pnj.laporanodp.util.DummyData;
import tik.pnj.laporanodp.util.UserPreference;

public class ProfileActivity extends AppCompatActivity {

    // widget
    private RecyclerView rvProfile;

    // vars
    ProfileAdapter adapter;
    private List<PasienEntity> listPasien;
    private UserPreference preference;
    private ProgressDialog progressDialog;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Profile Pasien");
        }

        // casting
        rvProfile = findViewById(R.id.rv_profile);

        preference = new UserPreference(this);
        progressDialog = new ProgressDialog(this);

        id = preference.getUserId();


    }

    private ArrayList<PasienEntity> getDummyData() {
        return DummyData.getDummyPasien();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListProfile(id);
    }

    private void getListProfile(String nomorKK) {

        progressDialog.setMessage("Harap Tunggu");
        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienResponse> getData = api.listOdp(nomorKK);
        getData.enqueue(new Callback<PasienResponse>() {
            @Override
            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {

                boolean error = response.body().isError();

                if (!error) {

                    listPasien = response.body().getListPasien();

                    setAdapter();

                } else {
                    Toast.makeText(ProfileActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PasienResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ProfileActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setAdapter(){
        rvProfile.setLayoutManager(new LinearLayoutManager(this));
        rvProfile.setHasFixedSize(true);

        adapter = new ProfileAdapter(ProfileActivity.this, listPasien, new ProfileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                PasienEntity pasien = listPasien.get(position);

                String id = pasien.getId();

                Intent intentUpdate = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                intentUpdate.putExtra("id", id);
                startActivity(intentUpdate);

            }
        });

        adapter.notifyDataSetChanged();
        rvProfile.setAdapter(adapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
