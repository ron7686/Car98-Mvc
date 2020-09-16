package com.web.car98.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.web.car98")
public class WebAppConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = 
         				new InternalResourceViewResolver();
//		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
//	 為了處理靜態檔案必須加入下列敘述：只要是 /css/開頭的任何請求，都轉到/WEB-INF/views/css/去尋找
//	 為了處理靜態檔案必須加入下列敘述：只要是 /image/開頭的任何請求，都轉到/WEB-INF/views/images/去尋找
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**")
				.addResourceLocations("/WEB-INF/views/css/");
		registry.addResourceHandler("/image/**")
				.addResourceLocations("/WEB-INF/views/image/");
		registry.addResourceHandler("/javascript/**")
				.addResourceLocations("/WEB-INF/views/javascript/");
		registry.addResourceHandler("/commodity/**")
		.addResourceLocations("/WEB-INF/views/commodity/");
		registry.addResourceHandler("/forum/**")
		.addResourceLocations("/WEB-INF/views/forum/");
		registry.addResourceHandler("/login/**")
		.addResourceLocations("/WEB-INF/views/login/");
		registry.addResourceHandler("/management/**")
		.addResourceLocations("/WEB-INF/views/management/");
		registry.addResourceHandler("/register/**")
		.addResourceLocations("/WEB-INF/views/register/");
		registry.addResourceHandler("/rent/**")
		.addResourceLocations("/WEB-INF/views/rent/");
		registry.addResourceHandler("/searchresource/**")
		.addResourceLocations("/WEB-INF/views/searchresource/");
		
		
	}

}
