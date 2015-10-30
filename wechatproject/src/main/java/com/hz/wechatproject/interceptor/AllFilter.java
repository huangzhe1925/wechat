package com.hz.wechatproject.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hz.wechatproject.utils.CommonUtil;

public class AllFilter extends OncePerRequestFilter {
	
	private static Logger logger = Logger.getLogger(AllFilter.class);

	public static final boolean IS_FILTER_ENABLED = true;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		
		logger.debug("REQUEST uri: "+uri);

		if (!IS_FILTER_ENABLED) {
			logger.debug("AllFilter is Disabled. ");
			filterChain.doFilter(request, response);
			return;
		}

		Boolean isValid=CommonUtil.isValidURI(uri);
		logger.debug("REQUEST URI "+uri+" is "+(isValid?"valid":"invalid"));
		if (isValid) {
			filterChain.doFilter(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}
}