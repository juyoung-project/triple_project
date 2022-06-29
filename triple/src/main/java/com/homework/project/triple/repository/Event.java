package com.homework.project.triple.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table( name="events ")
public class Event implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id // 기본키가 될 변수를 의미
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name="id" )
	private Long id;
	
	@Column( name="type" )
	private String type;
	
	@Column( name="action" )
	private String action;
	
	@Column( name="review_id" )
	private String reviewId;
	
	@Column( name="content" )
	private String content;
	
	@Column( name="place_id" )
	private String placeId;
	
	@Column( name="point" )
	private int point;
	
	@Column( name="attached_photo_ids" )
	private String attachedPhotoIds;
	
	@CreationTimestamp
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone="Asia/Seoul" )
	@Column( name="create_time" )
	private Date createTime;
	
	@UpdateTimestamp
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone="Asia/Seoul" )
	@Column( name="update_time" )
	private Date updateTime;
	
	
	// FK key관련
	@ManyToOne
	@JoinColumn( name="member_id" )
	private Member member;
	
	@OneToMany
	@JoinColumn( name="event_id" )
	private List<PointHistory> pointHistoryList;
	
}
