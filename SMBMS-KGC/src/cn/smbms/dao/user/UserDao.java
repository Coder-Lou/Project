package cn.smbms.dao.user;

import java.sql.Connection;
import java.util.List;

import cn.smbms.pojo.User;

public interface UserDao {
	
	/**
	 * 通过userCode获取User
	 * @param connection
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public User getLoginUser(Connection connection,String userCode)throws Exception;
	
	/**
	 * 通过条件查询-userList
	 */
	public List<User> getUserList(Connection connection,String userName,int userRole,
			int currentPageNo,int pageSize)throws Exception;
	
	
	/**
	 * 通过条件查询-用户表的记录数
	 */
	public int getUserCount(Connection connection,String userName,int userRole)throws Exception;
	
	
	/**
	 * 增加用户信息
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(Connection connection,User user)throws Exception;
	
}
