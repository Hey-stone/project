package com.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author stone
 * @return 学生信息.对应数据库中t_studentinfo表
 */
@Entity
@Table(name = "t_student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id; // 学生信息表主键
	@Column(name = "student_name")
	private String student_name; // 学生姓名
	@Column(name = "student_idcard")
	private String student_idcard;// 学生身份证号
	@Column(name = "student_sex")
	private String student_sex;// 学生性别
	@Column(name = "student_phone")
	private String student_phone;// 学生电话号码
	@Column(name = "student_email")
	private String student_email;// 学生Email
	@Column(name = "student_address")
	private String student_address;// 学生地址
	@Column(name = "student_origin")
	private String student_origin;// 学生生源类型
	@Column(name="state")
	private String state;//审核状态

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_idcard() {
		return student_idcard;
	}

	public void setStudent_idcard(String student_idcard) {
		this.student_idcard = student_idcard;
	}

	public String getStudent_sex() {
		return student_sex;
	}

	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}


	public String getStudent_phone() {
		return student_phone;
	}

	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}

	public String getStudent_email() {
		return student_email;
	}

	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}

	public String getStudent_address() {
		return student_address;
	}

	public void setStudent_address(String student_address) {
		this.student_address = student_address;
	}

	public String getStudent_origin() {
		return student_origin;
	}

	public void setStudent_origin(String student_origin) {
		this.student_origin = student_origin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Student(int id, String student_name, String student_idcard,
			String student_sex, String student_phone,
			String student_email, String student_address, String student_origin,String state) {
		super();
		this.id = id;
		this.student_name = student_name;
		this.student_idcard = student_idcard;
		this.student_sex = student_sex;
		this.student_phone = student_phone;
		this.student_email = student_email;
		this.student_address = student_address;
		this.student_origin = student_origin;
		this.state = state;
	}

	public Student() {
		super();
	}

}
