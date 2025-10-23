package poly.edu.interceptor;

import java.util.Date;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import poly.edu.entity.Account;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        Account user = (Account) request.getSession().getAttribute("user");

        if (user != null) { // chỉ log khi user đã đăng nhập
            System.out.println("URI: " + request.getRequestURI() 
                + ", Time: " + new Date() 
                + ", User: " + user.getFullname());
        }

        return true; // cho phép request tiếp tục
    }
}
