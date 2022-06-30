package com.homework.project.triple.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homework.project.triple.repository.Member;
import com.homework.project.triple.repository.MemberRepository;
import com.homework.project.utils.StringUtils;


@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private EventService eventService;
	
	public void insertTestMember() {
		
		List<String> memberList = new ArrayList<String>();
		
		memberList.add("홍길동");
		memberList.add("강감찬");
		memberList.add("장보고");
		
		for (String mem : memberList) {
			
			Member member = Member.builder().name(mem).build();
			memberRepository.save(member);
			
		}
		
		
	}
	
	public List<HashMap<String, String>> getAllMember(){
		
		List<Member> memberList = memberRepository.findAll();
		
		List<HashMap<String, String>> rtnList = new ArrayList<>();
		
		for ( Member member : memberList) {
			HashMap<String, String> tempMap = new HashMap<>();
			tempMap.put("id", String.valueOf( member.getId()));
			tempMap.put("name", member.getName());
			tempMap.put("totalPoint", StringUtils.objectToString( eventService.getMemberPoint(member.getId())) );
			rtnList.add(tempMap);
			
		}
		return rtnList;
		
	}
	

	
	public void fakeLogic() {
		
		/**
		 * place_id로 현재 있는지 count 
		 * point라는 변수를 하나 두고 더하는 식으로 point 적재
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		
	}
	
	
}

