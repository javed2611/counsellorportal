package com.counsellorportal.dto;

public class DashboardResponse {

	private Integer totalEnq;
	private Integer openEnq;
	private Integer enrolled;
	private Integer lost;
	public Integer getTotalEnq() {
		return totalEnq;
	}
	public void setTotalEnq(Integer totalEnq) {
		this.totalEnq = totalEnq;
	}
	public Integer getOpenEnq() {
		return openEnq;
	}
	public void setOpenEnq(Integer openEnq) {
		this.openEnq = openEnq;
	}
	public Integer getEnrolled() {
		return enrolled;
	}
	public void setEnrolled(Integer enrolled) {
		this.enrolled = enrolled;
	}
	public Integer getLost() {
		return lost;
	}
	public void setLost(Integer lost) {
		this.lost = lost;
	}
	
	
}
