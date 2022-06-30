package com.homework.project.triple.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.homework.project.triple.service.EventService;
import com.homework.project.triple.service.MemberService;
import com.homework.project.utils.ReponsePOJO;


@RestController
@RequestMapping( value ="/api" )
public class ApiController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EventService eventService;
	
	@GetMapping( value = "/get-member")
	public ReponsePOJO getAllMeber() {
		
		return ReponsePOJO.success(memberService.getAllMember(), "데이터 조회가 완료되었습니다.");
		
	}
	
	@GetMapping( value="/create-member")
	public ReponsePOJO createMember() {
		
		memberService.insertTestMember();
		return ReponsePOJO.success(null,"데이터 생성이 완료되었습니다.");
		
	}
	
	@PostMapping( value="/create-event-review" )
	public ReponsePOJO createEventReview(@RequestBody HashMap<String, Object> param) {
		
		eventService.insertEventReview(param);
		return ReponsePOJO.success(null, "리뷰 등록이 완료되었습니다");
		
	}
	
	
	@PostMapping( value="/update-event-review" )
	public ReponsePOJO updateEventReview(@RequestBody HashMap<String, Object> param) {
		
		eventService.updateEventReview(param);
		return ReponsePOJO.success(null, "리뷰 수정이 완료되었습니다");
		
	}
	
	@PostMapping( value="/delete-event-review" )
	public ReponsePOJO deleteEventReview(@RequestBody HashMap<String, Object> param) {
		
		eventService.deleteEventReview(param);
		return ReponsePOJO.success(null, "리뷰 삭제가 완료되었습니다");
		
	}
}
