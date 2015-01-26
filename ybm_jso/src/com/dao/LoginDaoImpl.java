package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.LoginDao;
import com.pojo.Admin;
import com.pojo.Student;

@Repository
public class LoginDaoImpl implements LoginDao {

	private SessionFactory sessionFactory;
	
	@Override
	public Student studentlogin(Student student) throws Exception {
		Student currentstudent = null;
		Query query = null;
		String hql = "from Student where student_idcard = ?";
		Session session =  this.getSession();
		query = session.createQuery(hql);
		query.setString(0, student.getStudent_idcard());
		List<Student> studentList =(List<Student>) query.list();
		if(studentList.size() > 0){
			currentstudent = studentList.get(0);
		}
		return currentstudent;
	}

	@Override
	public Admin adminlogin(Admin admin) throws Exception {
		Admin currentadmin = null;
		String hql = "from Admin where admin_idten=? and admin_password=?";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setString(0, admin.getAdmin_idten());
		query.setString(1, admin.getAdmin_password());
		List<Admin> adminList = (List<Admin>) query.list();
		if(adminList.size() > 0 ){
			currentadmin = adminList.get(0);
		}
		return currentadmin;
	}
	
	@Resource
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {// 获取session
		return sessionFactory.openSession();
	}

}
