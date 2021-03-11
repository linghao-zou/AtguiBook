package com.linghao.Filter;

import com.linghao.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author zoulinghao
 * @create 2021-02-08-13:57
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollbackAndClose();
            throw new RuntimeException(e);//把异常再次抛出
        }

    }

    @Override
    public void destroy() {

    }
}
