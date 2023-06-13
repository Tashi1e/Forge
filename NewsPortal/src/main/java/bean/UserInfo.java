package bean;

import java.io.Serializable;

public class UserInfo implements Serializable{

	private static final long serialVersionUID = -1421192482328889996L;
	
	private String nickName;
	private String email;
	private String country;
	private String phoneNumber;
	private String password;
	private String role;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserInfo [nickName=" + nickName + ", email=" + email + ", country=" + country + ", phoneNumber="
				+ phoneNumber + ", password=" + password + ", role=" + role + "]";
	}
	
}
