package tik.pnj.laporanodp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PasienResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("error")
    private boolean error;

    @SerializedName("data")
    private List<PasienEntity> listPasien;

    @SerializedName("data_pasien")
    private List<PasienEntity> listDataPasien;

    @SerializedName("user")
    private List<UserEntity> user;

    @SerializedName("laporan")
    private List<LaporanEntity> listLaporan;

    @SerializedName("message")
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public List<UserEntity> getUser() {
        return user;
    }

    public void setUser(List<UserEntity> user) {
        this.user = user;
    }

    public List<LaporanEntity> getListLaporan() {
        return listLaporan;
    }

    public void setListLaporan(List<LaporanEntity> listLaporan) {
        this.listLaporan = listLaporan;
    }

    public List<PasienEntity> getListDataPasien() {
        return listDataPasien;
    }

    public void setListDataPasien(List<PasienEntity> listDataPasien) {
        this.listDataPasien = listDataPasien;
    }
}
