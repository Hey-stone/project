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
 *@return 学校信息表
 */
@Entity
@Table(name="t_school")
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name="student_idcard")
	private String student_idcard;//学生身份证号
	@Column(name="school_state")
	private String school_state;//学校类型
	@Column(name="school_name")
	private String school_name;//学校名称
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudent_idcard() {
		return student_idcard;
	}
	public void setStudent_idcard(String student_idcard) {
		this.student_idcard = student_idcard;
	}
	public String getSchool_state() {
		return school_state;
	}
	public void setSchool_state(String school_state) {
		this.school_state = school_state;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public School(int id, String student_idcard, String school_state,
			String school_name) {
		super();
		this.id = id;
		this.student_idcard = student_idcard;
		this.school_state = school_state;
		this.school_name = school_name;
	}
	public School() {
		super();
	}
	
	
}
