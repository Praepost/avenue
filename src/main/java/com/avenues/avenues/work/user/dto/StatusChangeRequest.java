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
public class StatusChangeRequest {
    @ApiModelProperty(name = "id", value = "user id")
    private Integer id;
    @ApiModelProperty(name = "status", value = "user status: Online/Offline")
    private String status;
}
