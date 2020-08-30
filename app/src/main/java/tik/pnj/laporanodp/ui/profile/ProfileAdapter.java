package tik.pnj.laporanodp.ui.profile;

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

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private List<PasienEntity> listPasien;
    private OnItemClickListener mOnItemClickListener;

    public ProfileAdapter(Context context, List<PasienEntity> listPasien, OnItemClickListener mOnItemClickListener) {
        this.context = context;
        this.listPasien = listPasien;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        PasienEntity model = listPasien.get(position);

        holder.mTvNomorKtp.setText(model.getNik());
        holder.mTvNama.setText(model.getNama());
        holder.mTvTglLahir.setText(model.getTglLahir());

        if(model.getJenkel().equals("L")){
            holder.mIvJenkel.setImageResource(R.drawable.icon_user_boy);
        }else{
            holder.mIvJenkel.setImageResource(R.drawable.icon_user_girl);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPasien.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvNomorKtp, mTvNama, mTvTglLahir;
        ImageView mIvJenkel;
        View container;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView;

            mTvNomorKtp = itemView.findViewById(R.id.tv_item_ktp);
            mTvNama = itemView.findViewById(R.id.tv_item_nama);
            mTvTglLahir = itemView.findViewById(R.id.tv_item_tgl_lahir);
            mIvJenkel = itemView.findViewById(R.id.iv_jenkel);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int posisition);
    }
}
