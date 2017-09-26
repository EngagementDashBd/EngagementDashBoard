package com.acc.dao;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.instrument.classloading.tomcat.TomcatLoadTimeWeaver;
import org.springframework.stereotype.Repository;

import com.acc.dto.LoginUser;
import com.acc.entity.ResourceMaster;

@Repository
public class LoginDaoImpl extends AbstractDao implements LoginDao {
	
	private  static final String GET_USER="select employee.enterpriseId,employee.password,employee.active,a.authority  from ResourceMaster employee , Authority a WHERE employee.employeeId=a.employeeId and employee.enterpriseId=:enterpriseId";
@Transactional
	public LoginUser getUser(String username) {
		LoginUser loginUser=new LoginUser();
		Session session=getSession();
		
		Query query=session.createQuery(GET_USER);
		query.setParameter("enterpriseId", username);
		List<String> roles=new ArrayList<String>();
		List<Object[]> resources=query.list();
		int count=0;
		if(!resources.isEmpty()){
			for(Object[] row: resources){
				if(count==0){
			loginUser.setEnterpriseId(String.valueOf(row[0]));
			loginUser.setPassword(String.valueOf(row[1]));
			loginUser.setEnabled((Boolean)row[2]);
				}
			roles.add(String.valueOf(row[3]));
			count++;
		}
			loginUser.setRoles(roles);
		}
			
		return loginUser;
	}

public ResourceMaster loginEmployee(String enterpriseId, String password)
		throws ClassNotFoundException, SQLException {
	
	Session session=getSession();
	Query query=session.createQuery("select e from  ResourceMaster e where e.enterpriseId=:entId and e.password=:pass and e.active=:active");
	query.setParameter("entId", enterpriseId);
	query.setParameter("pass",password);
	query.setParameter("active", true);
	ResourceMaster resource = new ResourceMaster();
	List<ResourceMaster> empList=query.list();
	for(ResourceMaster resources:empList)
	{
		resource = resources;
	}
	return resource;
}
public int signupEmployee(String enterpriseId, String password) throws ClassNotFoundException, SQLException {
	int count = 0;
	Session session=getSession();Query query=session.createQuery("update ResourceMaster r set r.password=:password where r.enterpriseId=:enterpriseid");
	query.setParameter("enterpriseid", enterpriseId);
	query.setParameter("password",password);
	count = query.executeUpdate();
	
	return count;	}
}
