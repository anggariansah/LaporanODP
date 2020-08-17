package tik.pnj.laporanodp.ui.laporan.listlaporan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.LaporanEntity;
import tik.pnj.laporanodp.data.LaporanResponse;
import tik.pnj.laporanodp.data.PasienResponse;
import tik.pnj.laporanodp.network.ApiRequest;
import tik.pnj.laporanodp.network.RetrofitServer;
import tik.pnj.laporanodp.ui.laporan.input.InsertLaporanActivity;

public class ListLaporanActivity extends AppCompatActivity {

    // widget
    private TextView mTvNama;
    private RecyclerView mRvLaporan;
    private FloatingActionButton mFabAdd;

    // vars
    private ListLaporanAdapter adapter;
    private List<LaporanEntity> listLaporan;
    private ProgressDialog progressDialog;
    private String idOdp, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_laporan);

        //casting widget
        mTvNama = findViewById(R.id.tv_nama);
        mRvLaporan = findViewById(R.id.rv_list_laporan);
        mFabAdd = findViewById(R.id.fab_add);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Laporan");
        }


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idOdp = extras.getString("id");
            nama = extras.getString("name");
            mTvNama.setText(nama);
        }

        progressDialog = new ProgressDialog(this);

        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListLaporanActivity.this, InsertLaporanActivity.class);
                intent.putExtra("id", idOdp);
                intent.putExtra("name", nama);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListLaporan(idOdp);
    }

    private void getListLaporan(String idOdp) {

        progressDialog.setMessage("Harap Tunggu");
        progressDialog.show();
        progressDialog.setCancelable(false);

        ApiRequest api = RetrofitServer.getClient().create(ApiRequest.class);
        Call<LaporanResponse> getData = api.listLaporan(idOdp);
        getData.enqueue(new Callback<LaporanResponse>() {
            @Override
            public void onResponse(Call<LaporanResponse> call, Response<LaporanResponse> response) {

                boolean error = response.body().isError();

                if (!error) {

                    listLaporan = response.body().getListLaporan();

                    setAdapter();

                } else {
                    Toast.makeText(ListLaporanActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LaporanResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListLaporanActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setAdapter() {
        mRvLaporan.setLayoutManager(new LinearLayoutManager(this));
        mRvLaporan.setHasFixedSize(true);
        adapter = new ListLaporanAdapter(ListLaporanActivity.this, listLaporan, new ListLaporanAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(ListLaporanActivity.this, InsertLaporanActivity.class);
//                intent.putExtra("id", listPasien.get(position).getId());
//                startActivity(intent);
            }
        });
        mRvLaporan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
