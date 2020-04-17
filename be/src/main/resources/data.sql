INSERT INTO user(id, name, password)
VALUES (1, 'scott', 'tiger57');

INSERT INTO project(id, title)
VALUES (1, 'Project');

INSERT INTO category(id, title, is_deleted, project, project_key)
VALUES (1, 'Todo', FALSE, 1, 0);
INSERT INTO category(id, title, is_deleted, project, project_key)
VALUES (2, 'Doing', FALSE, 1, 1);
INSERT INTO category(id, title, is_deleted, project, project_key)
VALUES (3, 'Done', FALSE, 1, 2);

INSERT INTO card(title, time_created, user, category, is_deleted)
VALUES ('로깅할거야', LOCALTIMESTAMP, 1, 1, false);

INSERT INTO log(user, card, card_title, src_category, dst_category, action, time_logged, project, project_key)
VALUES (1, 1, '로깅할거야', null, 'Todo', 'added', LOCALTIMESTAMP, 1, 0);
INSERT INTO log(user, card, card_title, src_category, dst_category, action, time_logged, project, project_key)
VALUES (1, 1, '로깅할거야', 'Todo', 'Doing', 'moved', LOCALTIMESTAMP, 1, 1);
INSERT INTO log(user, card, card_title, src_category, dst_category, action, time_logged, project, project_key)
VALUES (1, 1, '로깅할거야', null, 'Doing', 'moved', LOCALTIMESTAMP, 1, 2);
INSERT INTO log(user, card, card_title, src_category, dst_category, action, time_logged, project, project_key)
VALUES (1, 1, '로깅할거야', null, null, 'updated', LOCALTIMESTAMP, 1, 3);
INSERT INTO log(user, card, card_title, src_category, dst_category, action, time_logged, project, project_key)
VALUES (1, 1, '로깅할거야', 'Todo', null, 'removed', LOCALTIMESTAMP, 1, 4);
