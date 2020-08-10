package tik.pnj.laporanodp.data;

import com.google.gson.annotations.SerializedName;

public class UserEntity {

    @SerializedName("id_akun")
    private int idAkun;

    @SerializedName("username")
    private String username;

    @SerializedName("id_kasus")
    private String idKasus;

    @SerializedName("nik")
    private String nik;

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

    public String getIdKasus() {
        return idKasus;
    }

    public void setIdKasus(String idKasus) {
        this.idKasus = idKasus;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
}
