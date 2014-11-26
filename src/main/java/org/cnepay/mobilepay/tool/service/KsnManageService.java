package org.cnepay.mobilepay.tool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.cnepay.mobilepay.tool.dao.table.McrKsnDao;
import org.cnepay.mobilepay.tool.views.KsnEntity;
import org.springframework.stereotype.Service;

@Service
public class KsnManageService extends AbstractService{
	
	private McrKsnDao mcrKsnDao = null;
	
	public McrKsnDao getMcrKsnDao() {
		return mcrKsnDao;
	}

	@Resource
	public void setMcrKsnDao(McrKsnDao mcrKsnDao) {
		this.mcrKsnDao = mcrKsnDao;
	}



	public List<KsnEntity> loadKsnByKsnNo(String ksnNo){
		List<KsnEntity> ksns = new ArrayList<KsnEntity>();
		List<Map<String,Object>> ksnDaos = mcrKsnDao.findByKsn(ksnNo);
		if(ksnDaos != null && ksnDaos.size() > 0){
			for(Map<String,Object> item:ksnDaos){
				KsnEntity entity = new KsnEntity();
				renameForKey(entity.getClass(), item);
				copyValueToBean(entity,item);
				ksns.add(entity);
			}
		}
		return ksns;
	}
	
	public int getMobileNoCount(String mobileNo){
		return mcrKsnDao.findMobileCount(mobileNo);
	}
	
	public int deleteMobileMessage(String mobileNo){
		return mcrKsnDao.deleteMobileMessage(mobileNo);
	}
	
	public Boolean unbundKsn(String ksnNo){
		return this.mcrKsnDao.unbundKsn(ksnNo);
	}
	
	public int resetKsnTmk(String ksnNo){
		return this.mcrKsnDao.resetKsnTmk(ksnNo);
	}
	
	public int clearKsnMessage(String ksnNo){
		return this.mcrKsnDao.deleteKsnMessage(ksnNo);
	}
	
	public int sendIcKey(String ksnNo){
		return this.mcrKsnDao.updateICKeyForDownload(ksnNo);
	}
	
	public Boolean unbundMobileNo(String mobileNo){
		return this.mcrKsnDao.unbundMobileNo(mobileNo);
	}
}
