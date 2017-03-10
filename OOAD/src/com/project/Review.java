package com.project;

import java.util.Date;

public class Review {
	private int reviewId;
	private Customer reviewer;
	private Date createdDate;
	private String postContent;
	private float numStars;
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public Customer getReviewer() {
		return reviewer;
	}
	public void setReviewer(Customer reviewer) {
		this.reviewer = reviewer;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public float getNumStars() {
		return numStars;
	}
	public void setNumStars(float numStars) {
		this.numStars = numStars;
	}
}
