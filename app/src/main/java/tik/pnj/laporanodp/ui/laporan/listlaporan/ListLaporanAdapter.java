package tik.pnj.laporanodp.ui.laporan.listlaporan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import tik.pnj.laporanodp.R;

public class ListLaporanAdapter extends RecyclerView.Adapter<ListLaporanAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_laporan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvHari, mTvTanggal, mTvStatus;
        Button mBtnDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvHari = itemView.findViewById(R.id.tv_item_hari);
            mTvTanggal = itemView.findViewById(R.id.tv_item_date);
            mTvStatus = itemView.findViewById(R.id.tv_item_status);
            mBtnDetail = itemView.findViewById(R.id.btn_item_detail);
        }
    }

}
