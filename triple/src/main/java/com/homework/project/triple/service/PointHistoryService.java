package com.homework.project.triple.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homework.project.triple.repository.Event;
import com.homework.project.triple.repository.Member;
import com.homework.project.triple.repository.PointHistory;
import com.homework.project.triple.repository.PointHistoryRepository;

@Service
public class PointHistoryService {
	
	/**
	 * 
	 * 히스토리 관한 서비스 로직 
	 * 
	 * */
	
	@Autowired
	private PointHistoryRepository pointHistoryRepository;
	
	@Transactional
	public void savePointHistory(int point, String action, Member member, Event event) {
		
		PointHistory pointHistory = PointHistory.builder().pointsEarned(point).action(action).member(member).event(event).build();
		pointHistoryRepository.save(pointHistory);
		
		
	}
	
}
