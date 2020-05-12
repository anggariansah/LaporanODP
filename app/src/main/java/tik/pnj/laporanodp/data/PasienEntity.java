package tik.pnj.laporanodp.data;

public class PasienEntity {

    private String nomorKK, nomorKTP, nama, alamat, jenisKelamin;

    public PasienEntity() {
    }

    public PasienEntity(String nomorKK, String nomorKTP, String nama, String alamat, String jenisKelamin) {
        this.nomorKK = nomorKK;
        this.nomorKTP = nomorKTP;
        this.nama = nama;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
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
