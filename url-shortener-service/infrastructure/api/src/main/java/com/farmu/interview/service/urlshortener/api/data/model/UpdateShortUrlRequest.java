package com.farmu.interview.service.urlshortener.api.data.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateShortUrlRequest implements Serializable {
	private static final long serialVersionUID = 8800765149968708431L;
	
	@NotEmpty
    private String destinationUrl;

}
