package org.cnepay.mobilepay.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试 
//@ContextConfiguration({"/applicationContext.xml"}) //加载配置文件
public abstract class AbstractInitParams implements ApplicationContextAware{
	
	private static Logger logger = LoggerFactory.getLogger(AbstractInitParams.class);
	
	private ApplicationContext applicationContext = null;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		logger.info("创建ApplicationContext:" + applicationContext);
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
