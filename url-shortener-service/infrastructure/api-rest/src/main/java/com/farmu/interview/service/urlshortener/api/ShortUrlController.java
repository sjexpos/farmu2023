package com.farmu.interview.service.urlshortener.api;

import com.farmu.interview.service.urlshortener.Routes;
import com.farmu.interview.service.urlshortener.api.data.PageResponse;
import com.farmu.interview.service.urlshortener.api.data.PageResponseImpl;
import com.farmu.interview.service.urlshortener.api.data.PageableRequestImpl;
import com.farmu.interview.service.urlshortener.api.data.model.CreateShortUrlRequest;
import com.farmu.interview.service.urlshortener.api.data.model.CreateShortUrlResponse;
import com.farmu.interview.service.urlshortener.api.data.model.DeleteShortUrlResponse;
import com.farmu.interview.service.urlshortener.api.data.model.GetAllShortUrlResponse;
import com.farmu.interview.service.urlshortener.api.data.model.GetShortUrlResponse;
import com.farmu.interview.service.urlshortener.api.data.model.UpdateShortUrlRequest;
import com.farmu.interview.service.urlshortener.api.data.model.UpdateShortUrlResponse;
import com.farmu.interview.service.urlshortener.api.exception.UrlNotFoundApiException;
import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.exception.UrlNotFoundDomainException;
import com.farmu.interview.service.urlshortener.usecases.urls.GetAllShortUrlUseCase;
import com.farmu.interview.service.urlshortener.usecases.urls.GetShortUrlByIdUseCase;
import com.farmu.interview.service.urlshortener.usecases.urls.UpdateShortUrlUseCase;
import com.farmu.interview.service.urlshortener.usecases.urls.CreateShortUrlUseCase;
import com.farmu.interview.service.urlshortener.usecases.urls.DeleteShortUrlUseCase;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Routes.BASE_SHORT_URLS)
@Tag(name = "Short URLs")
@Slf4j
public class ShortUrlController extends AbstractController implements ShortUrlService {

    private final GetAllShortUrlUseCase getAllShortUrlUseCase;
    private final CreateShortUrlUseCase createShortUrlUseCase;
    private final DeleteShortUrlUseCase deleteShortUrlUseCase;
    private final GetShortUrlByIdUseCase getShortUrlByIdUseCase;
    private final UpdateShortUrlUseCase updateShortUrlUseCase;
    private final String baseDomain;
    
    public ShortUrlController(
        GetAllShortUrlUseCase getAllShortUrlUseCase, 
        CreateShortUrlUseCase createShortUrlUseCase,
        DeleteShortUrlUseCase deleteShortUrlUseCase,
        GetShortUrlByIdUseCase getShortUrlByIdUseCase,
        UpdateShortUrlUseCase updateShortUrlUseCase,
        @Value("${farmu.interview.service.urlshortener.domain}") String baseDomain
    ) {
        this.getAllShortUrlUseCase = getAllShortUrlUseCase;
        this.createShortUrlUseCase = createShortUrlUseCase;
        this.deleteShortUrlUseCase = deleteShortUrlUseCase;
        this.getShortUrlByIdUseCase = getShortUrlByIdUseCase;
        this.updateShortUrlUseCase = updateShortUrlUseCase;
        this.baseDomain = baseDomain;
    }

    @Operation(summary = "Get all short urls")
    @PageableAsQueryParam
    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public PageResponse<GetAllShortUrlResponse> getAll(
        @Parameter(hidden = true) PageableRequestImpl pageable
    ) {
        log.info("############ call getAll ############");
        Page<ShortUrl> urls = this.getAllShortUrlUseCase.handle(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").ascending()));
        List<GetAllShortUrlResponse> response = urls.getContent()
                .stream()
                .map(su -> 
                    GetAllShortUrlResponse.builder()
                            .id(su.getId())
                            .shortUrl(su.getUrl(this.baseDomain, Routes.DISPATCHER))
                            .destinationUrl(su.getDestination())
                            .build()
                ).collect(Collectors.toList());
        return new PageResponseImpl<>(response, pageable, urls.getTotalElements());
    }

    @Operation(summary = "Create a new short url")
    @PostMapping(value = Routes.CREATE_SHORT_URL, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public CreateShortUrlResponse create(
        @RequestBody @Valid CreateShortUrlRequest request
    ) {
        log.info("############ call create [{}] ############", request.getDestinationUrl());
        ShortUrl shortUrl = this.createShortUrlUseCase.handle(request.getDestinationUrl());
        return CreateShortUrlResponse.builder()
                .id(shortUrl.getId())
                .shortUrl(shortUrl.getUrl(this.baseDomain, Routes.DISPATCHER))
                .destinationUrl(shortUrl.getDestination())
                .build();
    }

    @Operation(summary = "Delete short url by id")
    @DeleteMapping(value = Routes.DELETE_SHORT_URL, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public DeleteShortUrlResponse delete(@PathVariable(name = "id") long id) throws UrlNotFoundApiException {
        log.info("############ call delete [{}] ############", id);
    	try {
			ShortUrl shortUrl = this.deleteShortUrlUseCase.handle(id);
			return DeleteShortUrlResponse.builder()
	                .shortUrl(shortUrl.getUrl(this.baseDomain, Routes.DISPATCHER))
	                .destinationUrl(shortUrl.getDestination())
					.build();
		} catch (UrlNotFoundDomainException e) {
			throw new UrlNotFoundApiException(e.getMessage());
		}
    }

    @Operation(summary = "Get short url by id")
    @GetMapping(value = Routes.GET_SHORT_URL_BY_ID, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public GetShortUrlResponse getById(@PathVariable(name = "id") long id) throws UrlNotFoundApiException {
        log.info("############ call getById [{}] ############", id);
        try {
			ShortUrl shortUrl = this.getShortUrlByIdUseCase.handle(id);
			return GetShortUrlResponse.builder()
					.id(shortUrl.getId())
	                .shortUrl(shortUrl.getUrl(this.baseDomain, Routes.DISPATCHER))
	                .destinationUrl(shortUrl.getDestination())
					.build();
		} catch (UrlNotFoundDomainException e) {
			throw new UrlNotFoundApiException(e.getMessage());
		}
    }

    @Operation(summary = "Update short url by id")
    @PutMapping(value = Routes.UPDATE_SHORT_URL_BY_ID, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
	public UpdateShortUrlResponse update(@PathVariable(name = "id") long id, @RequestBody @Valid UpdateShortUrlRequest request) throws UrlNotFoundApiException {
    	try {
			ShortUrl shortUrl = this.updateShortUrlUseCase.handle(id, request.getDestinationUrl());
			return UpdateShortUrlResponse.builder()
					.id(shortUrl.getId())
	                .shortUrl(shortUrl.getUrl(this.baseDomain, Routes.DISPATCHER))
	                .destinationUrl(shortUrl.getDestination())
					.build();
		} catch (UrlNotFoundDomainException e) {
			throw new UrlNotFoundApiException(e.getMessage());
		}
	}
    
}
