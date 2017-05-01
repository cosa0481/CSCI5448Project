package com.project;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="review")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id", nullable=false, unique=true)
	private int reviewId;
	
	@Column(name="DATE")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name="CONTENT")
	private String postContent;
	
	@Column(name="STARS")
	private float numStars;
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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
