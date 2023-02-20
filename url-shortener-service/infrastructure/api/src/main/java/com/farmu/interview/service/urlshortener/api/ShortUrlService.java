package com.farmu.interview.service.urlshortener.api;

import com.farmu.interview.service.urlshortener.api.data.PageResponse;
import com.farmu.interview.service.urlshortener.api.data.PageableRequestImpl;
import com.farmu.interview.service.urlshortener.api.data.model.GetAllShortUrlResponse;
import com.farmu.interview.service.urlshortener.api.data.model.GetShortUrlResponse;
import com.farmu.interview.service.urlshortener.api.data.model.UpdateShortUrlRequest;
import com.farmu.interview.service.urlshortener.api.data.model.UpdateShortUrlResponse;
import com.farmu.interview.service.urlshortener.api.exception.UrlNotFoundApiException;
import com.farmu.interview.service.urlshortener.api.data.model.CreateShortUrlRequest;
import com.farmu.interview.service.urlshortener.api.data.model.CreateShortUrlResponse;
import com.farmu.interview.service.urlshortener.api.data.model.DeleteShortUrlResponse;


public interface ShortUrlService {
    
    PageResponse<GetAllShortUrlResponse> getAll(PageableRequestImpl pageable);

    CreateShortUrlResponse create(CreateShortUrlRequest request);

    DeleteShortUrlResponse delete(long Id) throws UrlNotFoundApiException;
    
    GetShortUrlResponse getById(long id) throws UrlNotFoundApiException;
    
    UpdateShortUrlResponse update(long id, UpdateShortUrlRequest request) throws UrlNotFoundApiException; 
    
}
