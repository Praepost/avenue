package com.avenues.avenues.controller.user;

import com.avenues.avenues.work.user.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserController {
    @PostMapping("/reg")
    @ApiOperation(value = "add user")
    @ApiResponses({
            @ApiResponse(code = 200, response = UserRegResponse.class, message =
                    "Successful response: \n" +
                            "- Register User. \n"),
            @ApiResponse(code = 400, message =
                    "Error codes: general")

    })
    UserRegResponse regUser(@RequestBody UserRegRequest request);

    @PostMapping("/profile")
    @ApiOperation(value = "view user")
    @ApiResponses({
            @ApiResponse(code = 200, response = UserProfileResponse.class, message =
                    "Successful response: \n" +
                            "- Profile User. \n"),
            @ApiResponse(code = 400, message =
                    "Error codes: general")

    })
    UserProfileResponse userProfile(@RequestBody UserProfileRequest request);

    @PostMapping("/online")
    @ApiOperation(value = "update online user status")
    @ApiResponses({
            @ApiResponse(code = 200, response = StatusChangeResponse.class, message =
                    "Successful response: \n" +
                            "- Status User. \n"),
            @ApiResponse(code = 400, message =
                    "Error codes: general")

    })
    StatusChangeResponse onlineStatus(@RequestBody StatusChangeRequest request);

}
