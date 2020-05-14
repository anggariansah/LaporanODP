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
    @POST("login/login")
    Call<PasienResponse> loginUser(@Field("username") String noKtp,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("laporan/add")
    Call<PasienResponse> insertLaporan(@Field("id_odp") String idOdp,
                                     @Field("demam") String demam,
                                     @Field("sesak") String sesak,
                                     @Field("nyeri_tenggorokan") String nyeri,
                                     @Field("batuk") String batuk,
                                     @Field("pilek") String pilek,
                                     @Field("diare") String diare);

    @FormUrlEncoded
    @POST("data/update")
    Call<PasienResponse> updateProfile(@Field("id") String id,
                                       @Field("no_ktp") String noKtp,
                                       @Field("no_kk") String noKK,
                                       @Field("nama_lengkap") String nama,
                                       @Field("alamat") String alamat,
                                       @Field("status_kk") String status,
                                       @Field("jenis_kelamin") String jenkel);

    @FormUrlEncoded
    @POST("akun/update")
    Call<PasienResponse> updatePassword(@Field("id") String id,
                                        @Field("password") String passwordBaru);

    @GET("data")
    Call<PasienResponse> detailProfile(@Query("id") String id);

    @GET("data")
    Call<PasienResponse> listOdp(@Query("no_kk") String nomorKK);

}
