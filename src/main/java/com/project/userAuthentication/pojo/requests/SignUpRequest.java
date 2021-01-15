package com.project.userAuthentication.pojo.requests;

import lombok.Data;
import lombok.NonNull;

@Data
public class SignUpRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String userName;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String name;

}
