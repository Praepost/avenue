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
public class UserRegResponse {
    @ApiModelProperty(name = "id", value = "id пользователя")
    private Integer id;
}
