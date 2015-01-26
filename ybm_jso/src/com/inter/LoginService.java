package com.inter;

import com.pojo.Admin;
import com.pojo.Student;

/**
* 
* @author stone
* @return 学生登录，管理员登录
*/
public interface LoginService {

	/**
	 * 
	 * @param student
	 * @return 学生登录（数据中没有idcard信息，则跳转到学生信息添加。如果有返回学生的报名信息）
	 * @throws Exception
	 */
	public Student studentlogin(Student student) throws Exception;

	/**
	 * 
	 * @param admin
	 * @return 管理员登录（根据role判断管理员的权限）
	 * @throws Exception
	 */
	public Admin adminlogin(Admin admin) throws Exception;
}
