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
