package com.inter;

import java.util.List;

import com.pojo.Parent;

public interface ParentService {

	/**
	 * 
	 * @param idcard
	 * @return 根据idcard 查询
	 * @throws Exception
	 */
	public Parent one_parent(String idcard)throws Exception;
	
	/**
	 * 
	 * @param parent
	 * @return 家长信息的保存或者修改
	 * @throws Exception
	 */
	public int Save(Parent parent) throws Exception;

	/**
	 * 
	 * @param parent
	 * @return 家长信息的删除
	 * @throws Exception
	 */
	public int Del(Parent parent) throws Exception;

	/**
	 * 
	 * @param parent
	 * @return 查询家长的信息
	 * @throws Exception
	 */
	public List<Parent> all_parent(Parent parent) throws Exception;
	/**
	 * 
	 * @param idcards
	 * @return 根据idcard删除多条记录
	 * @throws Exception
	 */
	public int DelMangParents(String idcards)throws Exception;
}
