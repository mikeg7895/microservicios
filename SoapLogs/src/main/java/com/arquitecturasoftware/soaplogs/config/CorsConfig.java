package com.arquitecturasoftware.soaplogs.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<Filter> corsFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                HttpServletRequest httpRequest = (HttpServletRequest) request;

                // Agregar cabeceras de CORS
                httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
                httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // Métodos permitidos
                httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
                httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

                // Manejar solicitudes de preflight (OPTIONS)
                if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
                    httpResponse.setStatus(HttpServletResponse.SC_OK);
                    return;
                }

                chain.doFilter(request, response);
            }
        });

        // Aplica este filtro a todas las rutas SOAP
        registrationBean.addUrlPatterns("/ws/*"); // Cambia esto según tu configuración
        registrationBean.setOrder(0); // Prioridad alta

        return registrationBean;
    }
}
