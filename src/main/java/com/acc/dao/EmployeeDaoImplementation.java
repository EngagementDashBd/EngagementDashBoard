package com.acc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.entity.Authority;
import com.acc.entity.EmployeeHub;
import com.acc.entity.Location;
import com.acc.entity.ResourceMaster;
import com.acc.entity.RoleName;
import com.acc.entity.TrainingDetails;
import com.acc.entity.TrainingTopics;

@SuppressWarnings("unchecked")
@Repository
public class EmployeeDaoImplementation extends AbstractDao implements EmployeeDao{

	public ResourceMaster searchEmployee(String enterpriseId) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		Query query=session.createQuery("select e from  ResourceMaster e where e.enterpriseId=:entId and e.active=:active");
		query.setParameter("active", true);
		query.setParameter("entId", enterpriseId);
		List<ResourceMaster> empList=query.list();
		ResourceMaster resource = new ResourceMaster();

		for(ResourceMaster resources:empList)
		{
			resource = resources;
		}
		return resource;
	}

	

	

	public ArrayList<ResourceMaster> allEmployeeDetails() throws ClassNotFoundException, SQLException {
		ArrayList<ResourceMaster> allEmployeesData = new ArrayList<ResourceMaster>();
		Session session=getSession();
		Query query=session.createQuery("select e from  ResourceMaster e where e.active=:active");
		query.setParameter("active", true);
		List<ResourceMaster> empList=query.list();
		for(ResourceMaster resource:empList)
		{
			allEmployeesData.add(resource);
		}
		return allEmployeesData;
	}

	public int addNewEmployee(ResourceMaster resource, String creatorName) throws ClassNotFoundException, SQLException {
		Session session=getSession();
		int count = 0;
		Query query = session.createQuery("select e from ResourceMaster e where e.employeeId=:empId or e.enterpriseId=:enterpriseId and e.active=:active");
		query.setParameter("empId", resource.getEmployeeId());
		query.setParameter("enterpriseId", resource.getEnterpriseId());
		query.setParameter("active", true);
		List<ResourceMaster> empList=query.list();
		if(empList.isEmpty())
		{
			String[] roles = resource.getRoles();
			
			ResourceMaster resourceMaster = new ResourceMaster();
			resourceMaster.setEmployeeId(resource.getEmployeeId());
			resourceMaster.setEnterpriseId(resource.getEnterpriseId());
			resourceMaster.setEmployeeName(resource.getEmployeeName());
			resourceMaster.setPassword("login");
			resourceMaster.setLocationId(resource.getLocationId());
			resourceMaster.setLevel(resource.getLevel());
			resourceMaster.setSupervisorId(resource.getSupervisorId());
			resourceMaster.setTechnologyId(resource.getTechnologyId());
			resourceMaster.setDefaultShift("a");
			resourceMaster.setCreatedBy(creatorName);
			resourceMaster.setProjectId(resource.getProjectId());
			resourceMaster.setActive(true);
			resourceMaster.setDefaultPassword(true);
			resourceMaster.setEmployeeStatus("active");
			resourceMaster.setHcscMailId(resource.getHcscMailId());
			resourceMaster.setRoleOffDate(resource.getRoleOffDate());
			resourceMaster.setRoleOnDate(resource.getRoleOnDate());
			session.save(resourceMaster);
			for(int i = 0; i < roles.length; i++)
			{
				Authority authority = new Authority();
				authority.setEmployeeId(resource.getEmployeeId().toString());
				authority.setAuthority(roles[i].toUpperCase());
				session.save(authority);
			}
			count = 1;
			try {
				
				Query query1 = session.createQuery("select t from TrainingTopics t where t.active=:active");
				query1.setParameter("active", true);
				
				List<TrainingTopics> topicList = query1.list();
				for (TrainingTopics trainingtopics : topicList) {
					TrainingDetails trainingDetails = new TrainingDetails();
					trainingDetails.setenterpriseId(resource.getEnterpriseId());
					trainingDetails.setTopicId(trainingtopics.getTopicId());
					trainingDetails.setCompletionStatus(false);
					trainingDetails.setslideNumber(1);
					session.save(trainingDetails);

				}
			} catch (Exception e) {
				count = 0;
				e.printStackTrace();
			}
		}
		
		return count;
	}

	public ArrayList<ResourceMaster> allSupervisorDetails() throws ClassNotFoundException, SQLException {
		Session session = getSession();
		ArrayList<ResourceMaster> supervisors = new ArrayList<ResourceMaster>();
		// Query query=session.createQuery("select e from ResourceMaster e where
		// e.designation=:tl or e.designation=:am or e.designation=:m and
		// e.active=:active ");
		Query query = session
				.createQuery("select e from  ResourceMaster e where e.careerLevel>=9 and e.active=:active ");
		// query.setParameter("tl", "TL");
		// query.setParameter("am", "AM");
		// query.setParameter("m", "M");
		query.setParameter("active", true);
		List<ResourceMaster> supervisorList = query.list();
		for (ResourceMaster resource : supervisorList) {
			supervisors.add(resource);
		}

		return supervisors;
	}

	public int deleteEmployee(String enterpriseId) throws ClassNotFoundException, SQLException {
		Session session = getSession();
		int count = 0;
		Query query = session
				.createQuery("update ResourceMaster e set e.active=:active where e.enterpriseId=:enterpriseId  ");
		query.setParameter("enterpriseId", enterpriseId);
		query.setParameter("active", false);
		count = query.executeUpdate();
		return count;
	}

	public int updateEmployee(ResourceMaster resource, String creatorName) throws ClassNotFoundException, SQLException {
		Session session = getSession();
		int count = 0;
		Query query = session.createQuery(
				"update ResourceMaster r set  r.level=:careerLevel,r.supervisorId=:supervisorId, r.technologyId=:technologyId, r.projectId=:projectId where r.employeeId=:employeeId");
		query.setParameter("supervisorId", resource.getSupervisorId());
		query.setParameter("careerLevel", resource.getLevel());
		query.setParameter("technologyId", resource.getTechnologyId());
		query.setParameter("projectId", resource.getProjectId());
		query.setParameter("employeeId", resource.getEmployeeId());
		count = query.executeUpdate();
		count = 1;
		return count;
	}

	public ArrayList<ResourceMaster> approve(Long employeeId) throws ClassNotFoundException, SQLException {
		ArrayList<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		Session session = getSession();
		Query query = session
				.createQuery("select e from  ResourceMaster e where e.supervisorId=:supId and e.active=:active");
		query.setParameter("supId", employeeId);
		query.setParameter("active", true);
		List<ResourceMaster> empList = query.list();
		for (ResourceMaster resource : empList) {
			employeeObjects.add(resource);
		}
	

	return employeeObjects;
	}

	public List<ResourceMaster> getEmployeeDetailsByProject(Integer projectId)
			throws ClassNotFoundException, SQLException {
		Session session = getSession();
		List<ResourceMaster> employeeObjects = new ArrayList<ResourceMaster>();
		Query query = session
				.createQuery("select e from ResourceMaster e where projectId=:projId and e.active=:active");
		query.setParameter("projId", projectId);
		query.setParameter("active", true);
		employeeObjects = query.list();
		return employeeObjects;
	}

	public int insertPersonaldetails(Long ContactNo, String PassportNo, String PanNo, String enterpriseId)
			throws ClassNotFoundException, SQLException {
		int count;
		Session session = getSession();
		Query query = session.createQuery(
				"update ResourceMaster r set r.contactNo=:contactNo,r.passportNo=:passportNo,r.panNo=:panNo where r.enterpriseId=:enterpriseid");
		query.setParameter("contactNo", ContactNo);
		query.setParameter("passportNo", PassportNo);
		query.setParameter("panNo", PanNo);
		query.setParameter("enterpriseid", enterpriseId);

		count = query.executeUpdate();
		return count;

	}

	public int recommendToHoldEmployee(Long employeeId, String potentialFutureRole, Date hubStartDate, String creator)
			throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session = getSession();
		Session session1 = getSession();
		Query query1 = session.createQuery(
				"update EmployeeProject e set e.recommendToHold=:recommendToHold where e.employeeId=:employeeId");
		query1.setParameter("employeeId", employeeId);
		query1.setParameter("recommendToHold", true);
		count = query1.executeUpdate();
		/*Query query3 = session.createQuery(
				"update ResourceMaster e set e.employeeStatus=:employeeStatus where e.employeeId=:employeeId");
		query3.setParameter("employeeId", employeeId);
		query3.setParameter("employeeStatus", "held");
		count = query3.executeUpdate();*/
		EmployeeHub employeeHub = new EmployeeHub();
		employeeHub.setEmployeeId(employeeId);
		employeeHub.setHubStartDate(hubStartDate);
		employeeHub.setPotentialFutureRole(potentialFutureRole);
		employeeHub.setCreatedBy(creator);
		session1.save(employeeHub);
		return count;
	}

	public int changePassword(ResourceMaster resource, String password) {
		int count = 0;
		Session session = getSession();
		Query query = session.createQuery(
				"update ResourceMaster e set e.password=:password, e.defaultPassword=:defaultPassword where e.employeeId=:employeeId");
		query.setParameter("password", password);
		query.setParameter("defaultPassword", false);
		query.setParameter("employeeId", resource.getEmployeeId());
		count = query.executeUpdate();
		return count;
	}
	public int updateSupervisor(Long employeeId, Long supervisorId) throws ClassNotFoundException, SQLException {
		int count = 0;
		Session session = getSession();
		Query query = session
				.createQuery("update ResourceMaster r set r.supervisorId=:supervisorId where r.employeeId=:employeeId");
		query.setParameter("supervisorId", supervisorId);
		query.setParameter("employeeId", employeeId);
		count = query.executeUpdate();
		return count;
	}
public List<String> checkSupervisor(String Name, int careerLevel) throws ClassNotFoundException, SQLException {
		Session session = getSession();
		Query query;
		List<String> superVisorList = new ArrayList<String>();
		if(careerLevel == 0)
		{
			query = session.createQuery("select e from  ResourceMaster e where e.enterpriseId LIKE :eid");
			query.setParameter("eid", '%' + Name + '%');
			
		}
		else
		{
		query = session.createQuery("select e from  ResourceMaster e where e.enterpriseId LIKE :eid and ( e.level <=:careerLevel )");
		query.setParameter("eid", '%' + Name + '%');
		query.setParameter("careerLevel", careerLevel - 1);
		}
		List<ResourceMaster> employeeList = query.list();
		for (ResourceMaster resource : employeeList) {
			superVisorList.add(resource.getEnterpriseId());
		}

		return superVisorList;
	}
public List<Location> getAllLocations() {
	Session session = getSession();
	Query query = session.createQuery("select e from Location e");
	List<Location> locations = query.list();
	return locations;
}

public String getSupervisorName(Long supervisorId) throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
			String supervisorName = "";
			List<ResourceMaster> employee = new ArrayList<ResourceMaster>();
			Session session = getSession();
			Query query = session
					.createQuery("select e from  ResourceMaster e where e.employeeId=:employeeId and e.active=:active");
			query.setParameter("employeeId", supervisorId);
			query.setParameter("active", true);
			employee = query.list();
			for (ResourceMaster resource : employee) {
				supervisorName = resource.getEmployeeName();
				System.out.println(supervisorName);
			}

			return supervisorName;
}

public String getEmployeeEntId(Long employeeId)  {
	// TODO Auto-generated method stub
			String employeeEntId = "";
			List<ResourceMaster> employee = new ArrayList<ResourceMaster>();
			Session session = getSession();
			Query query = session
					.createQuery("select e from  ResourceMaster e where e.employeeId=:employeeId and e.active=:active");
			query.setParameter("employeeId", employeeId);
			query.setParameter("active", true);
			employee = query.list();
			for (ResourceMaster resource : employee) {
				employeeEntId = resource.getEnterpriseId();
				}

			return employeeEntId;
}


public int holdOrRoleOff(Long employeeId, String potentialFutureRole, Date roleOffDate) {
	Session session=getSession();
	int count = 0;
	if(roleOffDate == null)
	{
		Query query1 = session.createQuery(
				"update EmployeeProject e set e.recommendToHold=:recommendToHold where e.employeeId=:employeeId");
		query1.setParameter("employeeId", employeeId);
		query1.setParameter("recommendToHold", true);
		count = query1.executeUpdate();
		Query query2 = session.createQuery(
				"update EmployeeHub e set e.potentialFutureRole=:potentialFutureRole where e.employeeId=:employeeId");
		query2.setParameter("employeeId", employeeId);
		query2.setParameter("potentialFutureRole", potentialFutureRole);
		count = query2.executeUpdate();
		/*Query query3 = session.createQuery(
				"update ResourceMaster e set e.employeeStatus=:employeeStatus where e.employeeId=:employeeId");
		query3.setParameter("employeeId", employeeId);
		query3.setParameter("employeeStatus", "held");
		count = query3.executeUpdate();*/
	}
	else
	{
		Query query1 = session.createQuery(
				"update ResourceMaster e set e.roleOffDate=:roleOffDate where e.employeeId=:employeeId");
		query1.setParameter("employeeId", employeeId);
		query1.setParameter("roleOffDate", roleOffDate);
		count = query1.executeUpdate();
		
		Query query3 = session.createQuery(
				"update EmployeeHub e set e.potentialFutureRole=:potentialFutureRole where e.employeeId=:employeeId");
		query3.setParameter("employeeId", employeeId);
		query3.setParameter("potentialFutureRole", potentialFutureRole);
		count = query3.executeUpdate();
		
		Query query2 = session.createQuery(
				"update EmployeeProject e set e.recommendToHold=:recommendToHold where e.employeeId=:employeeId");
		query2.setParameter("employeeId", employeeId);
		query2.setParameter("recommendToHold", false);
		count = query2.executeUpdate();
		
		/*Query query3 = session.createQuery(
				"update ResourceMaster e set e.employeeStatus=:employeeStatus where e.employeeId=:employeeId");
		query3.setParameter("employeeId", employeeId);
		query3.setParameter("employeeStatus", "idle");
		count = query3.executeUpdate();*/
	}
	return count;
}

public int updateContact(Long employeeId, Long contactNo) throws ClassNotFoundException, SQLException {
	Session session=getSession();
	int count = 0;
	Query query=session.createQuery("update ResourceMaster e set e.contactNo=:contactNo where e.employeeId=:employeeId  ");
	query.setParameter("employeeId", employeeId);
	query.setParameter("contactNo", contactNo);
	count = query.executeUpdate();
	return count;	
}
public EmployeeHub getEmployeeOnHub(Long employeeId) throws ClassNotFoundException, SQLException {
	Session session=getSession();
	Query query=session.createQuery("select e from  EmployeeHub e where e.employeeId=:employeeId");
	query.setParameter("employeeId", employeeId);
	
	EmployeeHub employeehub=new EmployeeHub();
	List<EmployeeHub> employeesHubs=query.list();
	for(EmployeeHub employeeHubs:employeesHubs)
	{
		employeehub = employeeHubs;
	}
	return employeehub;
}

public Integer getEmpLocationIdByName(String locationName) {
	Integer locationId = null;
	Session session=getSession();
	Query query=session.createQuery("select e from  Location e where e.locationName=:locationName");
	query.setParameter("locationName", locationName);
	List<Location> locationList=query.list();
	for(Location location : locationList)
	{
		locationId = location.getLocationId().intValue();
	}
	return locationId;
}

public Integer uploadEmployeePicture(byte[] imageData, Long employeeId) {
	Session session=getSession();
	int count = 0;
	Query query=session.createQuery("update ResourceMaster e set e.employeePhoto=:employeePhoto where e.employeeId=:employeeId  ");
	query.setParameter("employeePhoto", imageData);
	query.setParameter("employeeId", employeeId);
	count = query.executeUpdate();

	return count;
}
public List<RoleName> getAllRoleNames() {
	Session session = getSession();
	Query query = session.createQuery("select e from RoleName e");
	List<RoleName> roleNames = query.list();
	return roleNames;
}





public List<String> getRoles(Long employeeId) {
	Session session = getSession();
	List<String> roles = new ArrayList<String>();
	Query query = session.createQuery("select e from Authority e where e.employeeId=:empId");
	query.setParameter("empId", employeeId.toString());
	List<Authority> authority = query.list();
	for(Authority a : authority)
		roles.add(a.getAuthority());
	return roles;
	
}


}
 