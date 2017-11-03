package com.acc.mailer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.acc.mailer.OutlookJACOB;

public class Mailer {
	
	public static void triggerMail(String body, String subject, List<String> recipients, List<String> cCopy)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("subject",subject);
		param.put("body", body);
		//String appPath = request.getSession().getServletContext().getRealPath("Mailer");
	    //String path = appPath.substring(0, appPath.indexOf(".metadata")).replaceAll("\\\\", "/");
	    //param.put("path", path) ;  
		String to[] = new String[2];
		//to[0] = recipients.get(0) + "@accenture.com";
		to[0] = "renga.r.santh.ledge@accenture.com";
		param.put("to", to);
		String cc[] = new String[2];
		cc[0] = "p.p.ramakrishnan@accenture.com";
		param.put("cc",cc);

		OutlookJACOB mail = new OutlookJACOB();
		mail.createEmail(param);
	}

}
