package cn.smbms.dao.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;

public class ProviderDaoImpl implements ProviderDao {


	@Override
	public List<Provider> getProviderList(Connection connection, String proName,String proCode)
			throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Provider> providerList = new ArrayList<Provider>();
		if(connection != null){
			StringBuffer sql = new StringBuffer();
			sql.append("select * from smbms_provider where 1=1 ");
			List<Object> list = new ArrayList<Object>();
			if(!StringUtils.isNullOrEmpty(proName)){
				sql.append(" and proName like ?");
				list.add("%"+proName+"%");
			}
			if(!StringUtils.isNullOrEmpty(proCode)){
				sql.append(" and proCode like ?");
				list.add("%"+proCode+"%");
			}
			Object[] params = list.toArray();
			System.out.println("sql ----> " + sql.toString());
			rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
			while(rs.next()){
				Provider _provider = new Provider();
				_provider.setId(rs.getInt("id"));
				_provider.setProCode(rs.getString("proCode"));
				_provider.setProName(rs.getString("proName"));
				_provider.setProDesc(rs.getString("proDesc"));
				_provider.setProContact(rs.getString("proContact"));
				_provider.setProPhone(rs.getString("proPhone"));
				_provider.setProAddress(rs.getString("proAddress"));
				_provider.setProFax(rs.getString("proFax"));
				_provider.setCreationDate(rs.getTimestamp("creationDate"));
				providerList.add(_provider);
			}
			BaseDao.closeResource(null, pstm, rs);
		}
		return providerList;
	}

	@Override
	public int deleteProviderById(Connection connection, String delId)
			throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		int flag = 0;
		if(null != connection){
			String sql = "delete from smbms_provider where id=?";
			Object[] params = {delId};
			flag = BaseDao.execute(connection, pstm, sql, params);
			BaseDao.closeResource(null, pstm, null);
		}
		return flag;
	}

}
