package controller;

import com.sun.xml.internal.ws.wsdl.writer.document.Service;

import service.member.LoginService;

public class ServiceMapper {
	
	private static ServiceMapper instance = new ServiceMapper();
	private ServiceMapper() {}
	public static ServiceMapper getInstance() {
		if (instance == null) {
			instance = new ServiceMapper();
		}
		return instance;
	}
	
	public Service getService(String ser) {
		Service service = null;
		switch(ser) {
		case "login.do" :
			service = (Service) new LoginService();
			
			break;
		}
		return service;
	}

}
