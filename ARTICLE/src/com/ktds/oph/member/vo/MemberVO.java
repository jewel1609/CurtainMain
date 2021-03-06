package com.ktds.oph.member.vo;

public class MemberVO {
	
	private String email;
	private int memberTypeId;
	private int univId;
	private int majorId;
	private String signupDate;
	private String nickName;
	private String secondEmail;
	private int point;
	private String rankModifyDate;
	private String password;
	private String phoneNumber;
	private String companyName;
	private int majorGroupId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMemberTypeId() {
		return memberTypeId;
	}
	public void setMemberTypeId(int memberTypeId) {
		this.memberTypeId = memberTypeId;
	}
	public int getUnivId() {
		return univId;
	}
	public void setUnivId(int univId) {
		this.univId = univId;
	}
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	public String getSignupDate() {
		return signupDate;
	}
	public void setSignupDate(String signupDate) {
		this.signupDate = signupDate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSecondEmail() {
		return secondEmail;
	}
	public void setSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getRankModifyDate() {
		return rankModifyDate;
	}
	public void setRankModifyDate(String rankModifyDate) {
		this.rankModifyDate = rankModifyDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getMajorGroupId() {
		return majorGroupId;
	}
	public void setMajorGroupId(int majorGroupId) {
		this.majorGroupId = majorGroupId;
	}
	
	public boolean isAdmin() {
		if ( this.memberTypeId == 6) {
			return true;
		}
		return false;
	}
}
