# RESTful Web Services

### User -> Posts

- Retrieve all Users - GET /users
- Create a User - POST /users
- Retrieve one User - GET /users/{id} -> /users/1
- Delete a User - DELETE /users/{id} -> /users/1

Para obtener una lista de toda slas publicaciones y crear una nueva publicación

- Retrieve all post for a User - GET /users/{id}/posts
- Create a posts for a User - POST /users{id}/posts
- Retrieve details of a post - GET /users/{id}/posts/{posts_id}

### User -> Posts

create table user (
id integer not null, 
birth_date timestamp, 
name varchar(255), 
primary key (id)
);

create table post (
id integer not null, 
description varchar(255), 
user_id integer, 
primary key (id)
);

alter table post 
add constraint post_to_user_foreign_key
foreign key (user_id) references user;
