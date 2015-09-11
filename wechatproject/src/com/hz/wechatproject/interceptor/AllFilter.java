package com.hz.wechatproject.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.hz.wechatproject.utils.CommonUtil;

public class AllFilter extends OncePerRequestFilter {

	public static final boolean IS_FILTER_ENABLED = false;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!IS_FILTER_ENABLED) {
			filterChain.doFilter(request, response);
			return;
		}

		String uri = request.getRequestURI();
		if (CommonUtil.isValidURI(uri)) {
			filterChain.doFilter(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}
}