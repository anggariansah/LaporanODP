package tik.pnj.laporanodp.data;

import com.google.gson.annotations.SerializedName;

public class LaporanEntity {

    @SerializedName("id_laporan")
    private String idLaporan;

    @SerializedName("id_odp")
    private String idOdp;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("demam")
    private String demam;

    @SerializedName("sesak")
    private String sesak;

    @SerializedName("nyeri_tenggorokan")
    private String nyeriTenggorokan;

    @SerializedName("batuk")
    private String batuk;

    @SerializedName("pilek")
    private String pilek;

    @SerializedName("diare")
    private String diare;

    public String getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(String idLaporan) {
        this.idLaporan = idLaporan;
    }

    public String getIdOdp() {
        return idOdp;
    }

    public void setIdOdp(String idOdp) {
        this.idOdp = idOdp;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDemam() {
        return demam;
    }

    public void setDemam(String demam) {
        this.demam = demam;
    }

    public String getSesak() {
        return sesak;
    }

    public void setSesak(String sesak) {
        this.sesak = sesak;
    }

    public String getNyeriTenggorokan() {
        return nyeriTenggorokan;
    }

    public void setNyeriTenggorokan(String nyeriTenggorokan) {
        this.nyeriTenggorokan = nyeriTenggorokan;
    }

    public String getBatuk() {
        return batuk;
    }

    public void setBatuk(String batuk) {
        this.batuk = batuk;
    }

    public String getPilek() {
        return pilek;
    }

    public void setPilek(String pilek) {
        this.pilek = pilek;
    }

    public String getDiare() {
        return diare;
    }

    public void setDiare(String diare) {
        this.diare = diare;
    }
}
