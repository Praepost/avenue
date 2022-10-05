package com.avenues.avenues.controller.file;

import com.avenues.avenues.work.file.dto.UploadFileResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Validated
public interface IFileController {
    @PostMapping("/upload")
    @ApiOperation(value = "upload file")
    @ApiResponses({
            @ApiResponse(code = 200, response = UploadFileResponse.class, message =
                    "Successful response: \n" +
                            "- Register User. \n"),
            @ApiResponse(code = 400, message =
                    "Error codes: general")

    })
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file);

    @PostMapping("/uploads")
    @ApiOperation(value = "upload files")
    @ApiResponses({
            @ApiResponse(code = 200, response = UploadFileResponse.class,
                    message =
                    "Successful response: \n" +
                            "- Upload file. \n"),
            @ApiResponse(code = 400, message =
                    "Error codes: general, operation.file.error")

    })
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files);


    @ApiOperation(value = "dowload file")
    @ApiResponses({
            @ApiResponse(code = 200, response = Resource.class, message =
                    "Successful response: \n" +
                            "- Upload files. \n"),
            @ApiResponse(code = 400, message =
                    "Error codes: general, operation.file.error")

    })
    @GetMapping("/dowload/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request);
}
