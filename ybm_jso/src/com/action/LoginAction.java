package com.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;

import com.inter.LoginService;
import com.inter.ParentService;
import com.inter.SchoolService;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Admin;
import com.pojo.Parent;
import com.pojo.School;
import com.pojo.Student;

@Scope("prototype")
@Namespace("/")
@Action(value = "login", results = {
		@Result(name = "success", type = "redirect", location = "/student_info.jsp"),
		@Result(name = "error", location = "/login.jsp"),
		@Result(name = "input", location = "/admin.jsp"),
		@Result(name = "main",type = "redirect",  location = "/admin_main.jsp"),
		@Result(name = "info", type = "redirect", location = "/student.jsp") })
public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	@Resource
	private LoginService loginservice;
	@Resource
	private ParentService parentService;
	@Resource
	private SchoolService schoolservice;
	private Admin admin;
	private Student student;
	private String message;
	private String imageCode;

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	HttpServletRequest request;
	HttpServletResponse response;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	//学生登录action
	public String student() throws Exception {
		HttpSession session = request.getSession();
		PrintWriter out =  response.getWriter();
		response.setContentType("text/plain");
		Student currentstudent = loginservice.studentlogin(student);
		if (imageCode.equals(session.getAttribute("sRand"))) {
			if (currentstudent == null) {// 数据中没有学生的id信息
				session.setAttribute("student", student);
				out.close();
				return null;
			} else {//数据中有记录
				Parent parent = parentService.one_parent(student.getStudent_idcard());
				School school = schoolservice.one_school(student.getStudent_idcard());
				session.setAttribute("student", currentstudent);
				session.setAttribute("parent", parent);
				session.setAttribute("school", school);
				message = "student";
				out.print(message);
				return null;
			}
		}else{
			//request.getRequestDispatcher("login.jsp").forward(request,response);
			message = "Sorry,Please enter the correct verification code";
			out.print(message);
			out.close();
			return null;
		}

	}

	//管理员登录action
	public String admin() throws Exception {
		HttpSession session = request.getSession();
		Admin currentadmin = loginservice.adminlogin(admin);
		if (imageCode.equals(session.getAttribute("sRand"))) {
			if (currentadmin == null) {// 管理员账号或者密码不对
				//PrintWriter out = response.getWriter(); 此句话在页面返回时容易引起乱码
				return "input";
			} else {
				session.setAttribute("admin", currentadmin);
				return "main";
			}
		}
		return "input";
	}
	
	//学生退出登录
	public String logout()throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("student");
		session.removeAttribute("parent");
		session.removeAttribute("school");
		return "error";
	}
	//admin退出登录
		public String admin_logout()throws Exception{
			HttpSession session = request.getSession();
			session.removeAttribute("admin");
			return "input";
		}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
}
