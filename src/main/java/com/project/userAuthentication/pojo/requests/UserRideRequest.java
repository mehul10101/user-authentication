package com.project.userAuthentication.pojo.requests;

import lombok.Data;

@Data
public class UserRideRequest {
    private String name;
    private Integer id;
    private Integer pinCode;
    private String vehicleType;
    private String startLandMark;

}
