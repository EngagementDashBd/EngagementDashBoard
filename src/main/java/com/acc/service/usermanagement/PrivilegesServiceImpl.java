package com.acc.service.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.usermanagement.MenuDao;
import com.acc.dto.PrivilegesDTO;

@Service
public class PrivilegesServiceImpl implements PrivilegesService{

	@Autowired
	private MenuDao menuDao;
	
	@Transactional(readOnly=true)
	public List<PrivilegesDTO> getMenuList(List<String> roles) {
		
List<PrivilegesDTO> menuList = new ArrayList<PrivilegesDTO>();
		
		List<PrivilegesDTO> sampleList = menuDao.getMenuByRoles(roles);
		
		Map<Integer, PrivilegesDTO> sampleMap = new HashMap<Integer, PrivilegesDTO>();
		for(PrivilegesDTO sample: sampleList){
			if(sample.getParentId() == 0){
				sampleMap.put(sample.getKey(), sample);
			} else {
				if(sampleMap.get(sample.getParentId()) == null){
					List<PrivilegesDTO> childElements = getChildren(sample.getParentId(),sampleMap);
					if(childElements!=null){
					childElements.add(sample);
					}
				} else {
					sampleMap.get(sample.getParentId()).getChildren().add(sample);
				}
			}
		}
		
		for(int key : sampleMap.keySet()){
			menuList.add(sampleMap.get(key));
		}
		
		return menuList;
	}
	private List<PrivilegesDTO> getChildren(int parentId,
			Map<Integer, PrivilegesDTO> sampleMap) {
		List<PrivilegesDTO> childElements = null;
		for(int key : sampleMap.keySet()){
			PrivilegesDTO privileges = sampleMap.get(key);
			if(null != privileges.getChildren() && privileges.getChildren().size()> 0) {
				childElements = getSubChildElements(parentId,privileges);
				if(childElements != null){
					break;
				}
				
			}
			
		}
		
		return childElements;
	}

	private List<PrivilegesDTO> getSubChildElements(int parentId,PrivilegesDTO privileges){
		List<PrivilegesDTO> childElements = null;
		for(PrivilegesDTO childElement : privileges.getChildren()){
			if(childElement.getKey() == parentId){
				childElements = childElement.getChildren();
				
				break;
			} else {
				if(childElement.getChildren().size() > 0) {
					childElements = getSubChildElements(parentId,childElement);
				}
			}
		}
		return childElements;
	}
}
