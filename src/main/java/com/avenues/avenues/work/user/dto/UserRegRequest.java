package com.avenues.avenues.work.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegRequest {
    @ApiModelProperty(name = "uri", value = "uri of profile image")
    private String uri;
    @ApiModelProperty(name = "username", value = "name of user")
    private String username;
    @ApiModelProperty(name = "uri", value = "email of user")
    private String email;
}
