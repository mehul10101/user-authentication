package com.project.userAuthentication.clients;

import com.project.userAuthentication.pojo.requests.UserRideRequest;
import com.project.userAuthentication.pojo.responses.RideDetailsResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface BookingService {

    @POST("/user/booking/requestRide")
    Call<Void> requestRide(@Body UserRideRequest userRideRequest);

    @POST("/user/booking/cancelRide")
    Call<Void> cancelRide(@Query("rideId") Long rideId);

    @GET("/user/booking/rideDetails")
    Call<RideDetailsResponse> getRideDetails(@Query("rideId") Long rideId);
}
