package com.farmu.interview.service.urlshortener.api.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonErrorHandlerResponse {
    private int status;
    private String error;
    private String message;
    private String path;
    
}
