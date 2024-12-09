package com.Qzin.Request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserUpdateRequestBody {

    @NotNull(message = "User UUID should not be null")
    private String userUUID;


    private String username;
    private String email;
    private String contactNumber;
    private Boolean isActive;
}
