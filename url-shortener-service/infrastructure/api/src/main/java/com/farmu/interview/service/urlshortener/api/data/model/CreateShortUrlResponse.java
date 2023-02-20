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
public class CreateShortUrlResponse implements Serializable {
	private static final long serialVersionUID = 2018978085955716758L;
	
	private Long id;
    private String shortUrl;
    private String destinationUrl;

}
