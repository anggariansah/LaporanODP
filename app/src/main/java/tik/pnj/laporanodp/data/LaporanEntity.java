package tik.pnj.laporanodp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LaporanEntity implements Parcelable {

    @SerializedName("id_laporan")
    private String idLaporan;

    @SerializedName("id_odp")
    private String idOdp;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("demam")
    private String demam;

    @SerializedName("sesak_napas")
    private String sesak;

    @SerializedName("sakit_tgg")
    private String nyeriTenggorokan;

    @SerializedName("batuk")
    private String batuk;

    @SerializedName("pilek")
    private String pilek;

    @SerializedName("diare")
    private String diare;

    @SerializedName("suhu_demam")
    private String suhu;

    protected LaporanEntity(Parcel in) {
        idLaporan = in.readString();
        idOdp = in.readString();
        tanggal = in.readString();
        demam = in.readString();
        sesak = in.readString();
        nyeriTenggorokan = in.readString();
        batuk = in.readString();
        pilek = in.readString();
        diare = in.readString();
        suhu = in.readString();
    }

    public static final Creator<LaporanEntity> CREATOR = new Creator<LaporanEntity>() {
        @Override
        public LaporanEntity createFromParcel(Parcel in) {
            return new LaporanEntity(in);
        }

        @Override
        public LaporanEntity[] newArray(int size) {
            return new LaporanEntity[size];
        }
    };

    public String getIdLaporan() {
        return idLaporan;
    }

    public String getIdOdp() {
        return idOdp;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getDemam() {
        return demam;
    }

    public String getSesak() {
        return sesak;
    }

    public String getNyeriTenggorokan() {
        return nyeriTenggorokan;
    }

    public String getBatuk() {
        return batuk;
    }

    public String getPilek() {
        return pilek;
    }

    public String getDiare() {
        return diare;
    }

    public String getSuhu() {
        return suhu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idLaporan);
        dest.writeString(idOdp);
        dest.writeString(tanggal);
        dest.writeString(demam);
        dest.writeString(sesak);
        dest.writeString(nyeriTenggorokan);
        dest.writeString(batuk);
        dest.writeString(pilek);
        dest.writeString(diare);
        dest.writeString(suhu);
    }
}
