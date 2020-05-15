package tik.pnj.laporanodp.ui.laporan.listlaporan;

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
import tik.pnj.laporanodp.ui.laporan.input.InsertLaporanActivity;
import tik.pnj.laporanodp.util.UserPreference;

public class ListLaporanActivity extends AppCompatActivity {

    // widget
    private RecyclerView mRvLaporan;

    // vars
    private ListLaporanAdapter adapter;
    private List<PasienEntity> listPasien;
    private UserPreference preference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_laporan);

        //casting widget
        mRvLaporan = findViewById(R.id.rv_list_odp);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Laporan");
        }

        preference = new UserPreference(this);
        progressDialog = new ProgressDialog(this);

        String nomorKK = preference.getNomorKK();

        getListLaporan(nomorKK);
    }

    private void getListLaporan(String nomorKK) {

        progressDialog.setMessage("Harap Tunggu");
        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienResponse> getData = api.listLaporan(nomorKK);
        getData.enqueue(new Callback<PasienResponse>() {
            @Override
            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {

                boolean error = response.body().isError();

                if (!error) {

                    listPasien = response.body().getListPasien();

                    setAdapter();

                } else {
                    Toast.makeText(ListLaporanActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PasienResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListLaporanActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setAdapter() {
        mRvLaporan.setLayoutManager(new LinearLayoutManager(this));
        mRvLaporan.setHasFixedSize(true);
        adapter = new ListLaporanAdapter(ListLaporanActivity.this, listPasien, new ListLaporanAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ListLaporanActivity.this, InsertLaporanActivity.class);
                intent.putExtra("id", listPasien.get(position).getId());
                startActivity(intent);
            }
        });
        mRvLaporan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
