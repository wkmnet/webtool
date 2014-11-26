package org.cnepay.mobilepay.tool.dao;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;


public abstract class AbstractDao{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected JdbcTemplate jdbcTemplate = null;
	
	protected TransactionTemplate transactionTemplate = null;
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	@Resource
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
