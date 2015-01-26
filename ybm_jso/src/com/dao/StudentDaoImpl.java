package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.StudentDao;
import com.pojo.PageBean;
import com.pojo.Parent;
import com.pojo.School;
import com.pojo.Student;
import com.util.StringUtil;

@Repository
public class StudentDaoImpl implements StudentDao {

	private SessionFactory sessionFactory;

	@Override
	public int Save(Student student, Parent parent, School school)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.merge(student);
			session.merge(parent);
			session.merge(school);
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
			System.out.println("系统异常");
			return 0;
		}
		session.close();
		return 1;
	}

	@Override
	public int Del(Student student) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = session
				.createSQLQuery("delete from t_student,t_parent,t_school where student_idcard ="
						+ student.getStudent_idcard() + "");
		int count = query.executeUpdate();
		return count;
	}

	@Override
	public List<Student> studentList(Student student, Parent parent,
			PageBean pageBean) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = null;
		// "select s.id,s.student_idcard,s.student_name,s.student_phone,s.student_email,s.student_address,s.student_sex,p.parent_faname,p.parent_maname from Student as s,Parent as p where s.student_idcard = p.student_idcard");
		StringBuffer hql = new StringBuffer(
				"from Student as s where 1=1 ");
		if (student != null) {
			if (StringUtil.isNotEmpty(student.getStudent_idcard())) {
				hql.append(" and s.student_idcard like '%"
						+ student.getStudent_idcard() + "%'");
			}
			if (StringUtil.isNotEmpty(student.getStudent_name())) {
				hql.append(" and s.student_name like '%"
						+ student.getStudent_name() + "%'");
			}
			if (StringUtil.isNotEmpty(String.valueOf(student.getStudent_sex()))) {
				hql.append(" and s.student_sex = '" + student.getStudent_sex()
						+ "'");
			}
			if (StringUtil.isNotEmpty(String.valueOf(student.getState()))) {
				hql.append(" and s.state = '" + student.getState()
						+ "'");
			}
			if (StringUtil.isNotEmpty(String.valueOf(student.getStudent_origin()))) {
				hql.append(" and s.student_origin = '" + student.getStudent_origin()
						+ "'");
			}
		}
		query = session.createQuery(hql.toString());
		if (pageBean != null) {
//			hql.append(" limit " + pageBean.getStart() + ","
//					+ pageBean.getRows());
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getRows());
		}
		List<Student> all_list = (List<Student>) query.list();
		return all_list;
	}

	@Resource
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {// 获取session
		return sessionFactory.openSession();
	}

	@Override
	public int getStudentTotal() throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		return this.sessionFactory.getCurrentSession().find("from Student")
				.size();
	}

	@Override
	public int savestudent(Student student) throws Exception {
		Session session = this.getSession();
		Transaction tr = session.beginTransaction();
		try {
			session.merge(student);
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
	public Student findByid(int id) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = session.createQuery("from Student where id=?");
		query.setInteger(0, id);
		return (Student)query.uniqueResult();
	}

	@Override
	public int manystates(String ids) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update t_student set state =? where id in("+ids+")";
		String s[] = ids.split(",");
		Session session = this.getSession();
		//System.out.println(sql);
		Query query = session.createSQLQuery(sql);
		query.setString(0, "已审核");
		//query.setParameterList("idList",new Integer[] {Integer.parseInt(s.toString())});
		return query.executeUpdate();
	}

	@Override
	public int DelMangStudents(String idcards) throws Exception {
		// TODO Auto-generated method stub
		String student_sql = "delete from t_student where student_idcard in("+idcards+")";
		Session session = this.getSession();
		Query query = null;
		try {
			query = session.createSQLQuery(student_sql);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("系统异常！");
			e.printStackTrace();
		}
		return query.executeUpdate();
	}
}
