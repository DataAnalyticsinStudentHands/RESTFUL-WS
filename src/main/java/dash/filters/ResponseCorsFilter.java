package dash.filters;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

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
		response.addHeader("Access-Control-Allow-Methods",
				"GET, POST, PUT, OPTIONS, DELETE");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader(
				"Access-Control-Allow-Headers",
				"Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With, X-XSRF-TOKEN");
	}
}
