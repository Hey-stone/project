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
 * @return 管理员实体类
 */
@Entity
@Table(name="t_admin")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id; // 管理员id
	@Column(name="admin_name")
	private String admin_name;// 管理员姓名
	@Column(name="admin_password")
	private String admin_password;// 管理员密码
	@Column(name="admin_role")
	private int admin_role;// 管理员角色
	@Column(name="admin_idten")
	private String admin_idten;//管理员登录账号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public int getAdmin_role() {
		return admin_role;
	}
	public void setAdmin_role(int admin_role) {
		this.admin_role = admin_role;
	}
	
	public String getAdmin_idten() {
		return admin_idten;
	}
	public void setAdmin_idten(String admin_idten) {
		this.admin_idten = admin_idten;
	}
	public Admin(int id, String admin_name, String admin_password,
			int admin_role,String admin_idten) {
		super();
		this.id = id;
		this.admin_name = admin_name;
		this.admin_password = admin_password;
		this.admin_role = admin_role;
		this.admin_idten = admin_idten;
	}
	public Admin() {
		super();
	}

	

}
