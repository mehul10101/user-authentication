package com.project.userAuthentication.controllers;

import com.project.userAuthentication.clients.BookingService;
import com.project.userAuthentication.clients.serviceGenerator.BookingServiceGenerator;
import com.project.userAuthentication.entities.UserEntity;
import com.project.userAuthentication.pojo.requests.UserRideRequest;
import com.project.userAuthentication.pojo.responses.RideDetailsResponse;
import com.project.userAuthentication.repository.UserRepository;
import com.project.userAuthentication.utils.ApiError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.io.IOException;

/**
 * This class will contain the apis of handling of booking on user side
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/user/booking")
@Slf4j
public class BookingController {

    private final BookingService bookingService = BookingServiceGenerator.getService();

    @Autowired
    private UserRepository userRepository;

    // request ride
    @PostMapping(value = "/requestRide")
    public void requestRide(@RequestHeader ("userId") String userIdString,
                            @RequestBody UserRideRequest userRideRequest) throws IOException {
        Long userId = Long.valueOf(userIdString);
        UserEntity userEntity = userRepository.findById(userId).get();
        userRideRequest.setId(userId);
        userRideRequest.setPhoneNumber(userEntity.getPhoneNumber());
        Response<Void> response = bookingService.requestRide(userRideRequest).execute();
        if(!response.isSuccessful()){
            throw new ApiError(HttpStatus.BAD_REQUEST, "error while booking ride");
        }

    }

    // cancel booking
    @PostMapping(value = "/cancelRide")
    public void cancelRide(@Param("rideId") Long rideId) throws IOException {
        Response<Void> response = bookingService.cancelRide(rideId).execute();
        if(!response.isSuccessful()){
            throw new ApiError(HttpStatus.BAD_REQUEST, "error while booking ride");
        }
    }

    //getRides
    @GetMapping(value = "/rides")
    public RideDetailsResponse getRideDetails(@Param("rideId") Long rideId) throws IOException {
        Response<RideDetailsResponse> response = bookingService.getRideDetails(rideId).execute();
        if(!response.isSuccessful()){
            throw new ApiError(HttpStatus.BAD_REQUEST, "error while booking ride");
        }
        return response.body();
    }

}
