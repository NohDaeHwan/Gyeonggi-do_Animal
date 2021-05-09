package com.jsp.animal.domain.user.dto;

public class JoinReqDto {
	private String username;
	private String password;
	private String email;
	private String roadAddress;
	private String jibunAddress;
	
	public JoinReqDto(String username, String password, String email, String roadAddress, String jibunAddress) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.roadAddress = roadAddress;
		this.jibunAddress = jibunAddress;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getJibunAddress() {
		return jibunAddress;
	}

	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}
	
}
