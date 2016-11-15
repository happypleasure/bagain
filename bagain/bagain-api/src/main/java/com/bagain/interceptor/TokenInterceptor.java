package com.bagain.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.bagain.utils.Constants;
import org.bagain.utils.Constants.ResultCode;
import org.bagain.utils.ReqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TokenInterceptor implements HandlerInterceptor{

	private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String tag = "preHandle";
        String accessToken = request.getParameter("access_token");
        
        printRequestParams(request);
        
        if (StringUtils.isEmpty(accessToken)) {
            render(response, Constants.ResultCode.Token_null,"token为空");
            if (logger.isInfoEnabled()) {
            	logger.info("{}. accessToken is null!. uri={}, ret={}", tag, request.getRequestURI(), false);
            }
            return false;
        } else {
        	Integer userId = ReqUtil.getUserId(accessToken);
            // 请求令牌是否有效
            if (0 == userId) {
                render(response, ResultCode.Token_inValid,"无效token");
                if (logger.isInfoEnabled()) {
                	logger.info("{}. invalied accessToken. uri={}, ret={}", tag, request.getRequestURI(), false);
                }
                return false;
            }
        }
        if (logger.isInfoEnabled()) {
        	logger.info("{}. uri={}, ret={}", tag, request.getRequestURI(), true);
        }
		return true;
	}
	
	 private static final String template = "{\"resultCode\":%1$s,\"resultMsg\":\"%2$s\"}";
	 private static void render(ServletResponse response, int errorCode, String errorMsg) {
        String s = String.format(template, errorCode, errorMsg);
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(s);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void printRequestParams(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("请求：" + request.getRequestURI());
		sb.append("\n");
		Map<String, String[]> paramMap = request.getParameterMap();
		if (!paramMap.isEmpty()) {
		    sb.append("?");
		    for (String key : paramMap.keySet()) {
			    sb.append(key).append("=").append(paramMap.get(key)[0]).append("&");
			}
		    sb.setLength(sb.length()-1);
		    sb.append("\n");
		}
		sb.append("Content-Type：" + request.getContentType());
		sb.append("\n");
		
		sb.append("User-Agent：" + request.getHeader("User-Agent"));
		sb.append("\n");
		
		sb.append("**************************************************");
		sb.append("\n");
		
		if (logger.isInfoEnabled()) {
			logger.info(sb.toString());
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
