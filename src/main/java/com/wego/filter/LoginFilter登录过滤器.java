package com.wego.filter;

import com.wego.constant.WeGoConst;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter登录过滤器 implements Filter {
    /**
     * 存放用户在web.xml中配置的放行的uri
     */
    private String[] uris;

    /**
     * init方法仅在tomcat容器启动时执行一次
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        String fangXingUri = config.getServletContext()
                .getInitParameter("fangXingUri");
        fangXingUri = fangXingUri.replaceAll(" ", "")
                .replaceAll("\n", "");
        uris = fangXingUri.split(",");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        //放行（不拦截）
        for (String item : uris) {
            if (uri.contains(item)) {
                chain.doFilter(request, response);
                return;
            }
        }

        Object obj;
        String loginUri = "";
        HttpSession session = req.getSession();
        if (uri.contains("manager")) {
            //如果是后台
            obj = session.getAttribute(WeGoConst.SESSION_ADMIN);
            loginUri = "/WEB-INF/manager/login.jsp";
        }else{
            //如果是前端
            obj = session.getAttribute(WeGoConst.SESSION_USER);
            loginUri = "/WEB-INF/login.jsp";
        }

        //如果用户登录成功，放行
        if (obj != null) {
            chain.doFilter(request, response);
            return;
        }

        //拦截：凡是用户指定放行的放行，凡是用户已经登录的放行，其它的全部拦截
        request.getRequestDispatcher(loginUri).forward(request, response);
    }
}
