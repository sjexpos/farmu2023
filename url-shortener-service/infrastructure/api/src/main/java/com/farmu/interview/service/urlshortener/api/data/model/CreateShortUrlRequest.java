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
public class CreateShortUrlRequest implements Serializable {
	private static final long serialVersionUID = 6236470354459534838L;
	
	@NotEmpty
    private String destinationUrl;

}
