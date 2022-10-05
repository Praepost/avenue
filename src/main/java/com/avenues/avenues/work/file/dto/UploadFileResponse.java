package com.avenues.avenues.work.file.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UploadFileResponse {
    @ApiModelProperty(name = "URI", value = "адрес ссылки", notes = "ссылка для загрузки фото")
    @NonNull
    private String fileDownloadUri;
}
