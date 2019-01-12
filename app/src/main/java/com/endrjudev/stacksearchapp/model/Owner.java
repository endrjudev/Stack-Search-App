package com.endrjudev.stacksearchapp.model;

import com.squareup.moshi.Json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    @Json(name = "reputation")
    private Integer reputation;
    @Json(name = "user_id")
    private Integer userId;
    @Json(name = "user_type")
    private String userType;
    @Json(name = "profile_image")
    private String profileImage;
    @Json(name = "display_name")
    private String displayName;
    @Json(name = "link")
    private String link;
}
