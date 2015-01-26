package com.inter;

import java.util.List;

import com.pojo.Admin;
import com.pojo.PageBean;

public interface AdminService {

	/**
	 * 
	 * @param admin
	 * @return 添加管理员或者修改管理员
	 * @throws Exception
	 */
	public int Save(Admin admin) throws Exception;

	/**
	 * 
	 * @param admin
	 * @return 删除管理员
	 * @throws Exception
	 */
	public int Del(Admin admin) throws Exception;

	/**
	 * 
	 * @return 查询所有的管理员信息
	 * @throws Exception
	 */
	public List<Admin> all_admin(Admin admin,PageBean pageBean) throws Exception;
	/**
	 * 
	 * @return 查询admin的总记录数
	 * @throws Exception
	 */
	public int adminTotal()throws Exception;
	/**
	 * 
	 * @param admin_idten
	 * @return 是否存在该账号的admin
	 * @throws Exception
	 */
	public Admin isExist(String admin_idten)throws Exception;
	/**
	 * 
	 * @param ids
	 * @return 根据ids删除数据
	 * @throws Exception
	 */
	public int delete(String ids)throws Exception;
}
