package cn.ly.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 过滤去  控制编码格式
 * @author Administrator
 *
 */
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	//取xml的值 
		String encoding = filterConfig.getInitParameter("encoding");
		//放入域中间
		filterConfig.getServletContext().setAttribute("encoding", encoding);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//放行
	chain.doFilter(request, response);
		
	}

}
