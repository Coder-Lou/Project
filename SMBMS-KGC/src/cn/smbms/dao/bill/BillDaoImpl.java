package cn.smbms.dao.bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;

public class BillDaoImpl implements BillDao {

	@Override
	public int getBillCountByProviderId(Connection connection, String providerId)
			throws Exception {
		// TODO Auto-generated method stub
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if(null != connection){
			String sql = "select count(1) as billCount from smbms_bill where" +
					"	providerId = ?";
			Object[] params = {providerId};
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			if(rs.next()){
				count = rs.getInt("billCount");
			}
			BaseDao.closeResource(null, pstm, rs);
		}
		
		return count;
	}
}
