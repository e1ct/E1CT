package kr.co.e1ct.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.e1ct.interceptor.LocaleInterceptor;
import kr.co.e1ct.interceptor.MenuInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	@Qualifier(value = "localeInterceptor" )
	private LocaleInterceptor localeInterceptor;
	
	@Autowired
	@Qualifier(value = "menuInterceptor" )
	private MenuInterceptor menuInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor( localeInterceptor ).addPathPatterns( "/**" ).excludePathPatterns( "/static/**" );
		registry.addInterceptor( menuInterceptor ).excludePathPatterns( "/static/**" );
	}
}
