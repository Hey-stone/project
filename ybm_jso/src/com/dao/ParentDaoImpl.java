package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.ParentDao;
import com.pojo.Parent;

@Repository
public class ParentDaoImpl implements ParentDao {

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
	public int Save(Parent parent) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.merge(parent);
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
			System.out.println("系统异常");
			return 0;
		}
		session.close();
		return 1;
	}

	@Override
	public int Del(Parent parent) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = session
				.createSQLQuery("delete from t_parent where student_idcard ="
						+ parent.getStudent_idcard() + "");
		int count = query.executeUpdate();
		return count;
	}

	@Override
	public List<Parent> all_parent(Parent parent) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = session.createQuery("from Parent");
		List<Parent> all_list = (List<Parent>) query.list();
		return all_list;
	}

	@Override
	public Parent one_parent(String idcard) throws Exception {
		// TODO Auto-generated method stub
		Session session =  this.getSession();
		Query query =  session.createQuery("from Parent where student_idcard ='"+idcard + "' ");
		Parent parent =(Parent) query.uniqueResult();
		return parent;
	}

	@Override
	public int DelMangParents(String idcards) throws Exception {
		String sql = "delete from t_parent where student_idcard in("+idcards+")";
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
