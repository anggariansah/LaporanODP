package tik.pnj.laporanodp.ui.laporan.listlaporan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        int day = position + 1;
        SimpleDateFormat formatFromDb = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatFromDb.parse(model.getTanggal());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatDisplay = new SimpleDateFormat("EEEE, dd MMMM y");
        String dateConvert = formatDisplay.format(date);

        holder.mTvHari.setText(context.getResources().getString(R.string.laporan_ke, String.valueOf(day)));
        holder.mTvTanggal.setText(dateConvert);


    }

    @Override
    public int getItemCount() {
        return listLaporan.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvHari, mTvTanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvHari = itemView.findViewById(R.id.tv_item_hari);
            mTvTanggal = itemView.findViewById(R.id.tv_item_date);
        }
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
