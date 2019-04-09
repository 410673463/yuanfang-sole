package com.pt.zh.yuanfang.common.utils;

import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Security相关操作
 * @author Louis
 * @date Nov 20, 2018
 */
public class SecurityUtils {

	/**
	 * 系统登录认证
	 * @param request
	 * @param username
	 * @param password
	 * @param authenticationManager
	 * @return
	 */
//	public static JwtAuthenticatioToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager) {
//		JwtAuthenticatioToken token = new JwtAuthenticatioToken(username, password);
//		token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//		// 执行登录认证过程
//	    Authentication authentication = authenticationManager.authenticate(token);
//	    // 认证成功存储认证信息到上下文
//	    SecurityContextHolder.getContext().setAuthentication(authentication);
//		// 生成令牌并返回给客户端
//	    token.setToken(JwtTokenUtils.generateToken(authentication));
//		return token;
//	}

	/**
	 * 获取令牌进行认证
	 * @param request
	 */
//	public static void checkAuthentication(HttpServletRequest request) {
//		// 获取令牌并根据令牌获取登录认证信息
//		Authentication authentication = JwtTokenUtils.getAuthenticationeFromToken(request);
//		// 设置登录认证信息到上下文
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//	}

	/**
	 * 获取当前用户名
	 * @return
	 */
	public static String getUsername() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		SysUser user = (SysUser) request.getAttribute("sysUser");
		return user.getName();
	}
	
	/**
	 * 获取用户名
	 * @return
	 */
	/*public static String getUsername(Authentication authentication) {
		String username = null;
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal != null && principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			}
		}
		return username;
	}*/
	
	/**
	 * 获取当前登录信息
	 * @return
	 */
//	public static Authentication getAuthentication() {
//		if(SecurityContextHolder.getContext() == null) {
//			return null;
//		}
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		return authentication;
//	}
	
}
