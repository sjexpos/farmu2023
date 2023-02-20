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
public class DeleteShortUrlResponse implements Serializable {
	private static final long serialVersionUID = 2862992438543656505L;
	
	private String shortUrl;
    private String destinationUrl;

}
