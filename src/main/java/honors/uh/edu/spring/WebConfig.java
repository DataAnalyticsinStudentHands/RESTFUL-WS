package honors.uh.edu.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("honors.uh.edu.web")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	public WebConfig() {
		super();
	}

}
