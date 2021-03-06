package tik.pnj.laporanodp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("error")
    private boolean error;

    @SerializedName("data")
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

    public List<LaporanEntity> getListLaporan() {
        return listLaporan;
    }

    public void setListLaporan(List<LaporanEntity> listLaporan) {
        this.listLaporan = listLaporan;
    }
}
