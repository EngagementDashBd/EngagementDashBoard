package com.acc.entity;

import java.awt.List;
import java.util.ArrayList;

public class ResourceTaskHelper {

	
	public ResourceTaskHelper()
	{
		
		
	}
	
	public ResourceTaskHelper(ArrayList<ResourceTaskAndVocation> resourceTaskList) {
		super();
		this.resourceTaskList = resourceTaskList;
	}


	ArrayList<ResourceTaskAndVocation> resourceTaskList = new ArrayList<ResourceTaskAndVocation>();

	public ArrayList<ResourceTaskAndVocation> getResourceTaskList() {
		return resourceTaskList;
	}

	public void setResourceTaskList(ArrayList<ResourceTaskAndVocation> resourceTaskList) {
		this.resourceTaskList = resourceTaskList;
	}
	
	 public void add(ResourceTaskAndVocation rtaskVca) {
	       
	        this.resourceTaskList.add(rtaskVca);
	    }
	
}
