package com.acc.mailer;

import java.util.Map;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class OutlookJACOB
{
    private ActiveXComponent ol;
    private Dispatch outlook;
    private Object mapi[] = new Object[1];
    //private Object email[] = new Object[1];

    public OutlookJACOB()
    {
        mapi[0] = "MAPI";
    }

    public void createEmail(Map<String, Object> params)
    {
    	String path = (String)params.get("path");
    	String dllPath = "AttendanceTracker/src/main/resources/jacob-1.18-M2-x64.dll";
    	String entirePath = path.concat(dllPath);
    	//System.setProperty("jacob.dll.path", entirePath);
    	System.setProperty("jacob.dll.path", "C:/Users/renga.r.santh.ledge/Documents/Mailing Code/jacob-1.18-M2/jacob-1.18-M2/jacob-1.18-M2-x64.dll");
    	ol = new ActiveXComponent("Outlook.Application");
        outlook = ol.getObject();
        Dispatch.call(outlook,"GetNamespace",mapi).toDispatch();
        Dispatch mail = Dispatch.invoke(outlook, "CreateItem", Dispatch.Get, new Object[] { "0" }, new int[0]).toDispatch();
        
        Dispatch.put(mail, "Subject", params.get("subject"));
        Dispatch.put(mail, "Body", params.get("body"));

        String to[] = (String[]) params.get("to");
        String attachments[] = (String[]) params.get("attachments");

        if(to != null)
        {
            if(to.length>0)
            {
                String _to = "";

                for(Object t : to)
                {
                	if(null != t)
                		_to += t + ";";
                		
                }

                Dispatch.put(mail, "To", _to);
            }
        }

        if(attachments != null)
        {
            if(attachments.length>0)
            {
                Dispatch attachs = Dispatch.get(mail, "Attachments").toDispatch();

                for(Object attachment : attachments)
                {
                    Dispatch.call(attachs, "Add", attachment);
                }
            }
        }
        Dispatch.call(mail, "Send");
    }
}
