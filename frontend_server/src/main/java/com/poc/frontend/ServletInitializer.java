package com.poc.frontend;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;

import com.sun.faces.config.ConfigureListener;

import base.filter.AppFilter;
import base.listener.SessionInvalidate;
import base.servlet.FileServlet;

public class ServletInitializer extends SpringBootServletInitializer implements ServletContextAware {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FrontendServerApplication.class);
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletRegistration() {
		ServletRegistrationBean<FacesServlet> servletRegistrationBean = new ServletRegistrationBean<FacesServlet>();
		servletRegistrationBean.setName("Faces Servlet");
		servletRegistrationBean.setServlet(new FacesServlet());
		servletRegistrationBean.setLoadOnStartup(1);
		servletRegistrationBean.addUrlMappings("*.xhtml");
		return servletRegistrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}

	@Bean
	public ServletListenerRegistrationBean<SessionInvalidate> sessionInvalidate() {
		return new ServletListenerRegistrationBean<SessionInvalidate>(new SessionInvalidate());
	}

	@Bean
	public FilterRegistrationBean<FileUploadFilter> primeFacesFileUploadFilter() {
		FilterRegistrationBean<FileUploadFilter> filterRegistrationBean = new FilterRegistrationBean<FileUploadFilter>();
		filterRegistrationBean.setName("PrimeFaces FileUpload Filter");
		filterRegistrationBean.setFilter(new FileUploadFilter());
		filterRegistrationBean.addServletNames("Faces Servlet");
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<AppFilter> appFilter() {
		FilterRegistrationBean<AppFilter> filterRegistrationBean = new FilterRegistrationBean<AppFilter>();
		filterRegistrationBean.setName("AppFilter");
		filterRegistrationBean.setFilter(new AppFilter());
		filterRegistrationBean.addUrlPatterns("/pages/*");
		return filterRegistrationBean;
	}

	@Bean
	public ServletRegistrationBean<FileServlet> fileServletRegistration() {
		ServletRegistrationBean<FileServlet> servletRegistrationBean = new ServletRegistrationBean<FileServlet>();
		servletRegistrationBean.setName("FileServlet");
		servletRegistrationBean.setServlet(new FileServlet());
		servletRegistrationBean.addUrlMappings("/servlet/secured/FileServlet");
		return servletRegistrationBean;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		/* Faces Servlet */
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
		servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
		servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
		/* PrimeFaces FileUpload Filter */
		servletContext.setInitParameter("primefaces.UPLOADER", "commons");
		servletContext.setInitParameter("primefaces.FONT_AWESOME", "true");
	}

}
