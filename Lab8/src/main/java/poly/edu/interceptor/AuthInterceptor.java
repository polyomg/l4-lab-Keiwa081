package poly.edu.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import poly.edu.entity.Account;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();
        session.setAttribute("securityUri", uri); // lưu URL người dùng đang cố truy cập

        Account user = (Account) session.getAttribute("user");

        // Kiểm tra đăng nhập
        if (user == null) {
            session.setAttribute("message", "Vui lòng đăng nhập để tiếp tục!");
            response.sendRedirect("/auth/login2");
            return false;
        }

        // Kiểm tra quyền admin
        if (uri.startsWith("/admin") && !user.isAdmin()) {
            session.setAttribute("message", "Bạn không có quyền truy cập trang quản trị!");
            response.sendRedirect("/auth/login2");
            return false;
        }

        return true;
    }
}
