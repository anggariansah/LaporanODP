package tik.pnj.laporanodp.data;

import com.google.gson.annotations.SerializedName;

public class PasienEntity {

    @SerializedName("id_kasus")
    private String id;

    @SerializedName("no_kk")
    private String nomorKK;

    @SerializedName("nik")
    private String nik;

    @SerializedName("nama")
    private String nama;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("tgl_lahir")
    private String tglLahir;

    @SerializedName("status_kk")
    private String statusKK;

    @SerializedName("jk")
    private String jenkel;

    @SerializedName("password")
    private String password;

    @SerializedName("day")
    private int day;

    @SerializedName("date")
    private int date;

    public PasienEntity() {
    }

    public PasienEntity(String nomorKK, String nik, String nama, String alamat, String tglLahir) {
        this.nomorKK = nomorKK;
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.tglLahir = tglLahir;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomorKK() {
        return nomorKK;
    }

    public void setNomorKK(String nomorKK) {
        this.nomorKK = nomorKK;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getStatusKK() {
        return statusKK;
    }

    public void setStatusKK(String statusKK) {
        this.statusKK = statusKK;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }
}
