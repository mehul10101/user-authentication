package com.project.userAuthentication.pojo.responses;

import lombok.Data;

@Data
public class RideDetailsResponse {
    private Integer rideId;
    private Integer pinCode;
    private String startLandMark;
    private String rideStatus;
    private String driverName;

}
