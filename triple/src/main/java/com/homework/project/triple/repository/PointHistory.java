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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
@Table( name="point_history" )
public class PointHistory implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id // 기본키가 될 변수를 의미
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name="id" )
	private Long id;
	
	@Column( name="points_earned" )
	private int pointsEarned;
	
	@Column( name="action" )
	private String action;
	
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
