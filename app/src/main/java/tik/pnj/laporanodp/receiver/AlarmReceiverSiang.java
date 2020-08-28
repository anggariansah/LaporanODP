package tik.pnj.laporanodp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import tik.pnj.laporanodp.ui.DashboardActivity;
import tik.pnj.laporanodp.util.NotifBuilder;

public class AlarmReceiverSiang extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        NotifBuilder.showNotification(context, DashboardActivity.class,
                "Anda Belum Mengisi Laporan!", "Harap Isi Sekarang!");
    }
}
