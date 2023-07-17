package it.engineering.spring.mvc.ds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {
		"it.engineering.spring.mvc.ds.controller"
})
public class MvcConfig {

	@Bean
	public ViewResolver jspViewResolver() {
		System.out.println("=========================== jspViewResolver =====================");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(1);
		return resolver;
	}
}
