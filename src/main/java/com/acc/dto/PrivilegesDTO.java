package com.acc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class PrivilegesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int key;
	private String title;
	private int parentId;
	private String menuAction;
	private List<PrivilegesDTO> children;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonIgnore
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<PrivilegesDTO> getChildren() {
		if(children == null){
			children = new ArrayList<PrivilegesDTO>();
		}
		return children;
	}

	public void setChildren(List<PrivilegesDTO> children) {
		this.children = children;
	}

	
	public String getMenuAction() {
		return menuAction;
	}

	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction;
	}

	@Override
	public String toString() {
		return "PrivilegesDTO [key=" + key + ", title=" + title + ", parentId="
				+ parentId + ", children=" + children + "]";
	}

}
