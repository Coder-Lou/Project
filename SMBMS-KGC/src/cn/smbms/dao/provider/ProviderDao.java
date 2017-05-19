package cn.smbms.dao.provider;

import java.sql.Connection;
import java.util.List;
import cn.smbms.pojo.Provider;

public interface ProviderDao {

	/**
	 * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
	 * @param connection
	 * @param proName
	 * @return
	 * @throws Exception
	 */
	public List<Provider> getProviderList(Connection connection,String proName,String proCode)throws Exception;
	
	/**
	 * 通过proId删除Provider
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public int deleteProviderById(Connection connection,String delId)throws Exception; 
	
}
