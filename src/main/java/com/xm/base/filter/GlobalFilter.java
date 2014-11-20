package com.xm.base.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


/**
 * 
 * @author jxx
 * 2014年11月8日 下午5:26:50
 */
public class GlobalFilter implements Filter {

	private static final Logger logger=Logger.getLogger(GlobalFilter.class);
	@Override
	public void destroy() {
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	/** (非 Javadoc)
	* 
	* 
	* @param req
	* @param res
	* @param chain
	* @throws IOException
	* @throws ServletException
	* @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	*/
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();
		String path=request.getRequestURI();
		Pattern p = Pattern.compile(".(jsp|jspx|php|asp|aspx)");  
		Matcher m = p.matcher(url);  
		boolean rs = m.find();  
		if(url.equals("/imageUp.jsp")){
			logger.debug("ueditor file upload file!");
			chain.doFilter(req, res);
		}else if(rs){
			logger.debug(".(jsp|jspx|php|asp|aspx) forbidden allow!");
			request.getRequestDispatcher("/404.html").forward(req, res);
		}else{
			logger.debug("normal url allow!");
			chain.doFilter(req, res);
		}
	}

}
