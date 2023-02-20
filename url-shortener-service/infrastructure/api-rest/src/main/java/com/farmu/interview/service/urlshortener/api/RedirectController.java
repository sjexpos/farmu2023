package com.farmu.interview.service.urlshortener.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.farmu.interview.service.urlshortener.Routes;
import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.exception.UrlNotFoundDomainException;
import com.farmu.interview.service.urlshortener.usecases.urls.GetShortUrlByKeyUseCase;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Routes.DISPATCHER)
@Hidden
@Slf4j
public class RedirectController extends AbstractController {

	private final GetShortUrlByKeyUseCase getShortUrlByKeyUseCase;

    public RedirectController(GetShortUrlByKeyUseCase getShortUrlByKeyUseCase) {
		super();
		this.getShortUrlByKeyUseCase = getShortUrlByKeyUseCase;
	}

    @GetMapping("/{key}")
    public Object getFromBrowser(@PathVariable("key") String key) {
        log.info("############ call get ############");
    	try {
			ShortUrl shortUrl = this.getShortUrlByKeyUseCase.handle(key);
			return new RedirectView(shortUrl.getDestination());
		} catch (UrlNotFoundDomainException e) {
			return new ModelAndView("/not-found-page", HttpStatus.NOT_FOUND);
		}
    }

}
