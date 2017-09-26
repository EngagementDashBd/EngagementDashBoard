package com.acc.dto;

import java.io.Serializable;
import java.util.List;
public class LoginUser  implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 2514225798265113040L;
private String empId;
private String username;
private String password;
private boolean enabled;
private String enterpriseId;
private List<String> roles;
public String getEmpId() {
	return empId;
}
public void setEmpId(String empId) {
	this.empId = empId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
public List<String> getRoles() {
	return roles;
}
public void setRoles(List<String> roles) {
	this.roles = roles;
}
public String getEnterpriseId() {
	return enterpriseId;
}
public void setEnterpriseId(String enterpriseId) {
	this.enterpriseId = enterpriseId;
}



}
