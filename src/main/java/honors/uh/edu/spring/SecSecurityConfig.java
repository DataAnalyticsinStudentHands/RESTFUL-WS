package honors.uh.edu.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:webSecurityConfig.xml" })
@ComponentScan("honors.uh.edu.security")
public class SecSecurityConfig {

	public SecSecurityConfig() {
		super();
	}

}
