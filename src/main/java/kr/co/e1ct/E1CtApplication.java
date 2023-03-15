package kr.co.e1ct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan( basePackages = "kr/co/e1ct/mappers" )
public class E1CtApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(E1CtApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources( E1CtApplication.class );
	}
	
	@Bean( name = "uploadPath" )
	public String uploadPath() {
		return "C:/uploadFile";
	}
	/*
	 * implements WebMvcConfigurer
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale( Locale.KOREA );
		return localeResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor( localeChangeInterceptor() );
	}
	*/

}
