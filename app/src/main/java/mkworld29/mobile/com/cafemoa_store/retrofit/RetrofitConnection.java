package mkworld29.mobile.com.cafemoa_store.retrofit;

/**
 * Created by 이은서 on 2017-04-02.
 */

import java.util.List;

import mkworld29.mobile.com.cafemoa_store.Order;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by eunseo on 2016-12-09.
 */
public class RetrofitConnection {
    public class Token{
        public String token;
    }

    public class Cafe{
        public boolean is_open;
        public int min_time;
    }

    public interface login {
        @FormUrlEncoded
        @POST("api-auth/")
        Call<Token> repoContributors(
                @Field("username") String username,
                @Field("password") String password
        );
    }

    public interface fcm_register{
        @FormUrlEncoded
        @POST("fcm/devices/")
        Call<ResponseBody> repoContributors(
                @Field("dev_id") String device_id,
                @Field("reg_id") String token,
                @Field("is_active") boolean is_active
        );
    }

    public interface get_orders{
        @GET("get_orders/")
        Call<List<Order>> repoContributors();
    }

    public interface complete_order{
        @POST("complete_order/{PK}")
        Call<ResponseBody> repoContributors(
                @Path("PK") int pk
        );
    }

    public interface set_minTime{
        @FormUrlEncoded
        @POST("set_min_time/")
        Call<ResponseBody> repoContributors(
                @Field("min_time") int min_time
        );
    }

    public interface set_Open{
        @FormUrlEncoded
        @POST("set_open/")
        Call<ResponseBody> repoContributors(
                @Field("is_open") int is_open
        );
    }

    public interface getCafeInfo{
        @GET("cafe_info/")
        Call<Cafe> repoContributors();
    }
}