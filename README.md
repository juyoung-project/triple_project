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
