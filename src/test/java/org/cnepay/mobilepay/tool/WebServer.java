package org.cnepay.mobilepay.tool;


public class WebServer {

	public static void main(String[] args) {
		String jetty_home = "jettytest";//这个就是你的项目发布时候的名字  
        try {  
               /*Server server = new Server();  
               Connector connector = new ServerCon;  
               connector.setPort(9999);  
               server.setConnectors(new Connector[] { connector });  
               WebAppContext webapp = new WebAppContext();  
               webapp.setContextPath("/"+jetty_home);//上下文路径 比如说/jettytest  
               webapp.setResourceBase("./WebRoot");//你的资源文件所在的路径 一般都在这下面   
               webapp.setDefaultsDescriptor("./WebRoot/etc/webdefault.xml");  
               server.setHandler(webapp);  
               server.start();  
               server.join();  */
           } catch (Exception e) {  
               e.printStackTrace();  
           }  
	}
}
