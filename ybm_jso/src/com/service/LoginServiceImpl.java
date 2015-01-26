package com.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.LoginDaoImpl;
import com.inter.LoginService;
import com.pojo.Admin;
import com.pojo.Student;

@Service
public class LoginServiceImpl implements LoginService {

	@Resource
	private LoginDaoImpl logindaoimpl;
	
	@Override
	public Student studentlogin(Student student) throws Exception {
		// TODO Auto-generated method stub
		return logindaoimpl.studentlogin(student);
	}

	@Override
	public Admin adminlogin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return logindaoimpl.adminlogin(admin);
	}

}
