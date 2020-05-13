package tik.pnj.laporanodp.ui.profile;

import android.app.ProgressDialog;
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
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;
import tik.pnj.laporanodp.util.DummyData;

public class ProfileActivity extends AppCompatActivity {

    // widget
    private RecyclerView rvProfile;

    // vars
    ProfileAdapter adapter;
    private List<PasienEntity> listPasien;

    private ProgressDialog progressDialog;


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

        progressDialog = new ProgressDialog(this);


        getListProfile();

    }

    private ArrayList<PasienEntity> getDummyData() {
        return DummyData.getDummyPasien();
    }

    private void getListProfile() {

        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienEntity> getData = api.listPasien();
        getData.enqueue(new Callback<PasienEntity>() {
            @Override
            public void onResponse(Call<PasienEntity> call, Response<PasienEntity> response) {

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
            public void onFailure(Call<PasienEntity> call, Throwable t) {
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