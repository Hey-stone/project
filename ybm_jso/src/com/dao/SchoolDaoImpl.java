package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.SchoolDao;
import com.pojo.School;

@Repository
public class SchoolDaoImpl implements SchoolDao {

	private SessionFactory sessionFactory;
	@Resource
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {// 获取session
		return sessionFactory.openSession();
	}
	
	@Override
	public int Save(School school) throws Exception {
		// TODO Auto-generated method stub
		Session session =  this.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.merge(school);
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("系统异常");
			tr.rollback();
			return 0;
		}
		session.close();
		return 1;
	}

	@Override
	public int Del(School school) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = session.createSQLQuery("delete from t_school where student_idcard ="+school.getStudent_idcard()+"");
		int count = query.executeUpdate();
		return count;
	}

	@Override
	public List<School> allschool(School school) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = session.createQuery("from School");
		List<School> all_list = (List<School>) query.list();
		return all_list;
	}

	@Override
	public School one_school(String idcard) throws Exception {
		// TODO Auto-generated method stub
		Session session =  this.getSession();
		Query query =  session.createQuery("from School where student_idcard ='"+idcard+"' ");
		School school = (School) query.uniqueResult();
		return school;
	}

	@Override
	public int DelMangSchools(String idcards) throws Exception {
		String sql = "delete from t_school where student_idcard in("+idcards+")";
		Session session = this.getSession();
		Query query = null;
		try {
			query = session.createSQLQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("系统异常！");
			e.printStackTrace();
		}
		return query.executeUpdate();
	}

}
