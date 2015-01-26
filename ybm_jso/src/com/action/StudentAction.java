package com.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;

import com.inter.LoginService;
import com.inter.ParentService;
import com.inter.SchoolService;
import com.inter.StudentService;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.PageBean;
import com.pojo.Parent;
import com.pojo.School;
import com.pojo.Student;
import com.util.ResponseUtil;
import com.util.StringUtil;

@Scope("prototype")
@Namespace("/")
@Action(value = "student", results = {
		@Result(name = "success", type = "redirect", location = "/student.jsp"),
		@Result(name = "error", location = "/login.jsp"),
		@Result(name = "info", type = "redirect", location = "/student.jsp") })
public class StudentAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	@Resource
	private StudentService studentservice;
	@Resource
	private ParentService parentservice;
	@Resource
	private SchoolService schoolservice;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Student student;
	private School school;
	private Parent parent;
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public String add() throws Exception {
		HttpSession session = request.getSession();
		// 保存操作应该是一步完成，所以应该把这三个保存放在一起，同理删除操作也是。
		parent.setStudent_idcard(student.getStudent_idcard());
		school.setStudent_idcard(student.getStudent_idcard());
		student.setState("未审核");
		if (studentservice.Save(student, parent, school) == 1) {
			error = "success";
			session.setAttribute("student", student);
			session.setAttribute("parent", parent);
			session.setAttribute("school", school);
			session.setAttribute("error", error);
			return "success";
		} else {
			error = "error";
			request.setAttribute("error", error);
			return "error";
		}
	}

	/**
	 * 
	 * @return 判断身份证是否可以使用（在提交之前）
	 * @throws Exception
	 */
	public String isable() throws Exception {
		JSONObject result = new JSONObject();
		String idcard = request.getParameter("id");
		School isExist = schoolservice.one_school(idcard);
		if (isExist == null) {// 说明此身份证号不存在可以添加
			result.put("success", "true");
			result.put("errorMsg", "此身份证可以使用!");
		} else {
			result.put("success", "false");
			result.put("errorMsg", "此身份证已被注册!");
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 
	 * @return 一级管理员添加学生信息
	 * @throws Exception
	 */
	public String admin_addstudent() throws Exception {
		JSONObject result = new JSONObject();
		parent.setStudent_idcard(student.getStudent_idcard());
		school.setStudent_idcard(student.getStudent_idcard());
		student.setState("未审核");
		if (studentservice.Save(student, parent, school) == 1) {
			result.put("success", "true");
		} else {
			result.put("success", "true");
			result.put("errorMsg", "操作失败");
			ResponseUtil.write(response, result);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 
	 * @return 查询所有的学生信息
	 * @throws Exception
	 */
	public String studentList() throws Exception {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		// 查询时获取以下信息
		String idcard = request.getParameter("student_idcard");
		String name = request.getParameter("student_name");
		String student_sex = request.getParameter("student_sex");
		String student_origin = request.getParameter("student_origin");
		String state = request.getParameter("state");
		if (StringUtil.isNotEmpty(idcard) || StringUtil.isNotEmpty(state)
				|| StringUtil.isNotEmpty(student_origin)
				|| StringUtil.isNotEmpty(student_sex)
				|| StringUtil.isNotEmpty(name)) {
			student = new Student();
			student.setStudent_idcard(idcard);
			student.setState(state);
			student.setStudent_origin(student_origin);
			student.setStudent_sex(student_sex);
			student.setStudent_name(name);
		}
		PageBean pageBean = null;
		if (page != null && rows != null) {
			pageBean = new PageBean(Integer.parseInt(page),
					Integer.parseInt(rows));
		}
		JSONObject result = new JSONObject();
		List list = studentservice.studentList(student, parent, pageBean);
		int total = studentservice.getStudentTotal();
		result.put("rows", list);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 
	 * @return 审核单条记录
	 * @throws Exception
	 */
	public String state() throws Exception {
		String id = request.getParameter("id");
		student = studentservice.findByid(Integer.parseInt(id));
		student.setState("已审核");
		int saveNums = 0;
		JSONObject result = new JSONObject();
		saveNums = studentservice.savestudent(student);
		if (saveNums == 1) {
			result.put("success", "true");
		} else {
			result.put("success", "true");
			result.put("errorMsg", "操作失败");
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 
	 * @return 审核多条信息
	 * @throws Exception
	 */
	public String manystates() throws Exception {
		String ids = request.getParameter("ids");
		int delNums = 0;
		JSONObject result = new JSONObject();
		delNums = studentservice.manystates(ids);
		if (delNums > 0) {
			result.put("success", "true");
			result.put("delNums", delNums);
		} else {
			result.put("errorMsg", "操作失败！");
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 
	 * @return 查询学生的详细资料
	 * @throws Exception
	 */
	public String info() throws Exception {
		String idcard = request.getParameter("id");
		JSONObject result = new JSONObject();
		parent = parentservice.one_parent(idcard);
		school = schoolservice.one_school(idcard);
		result.put("parent", parent);
		result.put("school", school);
		result.put("success", "true");
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 
	 * @return 学生信息的修改
	 * @throws Exception
	 */
	public String modify() throws Exception {
		JSONObject result = new JSONObject();
		String id = request.getParameter("id");
		Student modifystudent = studentservice.findByid(Integer.parseInt(id));
		student.setId(modifystudent.getId());
		Parent modifyparent = parentservice.one_parent(student.getStudent_idcard());
		parent.setId(modifyparent.getId());
		School modifyschool = schoolservice.one_school(student.getStudent_idcard());
		school.setId(modifyschool.getId());
		parent.setStudent_idcard(student.getStudent_idcard());
		school.setStudent_idcard(student.getStudent_idcard());
		if(studentservice.Save(student, parent, school) == 1){
			result.put("success", true);
		}else{
			result.put("success", false);
			result.put("errorMsg", "保存失败！");
		}
		return null;
	}
	
	public String Delete()throws Exception{
		String ids = request.getParameter("ids");
		String idcards = "'" + ids.replace(",", "','") + "'";
		int delNums = 0;
		JSONObject result = new JSONObject();
		delNums = studentservice.DelMangStudents(idcards);
		if (delNums == parentservice.DelMangParents(idcards) && delNums== schoolservice.DelMangSchools(idcards)) {
			result.put("success", "true");
			result.put("delNums", delNums);
		} else {
			result.put("errorMsg", "操作失败！");
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
}
