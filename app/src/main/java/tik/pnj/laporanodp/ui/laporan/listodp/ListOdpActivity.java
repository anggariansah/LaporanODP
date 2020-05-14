package tik.pnj.laporanodp.ui.laporan.listodp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
import tik.pnj.laporanodp.ui.laporan.input.InsertLaporanActivity;
import tik.pnj.laporanodp.util.DummyData;

public class ListOdpActivity extends AppCompatActivity {

    // widget
    private RecyclerView mRvOdp;

    // vars
    private ListOdpAdapter adapter;
    private List<PasienEntity> listPasien;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_odp);

        //casting widget
        mRvOdp = findViewById(R.id.rv_list_odp);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Daftar ODP");
        }

        progressDialog = new ProgressDialog(this);

        getListOdp();

    }

    private ArrayList<PasienEntity> getDummyData() {
        return DummyData.getDummyPasien();
    }

    private void getListOdp() {

        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<PasienResponse> getData = api.listOdp();
        getData.enqueue(new Callback<PasienResponse>() {
            @Override
            public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {

                boolean error = response.body().isError();

                if (!error) {

                    listPasien = response.body().getListPasien();

                    setAdapter();

                } else {
                    Toast.makeText(ListOdpActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PasienResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListOdpActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setAdapter(){
        mRvOdp.setLayoutManager(new LinearLayoutManager(this));
        mRvOdp.setHasFixedSize(true);
        adapter = new ListOdpAdapter(ListOdpActivity.this, listPasien, new ListOdpAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ListOdpActivity.this, listPasien.get(position).getNama(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListOdpActivity.this, InsertLaporanActivity.class);
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
