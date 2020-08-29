package tik.pnj.laporanodp.ui.laporan.listodp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.data.PasienResponse;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;
import tik.pnj.laporanodp.ui.laporan.listlaporan.ListLaporanActivity;
import tik.pnj.laporanodp.util.UserPreference;

public class ListOdpPasienActivity extends AppCompatActivity {

    // widget
    private RecyclerView mRvOdp;

    // vars
    private ListOdpAdapter adapter;
    private List<PasienEntity> listPasien;
    private UserPreference preference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_odp_pasien);


        //casting widget
        mRvOdp = findViewById(R.id.rv_list_odp);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Daftar Pasien");
        }

        preference = new UserPreference(this);
        progressDialog = new ProgressDialog(this);

        String idkasus = preference.getUserId();

        getListOdp(idkasus);
    }

    private void getListOdp(String idkasus) {

        progressDialog.setMessage("Harap Tunggu");
        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienResponse> getData = api.listOdpPasien(idkasus);
        getData.enqueue(new Callback<PasienResponse>() {
            @Override
            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {

                boolean error = response.body().isError();

                if (!error) {

                    listPasien = response.body().getListPasien();

                    setAdapter();

                } else {
                    Toast.makeText(ListOdpPasienActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PasienResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListOdpPasienActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setAdapter() {
        mRvOdp.setLayoutManager(new LinearLayoutManager(this));
        mRvOdp.setHasFixedSize(true);
        adapter = new ListOdpAdapter(ListOdpPasienActivity.this, listPasien, new ListOdpAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ListOdpPasienActivity.this, ListLaporanActivity.class);
                intent.putExtra("id", listPasien.get(position).getId());
                intent.putExtra("name", listPasien.get(position).getNama());
                startActivity(intent);
            }
        });
        mRvOdp.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
