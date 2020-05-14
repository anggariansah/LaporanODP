package tik.pnj.laporanodp.data;

import com.google.gson.annotations.SerializedName;

public class UserEntity {

    @SerializedName("id_akun")
    private int idAkun;

    @SerializedName("username")
    private String username;

    @SerializedName("id_odp")
    private String idOdp;

    public int getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(int idAkun) {
        this.idAkun = idAkun;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdOdp() {
        return idOdp;
    }

    public void setIdOdp(String idOdp) {
        this.idOdp = idOdp;
    }
}
