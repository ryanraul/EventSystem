package com.event.system.eventsystem.dto.AdminDTO;

import javax.validation.constraints.NotBlank;

public class AdminDTOUpdate {
    @NotBlank(message = "The 'email' cannot be empty!")
    private String email;

    @NotBlank(message = "The 'phone number' cannot be empty!")
    private String phoneNumber;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
