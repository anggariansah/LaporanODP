package tik.pnj.laporanodp.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import tik.pnj.laporanodp.data.PasienResponse;

public interface ApiRequest {

    @FormUrlEncoded
    @POST("login/login_pasien")
    Call<PasienResponse> loginUser(@Field("username") String noKtp,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("laporan/add")
    Call<PasienResponse> insertLaporan(@Field("id_kasus") String idOdp,
                                     @Field("sakit_tgg") String nyeri,
                                     @Field("batuk") String batuk,
                                     @Field("pilek") String pilek,
                                     @Field("diare") String diare,
                                     @Field("suhu_demam") String suhu,
                                     @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @POST("data/update")
    Call<PasienResponse> updateProfile(@Field("id") String id,
                                       @Field("no_ktp") String noKtp,
                                       @Field("nama_lengkap") String nama,
                                       @Field("alamat") String alamat,
                                       @Field("jenis_kelamin") String jenkel);

    @FormUrlEncoded
    @POST("akun/update")
    Call<PasienResponse> updatePassword(@Field("id") String id,
                                        @Field("password") String passwordBaru);

    @GET("data")
    Call<PasienResponse> detailProfile(@Query("no_kk") String id);

    @GET("data")
    Call<PasienResponse> listOdp(@Query("no_kk") String nomorKK);

    @GET("laporan")
    Call<PasienResponse> listLaporan(@Query("id_kasus") String idOdp);

}
