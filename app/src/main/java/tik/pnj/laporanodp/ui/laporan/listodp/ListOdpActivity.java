package tik.pnj.laporanodp.ui.laporan.listodp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.ui.laporan.input.InsertLaporanActivity;
import tik.pnj.laporanodp.util.DummyData;

public class ListOdpActivity extends AppCompatActivity {

    // widget
    private RecyclerView mRvOdp;

    // vars
    private ListOdpAdapter adapter;
    private List<PasienEntity> listPasien;

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

        listPasien = getDummyData();

        mRvOdp.setLayoutManager(new LinearLayoutManager(this));
        mRvOdp.setHasFixedSize(true);
        adapter = new ListOdpAdapter(ListOdpActivity.this, listPasien, new ListOdpAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ListOdpActivity.this, listPasien.get(position).getNama(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListOdpActivity.this, InsertLaporanActivity.class);
                intent.putExtra("name", listPasien.get(position).getNama());
                startActivity(intent);
            }
        });
        mRvOdp.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private ArrayList<PasienEntity> getDummyData() {
        return DummyData.getDummyPasien();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
