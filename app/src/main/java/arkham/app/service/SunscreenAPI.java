package arkham.app.service;


import retrofit2.Call;
import retrofit2.http.*;
import arkham.app.datamodel.Session;
import arkham.app.datamodel.User;

public interface SunscreenAPI {
    @POST("/api/sessions")
    Call<Session> login(@Body Session loginParams);

    @GET("/api/users/me")
    Call<User> getMe();

    @GET("/api/users")
    Call<User> getUsers();

    @GET("/api/users/{id}")
    Call<User> getUserById(@Path("id") int id);
}
