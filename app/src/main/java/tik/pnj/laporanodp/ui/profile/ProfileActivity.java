package tik.pnj.laporanodp.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.util.DummyData;

public class ProfileActivity extends AppCompatActivity {

    // widget
    private RecyclerView rvProfile;

    // vars
    private ProfileAdapter adapter;
    private List<PasienEntity> listPasien;

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

        listPasien = getDummyData();

        rvProfile.setLayoutManager(new LinearLayoutManager(this));
        rvProfile.setHasFixedSize(true);

        adapter = new ProfileAdapter(ProfileActivity.this, listPasien);
        adapter.notifyDataSetChanged();

        rvProfile.setAdapter(adapter);
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
