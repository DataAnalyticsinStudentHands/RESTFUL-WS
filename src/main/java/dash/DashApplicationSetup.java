package dash;

import java.lang.annotation.Annotation;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import dash.errorhandling.AppExceptionMapper;
import dash.errorhandling.GenericExceptionMapper;
import dash.errorhandling.NotFoundExceptionMapper;
import dash.filters.LoggingResponseFilter;
import dash.pojo.UserDetailedView;
import dash.pojo.UsersResource;
import dash.pojo.PlantResource;
import dash.pojo.PlantDetailedView;

/**
 * Registers the components to be used by the JAX-RS application
 *
 * @author plindner
 *
 */
public class DashApplicationSetup extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public DashApplicationSetup() {
		// register application resources
		register(UsersResource.class);
		register(PlantResource.class);

		// register filters
		register(RequestContextFilter.class);
		register(LoggingResponseFilter.class);

		// register exception mappers
		register(GenericExceptionMapper.class);
		register(AppExceptionMapper.class);
		register(NotFoundExceptionMapper.class);

		// register features
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		register(EntityFilteringFeature.class);

		property(EntityFilteringFeature.ENTITY_FILTERING_SCOPE,
				new Annotation[] { UserDetailedView.Factory.get() });
		property(EntityFilteringFeature.ENTITY_FILTERING_SCOPE,
				new Annotation[] { PlantDetailedView.Factory.get() });
	}
}

