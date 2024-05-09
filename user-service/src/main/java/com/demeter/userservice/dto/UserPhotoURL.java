package com.demeter.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserPhotoURL {
    @JsonProperty("picture")
    private String picture;

    public UserPhotoURL(final String url){
        this.picture = url;
    }
}
