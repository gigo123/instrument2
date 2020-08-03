package ua.com.gigasoft.instrument2c;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfig {
	 @Bean
	    public ResourceBundleMessageSource messageSource() {

	        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	        source.setBasenames("messages/error");
	        source.setUseCodeAsDefaultMessage(true);

	        return source;
	    }
}
