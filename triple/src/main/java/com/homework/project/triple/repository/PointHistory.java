package com.homework.project.triple.repository;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table( name="point_history" )
public class PointHistory implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id // 기본키가 될 변수를 의미
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name="id" )
	private Long id;
	
	@Column( name="current_point" )
	private int currentPoint;
	
	@Column( name="type" )
	private String type;
	
	@Column( name="is_deleted" )
	private Boolean isDeleted;
	
	@CreationTimestamp
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone="Asia/Seoul" )
	@Column( name="create_time" )
	private Date createTime;
	
	// FK key관련
	@ManyToOne
	@JoinColumn( name="member_id" )
	private Member member;
	
	@ManyToOne
	@JoinColumn( name="event_id" )
	private Event event;

}
