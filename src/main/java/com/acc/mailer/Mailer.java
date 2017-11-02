package com.acc.mailer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.acc.mailer.OutlookJACOB;

public class Mailer {
	
	public static void triggerMail(HttpServletRequest request, String body, String subject, String recipient)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("subject",subject);
		param.put("body", body);
		String appPath = request.getSession().getServletContext().getRealPath("Mailer");
		System.out.println(appPath);
	    String path = appPath.substring(0, appPath.indexOf(".metadata")).replaceAll("\\\\", "/");
	    param.put("path", path) ;  
		String attachment[] = new String[2];
		attachment[0] = "C:/Test1.pdf";
		attachment[1] = "C:/Test2.pdf";
		//param.put("attachments", attachment);

		String to[] = new String[2];
		//to[0] = recipient + "@accenture.com";
		to[0] = "renga.r.santh.ledge@accenture.com";
		param.put("to", to);

		OutlookJACOB mail = new OutlookJACOB();
		mail.createEmail(param);
	}

}
