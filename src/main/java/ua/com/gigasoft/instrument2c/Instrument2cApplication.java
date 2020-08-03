package ua.com.gigasoft.instrument2c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;


@SpringBootApplication
public class Instrument2cApplication extends SpringBootServletInitializer {

	 @Autowired
	    private MessageSource messageSource;
	public static void main(String[] args) {
		SpringApplication.run(Instrument2cApplication.class, args);
	}

}
