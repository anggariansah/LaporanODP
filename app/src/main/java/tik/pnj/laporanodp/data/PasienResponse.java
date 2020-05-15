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

    @SerializedName("user")
    private List<UserEntity> user;

    @SerializedName("laporan")
    private List<LaporanEntity> listLaporan;

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
}
