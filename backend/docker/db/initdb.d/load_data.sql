INSERT INTO tb_permission(permission_id, name)
VALUES (1, 'ROLE_USER')
     , (2, 'ROLE_ADMIN')
;

INSERT INTO tb_member(member_id, login_id, password, permission_id, name, profile_image_url, created_at, updated_at)
VALUES (1, 'user1', '$2a$10$B32L76wyCEGqG/UVKPYk9uqZHCWb7k4ci98VTQ7l.dCEib/kzpKGe', 1,  '김지훈', null, NOW(), NOW())
     , (2, 'admin1', '$2a$10$/enTGRjB6noB9NCd8g5kGuLchiTsZsqcUyXkUn4yglUPZ4WZ9MvrK', 2,  '이수민', null, NOW(), NOW())
     , (3, 'user2', '$2a$10$/enTGRjB6noB9NCd8g5kGuLchiTsZsqcUyXkUn4yglUPZ4WZ9MvrK', 1,  '박하늬', null, NOW(), NOW())
     , (4, 'user3', '$2a$10$/enTGRjB6noB9NCd8g5kGuLchiTsZsqcUyXkUn4yglUPZ4WZ9MvrK', 1,  '권정렬', null, NOW(), NOW())
     , (5, 'user4', '$2a$10$/enTGRjB6noB9NCd8g5kGuLchiTsZsqcUyXkUn4yglUPZ4WZ9MvrK', 1,  '이승권', null, NOW(), NOW())
     , (6, 'user5', '$2a$10$/enTGRjB6noB9NCd8g5kGuLchiTsZsqcUyXkUn4yglUPZ4WZ9MvrK', 1,  '소민희', null, NOW(), NOW())
     , (7, 'user6', '$2a$10$/enTGRjB6noB9NCd8g5kGuLchiTsZsqcUyXkUn4yglUPZ4WZ9MvrK', 1,  '구본율', null, NOW(), NOW())
     , (8, 'user7', '$2a$10$/enTGRjB6noB9NCd8g5kGuLchiTsZsqcUyXkUn4yglUPZ4WZ9MvrK', 1,  '장하늘', null, NOW(), NOW())
     , (9, 'user8', '$2a$10$/enTGRjB6noB9NCd8g5kGuLchiTsZsqcUyXkUn4yglUPZ4WZ9MvrK', 1,  '민지원', null, NOW(), NOW())
     , (10, 'user9', '$2a$10$/enTGRjB6noB9NCd8g5kGuLchiTsZsqcUyXkUn4yglUPZ4WZ9MvrK', 1,  '강혁', null, NOW(), NOW())
;

LOAD DATA INFILE '../../../../docker-entrypoint-initdb.d/post_data_50k.csv'
    INTO TABLE db_devrun.tb_post
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    (@id, @title, @content, @view_count, @member_id, @year, @month, @day)
    SET post_id = @id
        , view_count = @view_count
        , created_at = STR_TO_DATE(CONCAT(@year,'-',@month,'-',@day), '%Y-%m-%d')
        , updated_at = STR_TO_DATE(CONCAT(@year,'-',@month,'-',@day), '%Y-%m-%d')
        , member_id = @member_id
        , title = @title
        , content = @content;
