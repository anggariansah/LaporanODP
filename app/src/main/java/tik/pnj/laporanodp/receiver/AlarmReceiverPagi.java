package tik.pnj.laporanodp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import tik.pnj.laporanodp.ui.DashboardActivity;
import tik.pnj.laporanodp.util.NotifBuilder;

public class AlarmReceiverPagi extends BroadcastReceiver {

    MediaPlayer player;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm aktif!", Toast.LENGTH_LONG).show();
//        player = MediaPlayer.create(context, R.raw.alarm);
//        player.start();

        NotifBuilder.showNotification(context, DashboardActivity.class,
                "Anda Belum Mengisi Laporan!", "Harap Isi Sekarang!");
    }







}
