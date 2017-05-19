package cn.smbms.service.user;

import java.util.List;

import cn.smbms.pojo.User;

public interface UserService {
	
	/**
	 * 用户登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	public User login(String userCode,String userPassword);
	
	
	/**
	 * 根据条件查询用户列表
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public List<User> getUserList(String queryUserName,int queryUserRole,int currentPageNo, int pageSize);
	/**
	 * 根据条件查询用户表记录数
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public int getUserCount(String queryUserName,int queryUserRole);
	
	public boolean add(User user);
	
	public User selectUserCodeExist(String userCode);
	
}
