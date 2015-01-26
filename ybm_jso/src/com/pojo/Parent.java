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
 * @return 学生父母实体类
 */
@Entity
@Table(name="t_parent")
public class Parent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;// 主键
	@Column(name="student_idcard")
	private String student_idcard;// 学生身份证号
	@Column(name="parent_faname")
	private String parent_faname;// 父姓名
	@Column(name="parent_maname")
	private String parent_maname;// 母姓名
	@Column(name="parent_workid")
	private String parent_workid;// 父母工作号
	@Column(name="parent_unit")
	private String parent_unit;// 父母工作单位
	@Column(name="parent_address")
	private String parent_address;// 父母工作地址
	@Column(name="parent_call")
	private String parent_call;// 父母电话
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
	public String getParent_faname() {
		return parent_faname;
	}
	public void setParent_faname(String parent_faname) {
		this.parent_faname = parent_faname;
	}
	public String getParent_maname() {
		return parent_maname;
	}
	public void setParent_maname(String parent_maname) {
		this.parent_maname = parent_maname;
	}
	public String getParent_workid() {
		return parent_workid;
	}
	public void setParent_workid(String parent_workid) {
		this.parent_workid = parent_workid;
	}
	public String getParent_unit() {
		return parent_unit;
	}
	public void setParent_unit(String parent_unit) {
		this.parent_unit = parent_unit;
	}
	public String getParent_address() {
		return parent_address;
	}
	public void setParent_address(String parent_address) {
		this.parent_address = parent_address;
	}
	public String getParent_call() {
		return parent_call;
	}
	public void setParent_call(String parent_call) {
		this.parent_call = parent_call;
	}
	
	public Parent(int id, String student_idcard, String parent_faname,
			String parent_maname, String parent_workid, String parent_unit,
			String parent_address, String parent_call) {
		super();
		this.id = id;
		this.student_idcard = student_idcard;
		this.parent_faname = parent_faname;
		this.parent_maname = parent_maname;
		this.parent_workid = parent_workid;
		this.parent_unit = parent_unit;
		this.parent_address = parent_address;
		this.parent_call = parent_call;
	}
	public Parent() {
		super();
	}

	
}
