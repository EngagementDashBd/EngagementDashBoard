package com.acc.dao;

import java.sql.SQLException;
import java.util.Map;

public interface StatisticsDao1 {
	public  Map<String,Integer> statistics()throws ClassNotFoundException,SQLException;
	

}
