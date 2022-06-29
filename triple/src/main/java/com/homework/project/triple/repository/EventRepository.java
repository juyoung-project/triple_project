package com.homework.project.triple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	
	int countByPlaceId(String placeId);
	
	int countByMemberIdAndPlaceId(Long memberId, String placeId);
	
	Event findByReviewId(String reviewId);
	
}
