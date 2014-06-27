package honors.uh.edu.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

/*
    Allow CORS requests.
 */
@Singleton
public class ResponseCorsFilter implements Filter {

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException { }

	@Override
	public void destroy() { }

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
		if (servletResponse instanceof HttpServletResponse) {
			final HttpServletResponse alteredResponse = ((HttpServletResponse) servletResponse);
			addHeadersFor200Response(alteredResponse);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private void addHeadersFor200Response(final HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With");
		response.addHeader("Access-Control-Allow-Headers", "GET, PUT, OPTIONS, X-XSRF-TOKEN");
	}
}
