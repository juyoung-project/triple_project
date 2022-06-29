package com.homework.project.triple.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table( name="member" )
public class Member implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id // 기본키가 될 변수를 의미
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name="id" )
	private Long id;
	
	@Column( name="name" )
	private String name;
	
	
	// FK 역참조 관련
	@JsonManagedReference
	@BatchSize( size = 10 )
	@OneToMany( fetch = FetchType.EAGER )
	@JoinColumn( name="member_id" )
	private List<Event> eventList;
	
	@OneToMany
	@JoinColumn( name="member_id" )
	private List<PointHistory> pointHistoryList;

}
