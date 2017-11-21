package mkworld29.mobile.com.cafemoa_store.retrofit;

/**
 * Created by 이은서 on 2017-04-02.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by eunseo on 2016-12-09.
 */
public class RetrofitConnection {
    public class Token{
        public String token;
    }
    public class Option{
        public boolean whipping_cream;
        public boolean is_ice;
        public int size;
        public int shot_num;
        public String beverage_name;
    }
    public class Order{
        public int pk;
        public String order_time;
        public int order_num;
        public int get_time;
        public int payment_type;
        public int orderer_name;
        public List<Option> options;
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
}