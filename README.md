# triple_project
# 1. Project Setting 방법
    - git clone하여 ide에 코드를 내려받는다.
    - project를 import한다.
    - import한 프로젝트를 maven update를 한다.
    - project clean, maven clean, maven install을 순서대로 진행해준다.
    - db를 설정해준다.
    - 자세한 guide는 첨부파일을 확인 ( 이미지 첨부 )
    
    
# 2. DB SQL모음 
  - 스키마 생성
  CREATE SCHEMA `triple` ;


  -member table
  
    CREATE TABLE `member` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(200) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
    
  -point_history table   
  
    CREATE TABLE `point_history` (
      `id` int NOT NULL AUTO_INCREMENT,
      `member_id` int NOT NULL,
      `event_id` int NOT NULL,
      `points_earned` int NOT NULL,
      `action` varchar(45) NOT NULL,
      `is_deleted` tinyint NOT NULL DEFAULT '0',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`),
      KEY `member_id_idx` (`member_id`),
      KEY `event_id_idx` (`event_id`),
      CONSTRAINT `event_id` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`),
      CONSTRAINT `history_member_id` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
    
    
   -events table
   
      CREATE TABLE `events` (
      `id` int NOT NULL AUTO_INCREMENT,
      `action` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
      `review_id` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
      `content` varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
      `member_id` int NOT NULL,
      `place_id` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
      `point` int DEFAULT NULL,
      `attached_photo_ids` varchar(8000) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `update_time` datetime DEFAULT NULL,
      `type` varchar(45) NOT NULL,
      `is_deleted` tinyint DEFAULT '0',
      PRIMARY KEY (`id`),
      KEY `member_id_idx` (`member_id`),
      KEY `review_id_idx` (`review_id`) /*!80000 INVISIBLE */,
      KEY `place_id_idx` (`place_id`),
      CONSTRAINT `member_id` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
    
    
# 3. TESTCASE
    - http://localhost:8080/api/create-member ( 제일 처음 테스트용 멤버 생성 )
    - http://localhost:8080/api/get-member ( 생성된 멤버들의 아이디 확인 및 점수 확인 가능 )
    - http://localhost:8080/api/create-event-review ( 리뷰 생성 )
      테스트 JSON
      {
            "action" : "ADD",
            "content" : "TEST REVIEW",
            "user_id" : "1",
            "placeId" : "wa654-321as",
            "attachedPhotoIds" : ["111", "222"]
      }
    - http://localhost:8080/api/get-member ( 해당유저 점수 확인 가능 )
    - http://localhost:8080/api/update-event-review ( 리뷰 업데이트 )
      테스트 JSON 
      {
            "reviewId" : "ec2b43e9-df77-4ac8-b3ff-b0dfb57f6365",   ( db에서 조회해온 값을 넣어주어여 한다. )
            "content" : "32222222221",
            "attachedPhotoIds" : ["11"]
      }
    - http://localhost:8080/api/get-member ( 업데이트된 리뷰에 대한 점수 확인 )
    - http://localhost:8080/api/delete-event-review ( 리뷰 삭제 )
      테스트 JSON 
      {
            "reviewId" : "ec2b43e9-df77-4ac8-b3ff-b0dfb57f6365"   ( db에서 조회해온 값을 넣어주어여 한다. )
      }
      
    - http://localhost:8080/api/get-member ( 삭제된 리뷰에 대한 점수 확인 )
    - http://localhost:8080/api/get-point-history ( history 조회 )
