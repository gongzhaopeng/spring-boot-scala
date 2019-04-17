CREATE TABLE authorities
(
  id        bigint auto_increment  not null,
  username  varchar_ignorecase(50) not null,
  authority varchar_ignorecase(50) not null,
  constraint fk_authorities_users foreign key (username) references users (username)
);
CREATE TABLE users
(
  id       bigint auto_increment  not null,
  username varchar_ignorecase(50) not null,
  password varchar(50)            not null,
  enabled  boolean                not null
);
INSERT INTO users (username, password, enabled)
VALUES ('root', 'root', true);
INSERT INTO users (username, password, enabled)
VALUES ('user', 'user', true);
INSERT INTO authorities (username, authority)
VALUES ('root', 'ROLE_user');
INSERT INTO authorities (username, authority)
VALUES ('root', 'ROLE_admin');
INSERT INTO authorities (username, authority)
VALUES ('user', 'ROLE_user');