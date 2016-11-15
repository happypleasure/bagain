package org.bagain.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Hello world!
 *
 */
public class ReqUtil {
	
	public static String userTokenK(String token){
		return Constants.CacheKPre.Token+":"+token;
	}
	
	public static int getUserId() {
        return getUserId(getToken());
	}
	
	public static int getUserId(String token) {
		if (StringUtils.isBlank(token)) {
			return 0;
		}
		String userId = RedisUtils.get(userTokenK(token));
		return StringUtils.isEmpty(userId) ? 0 : Integer.parseInt(userId);
	}

	public static String getToken() {
    	if (getRequest() == null) {
    		return "";
    	}
        return getRequest().getParameter("access_token");
    }
	
	public static  HttpServletRequest getRequest() {
		if (RequestContextHolder.getRequestAttributes() == null) {
    		return null;
    	}
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
