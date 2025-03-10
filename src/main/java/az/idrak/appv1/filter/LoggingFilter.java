package az.idrak.appv1.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@Slf4j
@Component
@Order(1)
public class LoggingFilter extends HttpFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.trace("My logging filter initialized");
        Iterator<String> stringIterator = filterConfig.getServletContext().getInitParameterNames().asIterator();
        while (stringIterator.hasNext()){
            String paramName = stringIterator.next();
            log.trace("Context param: name: {} , value: {}", paramName, filterConfig.getServletContext().getInitParameter(paramName));
        }
    }

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.trace("Filter in action ");
        Iterator<String> stringIterator = servletRequest.getHeaderNames().asIterator();
        while (stringIterator.hasNext()){
            String next = stringIterator.next();
            log.trace("Header param {} -> {}", next, servletRequest.getHeader(next));
        }
    }

    @Override
    public void destroy() {
        log.trace("My logging filter being destroyed");
    }
}
