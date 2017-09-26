package com.acc.dao.usermanagement;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acc.dao.AbstractDao;
import com.acc.dto.PrivilegesDTO;

@Repository
public class MenuDaoImpl extends AbstractDao implements MenuDao {

	String queryString="select *from menu where id in(select menu_id  from  menu_role   where role_id in(select id from roles where name in(:roles)))";
	public List<PrivilegesDTO> getMenuByRoles(List<String> roles) {
		Session session=getSession();
		List<PrivilegesDTO> privileges=new ArrayList<PrivilegesDTO>();
		SQLQuery query=session.createSQLQuery(queryString);
		query.setParameterList("roles", roles);
		List<Object[]> menus=query.list();
		for(Object[] menu:menus){
			PrivilegesDTO privilegesDTO=new PrivilegesDTO();
			privilegesDTO.setKey((Integer)menu[0]);
			privilegesDTO.setTitle((String)menu[1]);
			if(menu[2]!=null){
			privilegesDTO.setParentId((Integer)menu[2]);
			}
			if(menu[3]!=null){
			privilegesDTO.setMenuAction((String)menu[3]);
			}
			privileges.add(privilegesDTO);
		}
		return privileges;
	}

}
