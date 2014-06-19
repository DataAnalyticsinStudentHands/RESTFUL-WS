package honors.uh.edu.infrastructure;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

// This is the entry point for Guice Dependency Injection when called from a servlet container using web.xml
//
// You should have something similar in your web.xml:  s
//
//      <listener>
//              <listener-class>ngdemo.infrastructure.NgDemoApplicationSetup</listener-class>
//      </listener>
public class DashApplicationSetup extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {

		return Guice.createInjector(new ServletModule() {

			@Override
			protected void configureServlets() {

				super.configureServlets();

				// Configuring Jersey via Guice:
				final ResourceConfig resourceConfig = new PackagesResourceConfig("honors.uh.edu/rest");
				for (final Class<?> resource : resourceConfig.getClasses()) {
					bind(resource);
				}

				// hook Jackson into Jersey as the POJO <-> JSON mapper
				bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);

				serve("/rest/*").with(GuiceContainer.class);

				// filter("/rest/*").through(ResponseCorsFilter.class);
			}
		}, new UserModule()); // <-- Adding other Guice Dependency Injection Modules
	}
}