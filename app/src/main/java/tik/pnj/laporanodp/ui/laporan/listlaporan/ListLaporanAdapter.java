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
import tik.pnj.laporanodp.data.LaporanEntity;

public class ListLaporanAdapter extends RecyclerView.Adapter<ListLaporanAdapter.ViewHolder> {

    Context context;
    private List<LaporanEntity> listLaporan;
    private ItemClickListener itemClickListener;

    public ListLaporanAdapter(Context context, List<LaporanEntity> listLaporan, ItemClickListener itemClickListener) {
        this.context = context;
        this.listLaporan = listLaporan;
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

        LaporanEntity model = listLaporan.get(position);

        int hari = position + 1;
        holder.mTvHari.setText(String.valueOf(hari));
        holder.mTvTanggal.setText(model.getTanggal());
        holder.mTvStatus.setText("SUDAH LAPOR");

        holder.mBtnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listLaporan.size();
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


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
