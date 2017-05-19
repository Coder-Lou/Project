package cn.smbms.pojo;

import java.util.Date;

public class User {
	private int id; //id 
	private String userCode; //用户编码
	private String userName; //用户名称
	private String userPassword; //用户密码
	private int gender;  //性别
	private Date birthday;  //出生日期
	private String phone;   //电话
	private String address; //地址
	private int userRole;    //用户角色
	private int createdBy;   //创建者
	private Date creationDate; //创建时间
	private int modifyBy;     //更新者
	private Date modifyDate;   //更新时间
	
	private int age;//年龄
	
	private String userRoleName;    //用户角色名称
	
	
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public int getAge() {
		/*long time = System.currentTimeMillis()-birthday.getTime();
		int age = Long.valueOf(time/365/24/60/60/1000).intValue();*/
		Date date = new Date();
		int age = date.getYear()-birthday.getYear();
		return age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
