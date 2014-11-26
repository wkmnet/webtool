package org.cnepay.mobilepay.tool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.cnepay.mobilepay.tool.dao.table.AccountDao;
import org.cnepay.mobilepay.tool.views.AccountEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountManageService extends AbstractService{
	
	
	private AccountDao accountDao = null;
	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	@Resource
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public List<AccountEntity> loadAccountNo(String accountNo){
		List<AccountEntity> accounts = new ArrayList<AccountEntity>();
		List<Map<String,Object>> accountDaos = accountDao.loadAccount(accountNo);
		if(accountDaos != null && accountDaos.size() > 0){
			for(Map<String,Object> item:accountDaos){
				AccountEntity entity = new AccountEntity();
				renameForKey(entity.getClass(), item);
				copyValueToBean(entity,item);
				accounts.add(entity);
			}
		}
		return accounts;
	}
	
	public int unbundAccountCount(String accountNo){
		return this.accountDao.unbundAccountCount(accountNo);
	}
	
	public boolean existBankCard(String accountNo){
		return this.accountDao.existBankCard(accountNo);
	}
	
	public boolean unbundAccountNo(String accountNo){
		return this.accountDao.unbundAccount(accountNo);
	}

}
