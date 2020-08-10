package tik.pnj.laporanodp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotifResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("error")
    private boolean error;

    @SerializedName("data")
    private boolean statusNotif;


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

    public boolean isStatusNotif() {
        return statusNotif;
    }

    public void setStatusNotif(boolean statusNotif) {
        this.statusNotif = statusNotif;
    }
}
