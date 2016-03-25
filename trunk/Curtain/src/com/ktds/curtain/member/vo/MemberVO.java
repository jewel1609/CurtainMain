package com.ktds.curtain.member.vo;

public class MemberVO {
	private String studentEmail;
	private int memberTypeId;
	private int univId;
	private int majorId;
	private String signupDate;
	private String nickName;
	private String secondEmail;
	private int point;
	private String rankModifyDate;
	private int Activity;
	private String password;
	private int majorGroupId;
	
	public int getMajorGroupId() {
		return majorGroupId;
	}
	public void setMajorGroupId(int majorGroupId) {
		this.majorGroupId = majorGroupId;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
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
	public int getActivity() {
		return Activity;
	}
	public void setActivity(int activity) {
		Activity = activity;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
