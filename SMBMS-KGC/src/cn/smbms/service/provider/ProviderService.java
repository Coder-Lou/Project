package cn.smbms.service.provider;

import java.util.List;

import cn.smbms.pojo.Provider;

public interface ProviderService {


	/**
	 * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
	 * @param proName
	 * @return
	 */
	public List<Provider> getProviderList(String proName,String proCode);
	
	/**
	 * 通过proId删除Provider
	 * @param delId
	 * @return
	 */
	public int deleteProviderById(String delId);
	
}
