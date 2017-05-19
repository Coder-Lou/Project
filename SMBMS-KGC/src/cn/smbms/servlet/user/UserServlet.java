package cn.smbms.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.role.RoleServiceImpl;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

public class UserServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method = request.getParameter("method");
		
		if(method != null && method.equals("query")){
			this.query(request, response);
		}else if (method != null && method.equals("add")){
			this.add(request, response);
		}else if (method != null && method.equals("getrolelist")){
			this.getRoleList(request, response);
		}else if (method != null && method.equals("ucexist")){
			this.userCodeExist(request, response);
		}
	}
	
	private void getRoleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Role> roleList = null;
		RoleService roleService = new RoleServiceImpl();
		roleList = roleService.getRoleList();
		//把roleList转换成json对象输出
		response.setContentType("application/json");
		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.write(JSONArray.toJSONString(roleList));
		outPrintWriter.flush();
		outPrintWriter.close();
	}
	
	private void userCodeExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//判断用户账号是否可用
		String userCode = request.getParameter("userCode");
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(userCode)){
			resultMap.put("userCode", "exist");
		}else{
			UserService userService = new UserServiceImpl();
			User user = userService.selectUserCodeExist(userCode);
			if(null != user){
				resultMap.put("userCode", "exist");
			}else{
				resultMap.put("userCode", "noexist");
			}
		}
		response.setContentType("application/json");
		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.write(JSONArray.toJSONString(resultMap));
		outPrintWriter.flush();
		outPrintWriter.close();
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String userCode = request.getParameter("userCode");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String userRole = request.getParameter("userRole");
		User user = new User();
		user.setUserCode(userCode);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setAddress(address);
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setGender(Integer.valueOf(gender));
		user.setPhone(phone);
		user.setUserRole(Integer.valueOf(userRole));
		user.setCreationDate(new Date());
		user.setCreatedBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
		
		UserService userService = new UserServiceImpl();
		if(userService.add(user)){
			response.sendRedirect(request.getContextPath()+"jsp/user.do?method=query");
		}else{
			request.getRequestDispatcher("useradd.jsp").forward(request, response);
		}
	}
	
	private void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//查询用户列表
		String queryUserName = request.getParameter("queryname");
		String temp = request.getParameter("queryUserRole");
		String pageIndex = request.getParameter("pageIndex");
		int queryUserRole = 0;
		UserService userService = new UserServiceImpl();
		List<User> userList = null;
		//设置页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		int currentPageNo = 1;
		System.out.println("queryUserName servlet----> " + queryUserName);
		System.out.println("queryUserRole servlet----> " + queryUserRole);
		System.out.println("pageSize servlet----> " + pageSize);
		
		/**
		 * http://localhost:8090/SMBMS/jsp/user.do?method=query&queryname=
		 * 
		 * http://localhost:8090/SMBMS/jsp/user.do?method=query
		 */
		if(queryUserName == null){
			queryUserName = "";
		}
		if(temp != null && !temp.equals("")){
			queryUserRole = Integer.parseInt(temp);
		}
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				response.sendRedirect("error.jsp");
			}
		}
		//总数量（表）
		int totalCount = userService.getUserCount(queryUserName, queryUserRole);
		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		
		//控制首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		
		userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
		request.setAttribute("userList", userList);
		List<Role> roleList = null;
		RoleService roleService = new RoleServiceImpl();
		roleList = roleService.getRoleList();
		request.setAttribute("roleList", roleList);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("currentPageNo", currentPageNo);
		request.setAttribute("queryUserName", queryUserName);
		request.setAttribute("queryUserRole", queryUserRole);
		request.getRequestDispatcher("userlist.jsp").forward(request, response);
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
