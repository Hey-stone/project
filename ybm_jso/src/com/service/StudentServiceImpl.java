package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.StudentDaoImpl;
import com.inter.StudentService;
import com.pojo.PageBean;
import com.pojo.Parent;
import com.pojo.School;
import com.pojo.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDaoImpl studentdaoimpl;
	@Override
	public int Save(Student student,Parent parent,School school) throws Exception {
		// TODO Auto-generated method stub
		return studentdaoimpl.Save(student,parent,school);
	}

	@Override
	public int Del(Student student) throws Exception {
		// TODO Auto-generated method stub
		return studentdaoimpl.Del(student);
	}

	@Override
	public List<Student> studentList(Student student,Parent parent,PageBean pageBean) throws Exception {
		// TODO Auto-generated method stub
		return studentdaoimpl.studentList(student, parent, pageBean);
	}

	@Override
	public int getStudentTotal() throws Exception {
		// TODO Auto-generated method stub
		return studentdaoimpl.getStudentTotal();
	}

	@Override
	public int savestudent(Student student) throws Exception {
		// TODO Auto-generated method stub
		return studentdaoimpl.savestudent(student);
	}

	@Override
	public Student findByid(int id) throws Exception {
		// TODO Auto-generated method stub
		return studentdaoimpl.findByid(id);
	}

	@Override
	public int manystates(String ids) throws Exception {
		// TODO Auto-generated method stub
		return studentdaoimpl.manystates(ids);
	}

	@Override
	public int DelMangStudents(String idcards) throws Exception {
		// TODO Auto-generated method stub
		return studentdaoimpl.DelMangStudents(idcards);
	}

}
