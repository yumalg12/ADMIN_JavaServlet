package servlet;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/*")
public class CommonFilter implements Filter {
	ServletContext context;

	public void init (FilterConfig fConfig) throws ServletException{
		System.out.println("UTF-8 인코딩");
		context = fConfig.getServletContext();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		System.out.println("----------------doFilter 호출------------------");
		
		String context = ((HttpServletRequest)request).getContextPath();
		String pathinfo = ((HttpServletRequest)request).getRequestURI();
		
		String msg = "Context 정보: " + context 
				+ "\nURI 정보: " + pathinfo;
		System.out.println(msg);
				
		String[] pathSplits = pathinfo.split("\\.");
		List<String> exceptionFormat = new ArrayList<>();
		exceptionFormat.add("html");
		exceptionFormat.add("css");
		exceptionFormat.add("svg");
		exceptionFormat.add("ico");
		exceptionFormat.add("png");
		exceptionFormat.add("jpg");
		

		if (!exceptionFormat.contains(pathSplits[pathSplits.length - 1]) && !pathinfo.equals("/ADMIN_JavaServlet/")) {
//			System.out.println("필터 적용됨\n");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
		} else {
//			System.out.println("필터 안 적용됨\n");
		}
		
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		System.out.println("destroy 호출");
	}

}
