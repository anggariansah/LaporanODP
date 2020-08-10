package tik.pnj.laporanodp.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import tik.pnj.laporanodp.data.LaporanResponse;
import tik.pnj.laporanodp.data.NotifResponse;
import tik.pnj.laporanodp.data.PasienResponse;

public interface ApiRequest {

    @FormUrlEncoded
    @POST("login/login_pasien")
    Call<PasienResponse> loginUser(@Field("username") String username,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("login/login_pj")
    Call<PasienResponse> loginPJ(@Field("username") String username,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("informasi_klinis/add_laporan")
    Call<PasienResponse> insertLaporan(@Field("id_kasus") String idOdp,
                                     @Field("sakit_tgg") String nyeri,
                                     @Field("batuk") String batuk,
                                     @Field("pilek") String pilek,
                                     @Field("diare") String diare,
                                     @Field("suhu") String suhu,
                                     @Field("tanggal") String tanggal,
                                     @Field("sesak") String sesak);

    @FormUrlEncoded
    @POST("profile_pasien/update")
    Call<PasienResponse> updateProfile(@Field("id_kasus") String id,
                                       @Field("nik") String noKtp,
                                       @Field("nama") String nama,
                                       @Field("alamat") String alamat,
                                       @Field("jk") String jenkel,
                                       @Field("tgl_lahir") String tgl_lahir);

    @FormUrlEncoded
    @POST("akun/update_pasien")
    Call<PasienResponse> updatePasswordPasien(@Field("id") String id,
                                              @Field("old_password") String old_password,
                                              @Field("new_password") String new_password);

    @FormUrlEncoded
    @POST("akun/update_pj")
    Call<PasienResponse> updatePasswordPj(@Field("nik") String nik,
                                          @Field("old_password") String old_password,
                                          @Field("new_password") String new_password);

    @GET("profile_pasien/data")
    Call<PasienResponse> detailProfile(@Query("id_pasien") String id);

    @GET("profile_pasien")
    Call<PasienResponse> listOdp(@Query("nik_pj") String nomorKK);

    @GET("profile_pasien")
    Call<PasienResponse> listOdpPasien(@Query("id_kasus") String idkasus);

    @GET("informasi_klinis")
    Call<LaporanResponse> listLaporan(@Query("id_kasus") String idOdp);

    @GET("informasi_klinis/check_notif_pj")
    Call<NotifResponse> statusNotifPj(@Query("nik_pj") String nikpj);

    @GET("informasi_klinis/check_notif_pasien")
    Call<NotifResponse> statusNotifPasien(@Query("id_kasus") String idkasus);

}
