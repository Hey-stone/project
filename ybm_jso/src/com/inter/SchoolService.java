package com.inter;

import java.util.List;

import com.pojo.School;

/**
 * 
 * @author stone
 * @return 学习相关操作的service层 接口
 */
public interface SchoolService {

	/**
	 * 
	 * @param school
	 * @return 学校的保存或者修改
	 * @throws Exception
	 */
	public int Save(School school) throws Exception;

	/**
	 * 
	 * @param school
	 * @return 学校的删除
	 * @throws Exception
	 */
	public int Del(School school) throws Exception;

	/**
	 * 
	 * @param school
	 * @return 没有特殊条件查询所有的学校，或者按照学校类型查询
	 * @throws Exception
	 */
	public List<School> allschool(School school) throws Exception;
	/**
	 * 
	 * @param idcard
	 * @return 根据idcard 查询
	 * @throws Exception
	 */
	public School one_school(String idcard)throws Exception;
	/**
	 * 
	 * @param idcards
	 * @return 根据idcard删除多条记录
	 * @throws Exception
	 */
	public int DelMangSchools(String idcards)throws Exception;
}
