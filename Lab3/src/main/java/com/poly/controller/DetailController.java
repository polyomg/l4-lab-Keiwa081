package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.model.Staff;

@Controller
public class DetailController {
	@RequestMapping("/poly/staff-detail")
	public String detail(Model model) {
		Staff staff = Staff.builder().id("annttts01299@fpt.edu.vn").fullname("Nguyễn Thái Thiên An").level(2)
//	.photo("staff.png")
				.build();
		model.addAttribute("staff", staff);
		return "/poly/staff-detail";
	}
}
