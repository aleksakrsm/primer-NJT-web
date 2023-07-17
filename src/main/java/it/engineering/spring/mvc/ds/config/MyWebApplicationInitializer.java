package it.engineering.spring.mvc.ds.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebApplicationInitializer implements WebApplicationInitializer{

	public MyWebApplicationInitializer() {
		System.out.println("======================= CONSTRUCTOR ========================");
		System.out.println("public class MyWebApplicationInitializer implements WebApplicationInitializer");
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("======================= METODA ========================");
		System.out.println("public void onStartup(ServletContext servletContext) throws ServletException ");
		AnnotationConfigWebApplicationContext rootContext = createContext();
		rootContext.register(RootAppConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		//konfigurisanje dispetcher servlet-a
		//svaki dispetcher servklet ima svoj kontekst - web context 
		//kreirati web kontekst i taj veb kontekst vezati za dispetcher servlet
		AnnotationConfigWebApplicationContext webContext = createContext();
		webContext.register(MvcConfig.class); //klasa koja sluzi za konfigurisanje svih komponenti koje ucestuju u obradi zahteva preko 
								   //dispatcher servlet-a
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);
		
	}
	
	//kreiraj context
	private AnnotationConfigWebApplicationContext createContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		return context;
	}
	
}
