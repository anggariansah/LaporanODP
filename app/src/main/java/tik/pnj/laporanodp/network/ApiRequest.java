package tik.pnj.laporanodp.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import tik.pnj.laporanodp.data.PasienEntity;

public interface ApiRequest {

    @FormUrlEncoded
    @POST("login")
    Call<PasienEntity> loginUser(@Field("username") String noKtp,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("insertLaporan")
    Call<PasienEntity> insertLaporan(@Field("noKtp") String noKtp,
                                     @Field("demam") String demam,
                                     @Field("sesak") String sesak,
                                     @Field("nyeri") String nyeri,
                                     @Field("batuk") String batuk,
                                     @Field("pilek") String pilek,
                                     @Field("diare") String diare);

    @GET("listProfile")
    Call<PasienEntity> listPasien();

    @GET("detailProfile")
    Call<PasienEntity> detailProfile(@Query("id") String id);

    @GET("listOdp")
    Call<PasienEntity> listOdp();

}
