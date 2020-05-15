package tik.pnj.laporanodp.ui.laporan.listlaporan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;
import tik.pnj.laporanodp.ui.laporan.listodp.ListOdpAdapter;

public class ListLaporanAdapter extends RecyclerView.Adapter<ListLaporanAdapter.ViewHolder> {

    Context context;
    private List<PasienEntity> listPasien;
    private ItemClickListener itemClickListener;

    public ListLaporanAdapter(Context context, List<PasienEntity> listPasien, ItemClickListener itemClickListener) {
        this.context = context;
        this.listPasien = listPasien;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_laporan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        PasienEntity model = listPasien.get(position);


        holder.mTvHari.setText(model.getDay());
        holder.mTvTanggal.setText(model.getDate());
        holder.mTvStatus.setText(model.getStatusKK());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPasien.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvHari, mTvTanggal, mTvStatus;
        Button mBtnDetail;
        View container;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView;

            mTvHari = itemView.findViewById(R.id.tv_item_hari);
            mTvTanggal = itemView.findViewById(R.id.tv_item_date);
            mTvStatus = itemView.findViewById(R.id.tv_item_status);
            mBtnDetail = itemView.findViewById(R.id.btn_item_detail);
        }
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
