package com.etour.etour_api.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0
 * @project etour-api
 * @since 28-01-2025
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePasswordRequest {
    @NotEmpty(message = "Password cannot be empty or null")
    private String password;
    @NotEmpty(message = "new Password cannot be empty or null")
    private String newPassword;
    @NotEmpty(message = "Confirm password cannot be empty or null")
    private String confirmNewPassword;
}
