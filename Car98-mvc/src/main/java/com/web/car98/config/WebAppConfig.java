package com.web.car98.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan("com.web.car98")
public class WebAppConfig implements WebMvcConfigurer {

	@Autowired
	SessionFactory factory;
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = 
         				new InternalResourceViewResolver();
//		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
//	@Override
////	 為了處理靜態檔案必須加入下列敘述：只要是 /css/開頭的任何請求，都轉到/WEB-INF/views/css/去尋找
////	 為了處理靜態檔案必須加入下列敘述：只要是 /image/開頭的任何請求，都轉到/WEB-INF/views/images/去尋找
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/css/**")
//				.addResourceLocations("/WEB-INF/views/css/");
//		registry.addResourceHandler("/image/**")
//				.addResourceLocations("/WEB-INF/views/image/");
//		registry.addResourceHandler("/javascript/**")
//				.addResourceLocations("/WEB-INF/views/javascript/");		
//	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckLoginInterceptor());
        DisableCacheInterceptor  disableCacheInterceptor = new DisableCacheInterceptor();
        registry.addInterceptor(disableCacheInterceptor);

        OpenSessionInViewInterceptor openSessionInViewInterceptor = new OpenSessionInViewInterceptor();
        openSessionInViewInterceptor.setSessionFactory(factory);
        registry.addWebRequestInterceptor(openSessionInViewInterceptor).addPathPatterns("/_05_orderProcess/orderDetail");


    }
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// 將 DefaultServlet 打開來
		configurer.enable();
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxInMemorySize(81920000);
		return resolver;
	}
	
	@Bean 
	public MappingJackson2JsonView jsonView() {
	    MappingJackson2JsonView view = new MappingJackson2JsonView();
	    view.setPrettyPrint(true);
	    return view;
	}

	
}
