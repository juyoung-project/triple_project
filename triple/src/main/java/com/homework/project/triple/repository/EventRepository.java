package com.homework.project.triple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	
	int countByPlaceIdAndIsDeleted(String placeId, Boolean isDeleted);
	
	int countByMemberIdAndPlaceId(Long memberId, String placeId);
	
	Event findByReviewId(String reviewId);
	
	@Query(value = "select ifnull(sum(point),0) from triple.events where member_id = :memberId", nativeQuery = true)
	int totalPoint(@Param("memberId") Long memberId);
	
}
