package org.cnepay.mobilepay.tool.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.math.NumberUtils;
import org.cnepay.mobilepay.tool.dao.table.MessageValidateDao;
import org.cnepay.mobilepay.tool.util.CalendarUtil;
import org.cnepay.mobilepay.tool.views.MessageValidateInfoEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageValidateService extends AbstractService{
	
	
	private MessageValidateDao messageValidateDao = null;
	
	public MessageValidateDao getMessageValidateDao() {
		return messageValidateDao;
	}

	@Resource
	public void setMessageValidateDao(MessageValidateDao messageValidateDao) {
		this.messageValidateDao = messageValidateDao;
	}

	public List<MessageValidateInfoEntity> loadMessageValidateCode(String rownum){
		List<MessageValidateInfoEntity> messageValidates = new ArrayList<MessageValidateInfoEntity>();
		List<Map<String,Object>> messages = this.messageValidateDao.loadMessageValidateCode(rownum);
		if(messages != null && messages.size() > 0){
			for(Map<String,Object> item:messages){
				MessageValidateInfoEntity entity = new MessageValidateInfoEntity();
				renameForKey(entity.getClass(), item);
				copyValueToBean(entity,item);
				messageValidates.add(entity);
			}
		}
		return messageValidates;
	}
	
	public List<MessageValidateInfoEntity> loadFactMessageValidateCode(String rownum){
		List<MessageValidateInfoEntity> messageValidates = new ArrayList<MessageValidateInfoEntity>();
		if(!NumberUtils.isNumber(rownum)){
			return messageValidates;
		}
		int row = NumberUtils.toInt(rownum,100);
		int pages = row % 15 == 0 ? row/15:row/15 + 1;
		for(int p = 1;p < pages + 1;p++){
			JSONArray array = getFactMessageValidateCodes(String.valueOf(p),String.valueOf(15));
			if(array != null && !array.isEmpty()){
				for(int index = 0;index < array.size();index++){
					JSONObject temp = array.getJSONObject(index);
					MessageValidateInfoEntity entity = new MessageValidateInfoEntity();
					entity.setKsnNo(temp.getString("ksn"));
					entity.setMobileNo(temp.getString("mobile"));
					entity.setValidateCode(temp.getString("code"));
					entity.setUpdateTime(new Timestamp(CalendarUtil.parse(temp.getString("date"))));
					entity.setStatus(temp.getString("status"));
					entity.setValidateStatus(temp.getString("registerStatus"));
					entity.setGetCount(String.valueOf(Integer.MIN_VALUE));
					entity.setValidateCount(String.valueOf(Integer.MIN_VALUE));
					messageValidates.add(entity);
				}
			}
		}
		return messageValidates;
	}
	
	private JSONArray getFactMessageValidateCodes(String page,String count){

		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://zftopen.vcpos.cn/wahaha");
		method.addParameters(new NameValuePair[]{new NameValuePair("p",page),new NameValuePair("max",count)});
		try {
			int status = client.executeMethod(method);
			logger.info("返回交易状态：" + status);
			String body = method.getResponseBodyAsString();
			if(status == 200){
				logger.info("接收到的返回数据：" + body);
				JSONObject jsonObject = JSONObject.fromObject(body);
				if(jsonObject.getBoolean("isSuccess")){
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					return jsonArray;
				} else {
					logger.error("服务器返回错误信息：" + jsonObject.toString());
				}
			} else {
				logger.error("错误的返回数据：" + body);
			}
			method.releaseConnection();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			logger.error("HttpException请求：" + e.getMessage(),e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("url请求IOException：" + e.getMessage(),e);
		}
		return null;
	}
}
