package cn.smbms.dao.bill;

import java.sql.Connection;
import java.util.List;

import cn.smbms.pojo.Bill;

public interface BillDao {


	/**
	 * 根据供应商ID查询订单数量
	 * @param connection
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public int getBillCountByProviderId(Connection connection,String providerId)throws Exception;

}
