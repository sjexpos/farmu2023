package com.farmu.interview.service.urlshortener.api.data.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateShortUrlResponse implements Serializable {
	private static final long serialVersionUID = -2512894150369896527L;
	
	private Long id;
    private String shortUrl;
    private String destinationUrl;

}
