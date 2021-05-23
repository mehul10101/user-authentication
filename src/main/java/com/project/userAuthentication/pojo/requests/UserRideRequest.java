package com.project.userAuthentication.pojo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRideRequest {
    private String name;
    private Integer pinCode;
    private String vehicleType;
    private String startLandMark;
    private Long id;
    private String phoneNumber;

}
