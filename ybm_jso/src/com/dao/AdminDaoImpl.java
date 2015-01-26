package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.AdminDao;
import com.pojo.Admin;
import com.pojo.PageBean;
import com.util.StringUtil;

@Repository
public class AdminDaoImpl implements AdminDao {

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
	public int Save(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.merge(admin);
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("系统异常");
			tr.rollback();
		}
		session.close();
		return 1;
	}

	@Override
	public int Del(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = session.createSQLQuery("delete from t_admin where id ="+admin.getId()+"");
		int count = query.executeUpdate();
		return count;
	}

	@Override
	public List<Admin> all_admin(Admin admin,PageBean pageBean) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = null;
		// "select s.id,s.student_idcard,s.student_name,s.student_phone,s.student_email,s.student_address,s.student_sex,p.parent_faname,p.parent_maname from Student as s,Parent as p where s.student_idcard = p.student_idcard");
		StringBuffer hql = new StringBuffer(
				"from Admin as a where 1=1 ");
		if(admin != null){
			if (StringUtil.isNotEmpty(admin.getAdmin_name())) {
				hql.append(" and a.admin_name like '%"
						+ admin.getAdmin_name() + "%'");
			}
			if (StringUtil.isNotEmpty(admin.getAdmin_idten())) {
				hql.append(" and a.admin_idten like '%"
						+ admin.getAdmin_idten() + "%'");
			}
			if (admin.getAdmin_role() != 3) {
				hql.append(" and a.admin_role like '%"
						+ admin.getAdmin_role() + "%'");
			}
		}
		query = session.createQuery(hql.toString());
		if (pageBean != null) {
//			hql.append(" limit " + pageBean.getStart() + ","
//					+ pageBean.getRows());
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getRows());
		}
		List<Admin> all = (List<Admin>) query.list();
		return all;
	}

	@Override
	public int adminTotal() throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		return this.sessionFactory.getCurrentSession().find("from Admin")
				.size();
	}

	@Override
	public Admin isExist(String admin_idten) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query =  session.createQuery("from Admin where admin_idten ='"+admin_idten+"' ");
		return (Admin) query.uniqueResult();
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from t_admin where id in("+ids+")";
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
