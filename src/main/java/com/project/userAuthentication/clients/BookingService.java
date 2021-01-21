package com.project.userAuthentication.clients;

import com.project.userAuthentication.pojo.requests.UserRideRequest;
import com.project.userAuthentication.pojo.responses.RideDetailsResponse;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.*;

@Component
public interface BookingService {

    @POST
    Call<Void> requestRide(UserRideRequest userRideRequest);

    @POST
    Call<Void> cancelRide(@Query("rideId") Integer rideId);

    @GET
    Call<RideDetailsResponse> getRideDetails(@Query("rideId") Integer rideId);
}
