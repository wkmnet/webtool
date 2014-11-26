package org.cnepay.mobilepay.tool;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class Test {

public void test(String page,String count){
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://zftopen.vcpos.cn/wahaha");
		method.addParameters(new NameValuePair[]{new NameValuePair("p",page),new NameValuePair("max",count)});
		try {
			int status = client.executeMethod(method);
			System.out.println(status);
			System.out.println(method.getResponseBodyAsString());
			JSONObject jsonObject = JSONObject.fromObject(method.getResponseBodyAsString());
			if(jsonObject.getBoolean("isSuccess")){
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				System.out.println(jsonArray.toString());
			};
			method.releaseConnection();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
		String s = "中";
		System.out.println(s.getBytes("UTF-8").length);
		System.out.println(s.getBytes("GBK").length);
	}
}
