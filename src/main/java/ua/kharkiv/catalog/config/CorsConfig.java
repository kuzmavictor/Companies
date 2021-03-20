package ua.kharkiv.catalog.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Enables the `CORS` requests for all endpoints.
 *
 * <p>Allows handling the requests obtained from other domains without rejection.
 */
@Configuration
public class CorsConfig extends CorsFilter {

    /**
     * Constructor accepting a {@link CorsConfigurationSource} used by the filter
     * to find the {@link CorsConfiguration} to use for each incoming request.
     *
     * @param configSource
     * @see UrlBasedCorsConfigurationSource
     */
    public CorsConfig(@Qualifier("mvcHandlerMappingIntrospector") CorsConfigurationSource configSource) {
        super(configSource);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, IOException, ServletException {

        if (logger.isInfoEnabled()) {
            logger.info("The `CORS` filter is ignited");
        }
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Accept, X-Requested-With, remember-me");

        filterChain.doFilter(request, response);
    }
}
