package tik.pnj.laporanodp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PasienEntity {

    @SerializedName("id")
    private String id;

    @SerializedName("no_kk")
    private String nomorKK;

    @SerializedName("no_ktp")
    private String nomorKTP;

    @SerializedName("nama_lengkap")
    private String nama;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

    @SerializedName("password")
    private String password;

    private boolean error;
    private List<PasienEntity> listPasien;

    public PasienEntity() {
    }

    public PasienEntity(String nomorKK, String nomorKTP, String nama, String alamat, String jenisKelamin) {
        this.nomorKK = nomorKK;
        this.nomorKTP = nomorKTP;
        this.nama = nama;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
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

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<PasienEntity> getListPasien() {
        return listPasien;
    }

    public void setListPasien(List<PasienEntity> listPasien) {
        this.listPasien = listPasien;
    }

    public String getNomorKK() {
        return nomorKK;
    }

    public void setNomorKK(String nomorKK) {
        this.nomorKK = nomorKK;
    }

    public String getNomorKTP() {
        return nomorKTP;
    }

    public void setNomorKTP(String nomorKTP) {
        this.nomorKTP = nomorKTP;
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

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
