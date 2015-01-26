package com.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;

import com.inter.AdminService;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Admin;
import com.pojo.PageBean;
import com.util.ResponseUtil;
import com.util.StringUtil;

@Scope("prototype")
@Namespace("/")
@Action(value = "admin", results = {
		@Result(name = "success", type = "redirect", location = "/student.jsp"),
		@Result(name = "error", location = "/login.jsp"),
		@Result(name = "info", type = "redirect", location = "/student.jsp") })
public class AdminAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	@Resource
	private AdminService adminService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Admin admin;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	/**
	 * 
	 * @return 管理员的全部信息
	 * @throws Exception
	 */
	public String adminlist() throws Exception {
		// 分页信息
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		// 获取以下查询信息
		String admin_name = request.getParameter("admin_name");
		String admin_idten = request.getParameter("admin_idten");
		String admin_role = request.getParameter("admin_role");
		if (StringUtil.isNotEmpty(admin_name)
				|| StringUtil.isNotEmpty(admin_idten)
				|| StringUtil.isNotEmpty(admin_role)) {
			admin = new Admin();
			admin.setAdmin_name(admin_name);
			admin.setAdmin_idten(admin_idten);
			admin.setAdmin_role(Integer.parseInt(admin_role));
		}
		PageBean pageBean = null;
		if (page != null && rows != null) {
			pageBean = new PageBean(Integer.parseInt(page),
					Integer.parseInt(rows));
		}
		JSONObject result = new JSONObject();
		List list = adminService.all_admin(admin, pageBean);
		int total = adminService.adminTotal();
		result.put("rows", list);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 
	 * @return 验证管理员账号是否可以使用
	 * @throws Exception
	 */
	public String isable() throws Exception {
		JSONObject result = new JSONObject();
		String idten = request.getParameter("id");
		Admin isExist = adminService.isExist(idten);
		if (isExist == null) {// 说明此账号可以使用
			result.put("success", "true");
			result.put("errorMsg", "此账号可以使用!");
		} else {
			result.put("success", "false");
			result.put("errorMsg", "此账号已被注册,请重新选择!");
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 
	 * @return 添加管理员
	 * @throws Exception
	 */
	public String add() throws Exception {
		JSONObject result = new JSONObject();
		if (adminService.Save(admin) == 1) {
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
	 * @return 管理员信息查询
	 * @throws Exception
	 */
	public String info() throws Exception {
		JSONObject result = new JSONObject();
		String idten = request.getParameter("id");
		Admin isadmin = adminService.isExist(idten);
		result.put("admin", isadmin);
		result.put("success", "true");
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 
	 * @return 管理员修改
	 * @throws Exception
	 */
	public String modify() throws Exception {
		JSONObject result = new JSONObject();
		String id = request.getParameter("id");
		admin.setId(Integer.parseInt(id));
		if (adminService.Save(admin) == 1) {
			result.put("success", true);
		} else {
			result.put("success", false);
			result.put("errorMsg", "修改失败！");
		}
		return null;
	}

	public String Delete() throws Exception {
		JSONObject result = new JSONObject();
		String ids = request.getParameter("ids");
		String id = "'" + ids.replace(",", "','") + "'";
		int delNums = 0;
		delNums = adminService.delete(id);
		if (delNums > 0) {
			result.put("success", "true");
			result.put("delNums", delNums);
		} else {
			result.put("errorMsg", "操作失败！");
		}
		ResponseUtil.write(response, result);
		return null;
	}
}