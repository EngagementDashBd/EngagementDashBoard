package com.acc.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Timesheet;
@Repository
public class StatisticsDaoimplementation1  extends AbstractDao implements StatisticsDao1{

	public Map<String, Integer> statistics() throws ClassNotFoundException, SQLException {
		  String[] monthName = { "january", "february", "march", "april", "may", "june", "july",
			        "august", "september", "october", "november", "december" };
		  int aCount = 0, bCount = 0, cCount = 0;
		  String shift=null;
		  Calendar cal = Calendar.getInstance();
		  String month = monthName[cal.get(Calendar.MONTH)];
		  int year = cal.get(Calendar.YEAR);
		  Session session=getSession();
		  Query query=session.createQuery("select e from  Timesheet e where month=:month and year=:year");
		  query.setParameter("month", month);
		  query.setParameter("year", year);
		  List<Timesheet> empList=query.list();
		  for(Timesheet shiftData : empList)
		  {
				shift = shiftData.getShift();
				if("a".equals(shift))
					aCount++;
				if("b".equals(shift))
					bCount++;
				if("c".equals(shift))
					cCount++;				
		  }
		  Map<String,Integer> shiftCount = new HashMap<String, Integer>();	  
		  shiftCount.put("shiftA", aCount);
		  shiftCount.put("shiftB", bCount);
		  shiftCount.put("shiftC", cCount);
		return shiftCount;
	}
}
