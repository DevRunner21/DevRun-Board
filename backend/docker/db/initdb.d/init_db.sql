USE db_devrun;

DROP TABLE IF EXISTS tb_member CASCADE;
DROP TABLE IF EXISTS tb_permission CASCADE;
DROP TABLE IF EXISTS tb_post CASCADE;

CREATE TABLE tb_permission
(
    permission_id BIGINT      NOT NULL AUTO_INCREMENT,
    name          VARCHAR(20) NOT NULL,
    PRIMARY KEY (permission_id)
);

CREATE TABLE tb_member
(
    member_id         BIGINT       NOT NULL AUTO_INCREMENT,
    created_at        TIMESTAMP    NOT NULL,
    updated_at        TIMESTAMP,
    login_id          VARCHAR(255) NOT NULL,
    name              VARCHAR(255) NOT NULL,
    password          VARCHAR(255) NOT NULL,
    profile_image_url VARCHAR(255),
    permission_id     BIGINT       NOT NULL,
    PRIMARY KEY (member_id),
    CONSTRAINT fk_permission_id_for_member FOREIGN KEY (permission_id) REFERENCES tb_permission (permission_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE tb_post
(
    post_id    BIGINT       NOT NULL AUTO_INCREMENT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    content    VARCHAR(1000),
    title      VARCHAR(255) NOT NULL,
    view_count BIGINT       NOT NULL,
    member_id  BIGINT,
    PRIMARY KEY (post_id),
    CONSTRAINT fk_member_id_for_post FOREIGN KEY (member_id) REFERENCES tb_member (member_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
