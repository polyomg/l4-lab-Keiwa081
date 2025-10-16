package poly.edu.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;

	/**
	 * Đọc chuỗi giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public String getString(String name, String defaultValue) {
		String value = request.getParameter(name);
		return (value != null) ? value : defaultValue;
	}

	/**
	 * Đọc số nguyên giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public int getInt(String name, int defaultValue) {
		try {
			String value = request.getParameter(name);
			return (value != null) ? Integer.parseInt(value) : defaultValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Đọc số thực giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public double getDouble(String name, double defaultValue) {
		try {
			String value = request.getParameter(name);
			return (value != null) ? Double.parseDouble(value) : defaultValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Đọc giá trị boolean của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = request.getParameter(name);
		if (value == null)
			return defaultValue;
		return value.equalsIgnoreCase("true") || value.equals("1") || value.equalsIgnoreCase("on");

	}

	/**
	 * Đọc giá trị thời gian của tham số
	 * 
	 * @param name    tên tham số
	 * @param pattern là định dạng thời gian
	 * @return giá trị tham số hoặc null nếu không tồn tại
	 * @throws RuntimeException lỗi sai định dạng
	 */
	public Date getDate(String name, String pattern) {
		try {
			String value = request.getParameter(name);
			if (value == null)
				return null;
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(value);
		} catch (ParseException e) {
			throw new RuntimeException("Sai dinh dang thoi gian tham so" + name);
		}

	}

	/**
	 * Lưu file upload vào thư mục
	 * 
	 * @param file chứa file upload từ client
	 * @param path đường dẫn tính từ webroot
	 * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
	 * @throws RuntimeException lỗi lưu file
	 */
	public File save(MultipartFile file, String path) {
		String realPath = request.getServletContext().getRealPath(path);
		File dir = new File(realPath);
		if (!dir.exists())
			dir.mkdirs();
		try {
			File savedFile = new File(dir, file.getOriginalFilename());
			file.transferTo(savedFile);
			return savedFile;
		} catch (IOException e) {
			throw new RuntimeException("Lỗi lưu file: " + e.getMessage());
		}

	}
}
