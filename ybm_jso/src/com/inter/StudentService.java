package com.inter;

import java.util.List;

import com.pojo.PageBean;
import com.pojo.Parent;
import com.pojo.School;
import com.pojo.Student;

/**
 * 
 * @author stone
 * @return service层
 */
public interface StudentService {

	/**
	 * 
	 * @param student
	 * @return 保存or修改学生报名信息
	 * @throws Exception
	 */
	public int Save(Student student,Parent parent,School school) throws Exception;
	
	/**
	 * 
	 * @param student
	 * @return 修改学生个人信息
	 * @throws Exception
	 */
	public int savestudent(Student student) throws Exception;
	
	/**
	 * 
	 * @param ids
	 * @return 审批多条信息
	 * @throws Exception
	 */
	public  int manystates(String ids) throws Exception;
	/**
	 * 
	 * @param id
	 * @return 根据id查找。
	 * @throws Exception
	 */
	public Student findByid(int id) throws Exception;
	/**
	 * 
	 * @param student
	 * @return  删除学生信息
	 * @throws Exception
	 */
	public int Del(Student student) throws Exception;
	
	/**
	 * 
	 * @return 查询所有的学生信息
	 * @throws Exception
	 */
	public List<Student> studentList(Student student,Parent parent,PageBean pageBean) throws Exception;
	/**
	 * 
	 * @return 查询一共多少条记录
	 * @throws Exception
	 */
	public int getStudentTotal() throws Exception;
	/**
	 * 
	 * @param idcards
	 * @return 根据idcard删除多条记录
	 * @throws Exception
	 */
	public int DelMangStudents(String idcards)throws Exception;
}

