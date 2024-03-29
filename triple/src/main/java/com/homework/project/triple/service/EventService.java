package com.homework.project.triple.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homework.project.triple.repository.Event;
import com.homework.project.triple.repository.EventRepository;
import com.homework.project.triple.repository.Member;
import com.homework.project.triple.repository.MemberRepository;
import com.homework.project.utils.CodeCons;
import com.homework.project.utils.StringUtils;
import com.homework.project.utils.Utils;

@Service
public class EventService {
	
	/**
	 * 
	 * 리뷰에 관한 서비스 로직 
	 * 
	 * */
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PointHistoryService pointHistoryService;
	
	@Transactional
	public void insertEventReview( HashMap<String, Object> param ) {
		
		String type = CodeCons.REVIEW;
		//  액션별로 method를 나누었기 때문에 action값을 받아올 필요가 없음		
		//	String action = StringUtils.objectToString( param.get("action") );
		String action = CodeCons.ADD;
		String reviewId = Utils.getUUID();
		String content = StringUtils.objectToString( param.get("content") );
		Long userId =  Long.valueOf( StringUtils.objectToString(param.get("user_id")) );
		String placeId = StringUtils.objectToString( param.get("placeId") );
		
		ArrayList<String> photoIds = (ArrayList<String>) param.get("attachedPhotoIds");
		Member member = memberRepository.findById(userId).get();
		
		int point = pointCalc(action, content, placeId, member, null, photoIds);
		
		Event event = Event.builder().type(type)
									 .action(action)
									 .reviewId(reviewId)
									 .content(content)
									 .attachedPhotoIds(StringUtils.arrayToCommaString(photoIds))
									 .placeId(placeId)
									 .member(member)
									 .point(pointCalc(action, content, placeId, member, null, photoIds))
									 .build();
		
		eventRepository.save(event);
		
		afterSaveHistory(point, action, member, event);
		
	}
	
	@Transactional
	public void updateEventReview( HashMap<String, Object> param ) {
		
		String reviewId = StringUtils.objectToString(param.get("reviewId"));
		String content = StringUtils.objectToString( param.get("content") );
		ArrayList<String> photoIds = (ArrayList<String>) param.get("attachedPhotoIds");
		String action = CodeCons.MOD;
		
		
		Event event = eventRepository.findByReviewId(reviewId);
		
		int point = pointCalc(action, content, null, null, event, photoIds);
		
		event.setPoint( point );
		event.setContent(content);
		event.setAttachedPhotoIds(StringUtils.arrayToCommaString(photoIds));
		
		eventRepository.save(event);
		
		afterSaveHistory(point, action, event.getMember(), event);
		
	}
	
	@Transactional
	public void deleteEventReview( HashMap<String, Object> param ) {
		
		String reviewId = StringUtils.objectToString(param.get("reviewId"));
		Event event = eventRepository.findByReviewId(reviewId);
		String action = CodeCons.DELETE;
		
		event.setIsDeleted(true);
		event.setPoint(0);
		eventRepository.save(event);
		
		afterSaveHistory(0, action, event.getMember(), event);
		
	}
	
	public int getMemberPoint(Long memberId) {
		
		return eventRepository.totalPoint(memberId);
		
	}
	
	public void afterSaveHistory( int point, String action, Member member, Event event ) {
		
		pointHistoryService.savePointHistory(point, action, member, event);
		
	}
	
	public int pointCalc(String action, String content, String placeId, Member member, Event event, ArrayList<String> photoIds) {
		
		int point = 0;
		
		
		switch (action) {
		
			case CodeCons.ADD:
				
				int placeCount = eventRepository.countByPlaceIdAndIsDeleted(placeId, false);
				
				int visitPlaceCount = eventRepository.countByMemberIdAndPlaceId(member.getId(), placeId);
				
				// 해당유저가 방문한곳이아닐때만 리뷰 점수가 있음				
				if( visitPlaceCount < 1 ) {
					
					if( content.length() >= 1 ) {
						point++;
					}
					
					if( photoIds != null && photoIds.size() >= 1 ) {
						point++;
					}
					
					// 1보다 작으면 신규				
					if( placeCount < 1 ) {
						point++;
					}
				}
				
				
				break;
				
			case CodeCons.MOD:
				
				point = event.getPoint();
				
				if ( event.getContent().length() >=1 && content.length() < 1 ) {
					point--;
				} else if( event.getContent().length() < 1 && content.length() >= 1 ) {
					point++;
				} else {
					// 내용만 바뀔경우 점수를 얻지 못한다.					
				}
				
				if( event.getAttachedPhotoIds().length() >=1 && StringUtils.arrayToCommaString(photoIds).length() < 1 ) {
					point--;
				} else if( event.getAttachedPhotoIds().length() < 1 && StringUtils.arrayToCommaString(photoIds).length() >= 1 ) {
					point++;
				} else {
					
				}
				
				
				break;
				
			case CodeCons.DELETE:
				break;

			default:
				break;
		}
		
		
		return point;
	}
	

}
