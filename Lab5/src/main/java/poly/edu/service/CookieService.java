package poly.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	/**
	 * Đọc cookie từ request
	 * 
	 * @param name tên cookie cần đọc
	 * @return đối tượng cookie đọc được hoặc null nếu không tồn tại
	 */
	public Cookie get(String name) {
		if (request.getCookies() == null)
			return null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equalsIgnoreCase(name)) {
				return cookie;
			}
		}
		return null;
	}

	/**
	 * Đọc giá trị của cookie từ request
	 * 
	 * @param name tên cookie cần đọc
	 * @return chuỗi giá trị đọc được hoặc rỗng nếu không tồn tại
	 */
	public String getValue(String name) {
		Cookie cookie = get(name);
		return (cookie != null) ? cookie.getValue() : "";
	}

	/**
	 * Tạo và gửi cookie về client
	 * 
	 * @param name  tên cookie
	 * @param value giá trị cookie
	 * @param hours thời hạn (giờ)
	 * @return đối tượng cookie đã tạo
	 */
	public Cookie add(String name, String value, int hours) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(hours * 10 * 60);
		response.addCookie(cookie);
		return cookie;
	}

	/**
	 * Xóa cookie khỏi client
	 * 
	 * @param name tên cookie cần xóa
	 */
	public void remove(String name) {
		Cookie cookie = get(name);
		if (cookie != null) {
			cookie.setValue("");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
}
