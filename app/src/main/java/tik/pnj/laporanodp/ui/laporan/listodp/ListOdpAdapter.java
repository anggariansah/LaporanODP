package tik.pnj.laporanodp.ui.laporan.listodp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tik.pnj.laporanodp.R;
import tik.pnj.laporanodp.data.PasienEntity;

public class ListOdpAdapter extends RecyclerView.Adapter<ListOdpAdapter.ViewHolder> {

    Context context;
    private List<PasienEntity> listPasien;
    private ItemClickListener itemClickListener;

    public ListOdpAdapter(Context context, List<PasienEntity> listPasien, ItemClickListener itemClickListener) {
        this.context = context;
        this.listPasien = listPasien;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_odp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        PasienEntity model = listPasien.get(position);

        holder.mTvNik.setText(model.getNik());
        holder.mTvNama.setText(model.getNama());

        if(model.getJenkel().equals("L")){
            holder.mIvJenkel.setImageResource(R.drawable.icon_user_boy);
        }else{
            holder.mIvJenkel.setImageResource(R.drawable.icon_user_girl);
        }

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

        TextView mTvNik, mTvNama;
        ImageView mIvJenkel;
        View container;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView;

            mTvNik = itemView.findViewById(R.id.tv_nik);
            mTvNama = itemView.findViewById(R.id.tv_nama);
            mIvJenkel = itemView.findViewById(R.id.iv_jenkel);
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
