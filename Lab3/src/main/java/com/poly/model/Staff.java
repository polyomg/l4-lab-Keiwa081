package com.poly.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
	private String id;
	private String fullname;
	@Default
	private String photo = "staff.png";
	@Default
	private Boolean gender = true;
	@Default
	private Date birthday = new Date();
	@Default
	private Double salary = 12345.6789;
	@Default
	private Integer level = 0;
}