package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.AdminDaoImpl;
import com.inter.AdminService;
import com.pojo.Admin;
import com.pojo.PageBean;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminDaoImpl admindaoimpl;
	@Override
	public int Save(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return admindaoimpl.Save(admin);
	}

	@Override
	public int Del(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return admindaoimpl.Del(admin);
	}

	@Override
	public List<Admin> all_admin(Admin admin,PageBean pageBean) throws Exception {
		// TODO Auto-generated method stub
		return admindaoimpl.all_admin(admin,pageBean);
	}

	@Override
	public int adminTotal() throws Exception {
		// TODO Auto-generated method stub
		return admindaoimpl.adminTotal();
	}

	@Override
	public Admin isExist(String admin_idten) throws Exception {
		// TODO Auto-generated method stub
		return admindaoimpl.isExist(admin_idten);
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return admindaoimpl.delete(ids);
	}

}
