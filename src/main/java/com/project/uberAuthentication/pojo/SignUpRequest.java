package com.project.uberAuthentication.pojo;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class SignUpRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String userName;
    private List<String> roles;

}
