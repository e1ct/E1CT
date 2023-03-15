package kr.co.e1ct.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LocaleInterceptor implements HandlerInterceptor {
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if( localeResolver.resolveLocale(request) == null ) {
			request.getSession().setAttribute("i18n", Locale.KOREA.getLanguage() );
			request.setAttribute("i18n", Locale.KOREA.getLanguage());
		} else {
			request.setAttribute("i18n", localeResolver.resolveLocale(request).getLanguage() );
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
