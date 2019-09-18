package com.task.simple.usersingup.logging;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestsFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestsFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest,
                                    @NonNull HttpServletResponse httpServletResponse,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(httpServletRequest, httpServletResponse);

        final String method = httpServletRequest.getMethod();
        final String path = httpServletRequest.getServletPath();
        final String query = httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : StringUtils.EMPTY;
        final String status = String.valueOf(httpServletResponse.getStatus());

        LOGGER.info("{} {}{} - {}", method, path, query, status);
    }
}
