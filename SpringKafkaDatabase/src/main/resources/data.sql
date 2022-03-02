create table processed_interval_subscriptions(
                                                 timestamp_from timestamp,
                                                 timestamp_to timestamp,
                                                 average_week_count integer,
                                                 user_id integer,
                                                 foreign key (user_id) references users(id)
);

insert into processed_interval_subscriptions(timestamp_from, timestamp_to, average_week_count) values ('2022-03-01 17:31:00', '2022-03-01 17:31:00', 1);

create table users (
                       id serial primary key,
                       first_name varchar,
                       last_name varchar,
                       age integer,
                       unique(first_name, last_name, age)
);

insert into users(first_name, last_name, age) values ('nick', 'kskskk', 22);

insert into users(first_name, last_name, age)
values ('eest', 't2', 22)
    on conflict(first_name, last_name, age) do nothing ;

INSERT INTO users (first_name, last_name, age)
    (SELECT 'testing1', 't', 19
         WHERE NOT EXISTS (SELECT FROM users WHERE first_name = 'testing1' and  last_name = 't' and age = 19))
    returning id;

select
from users
where age = 19;

WITH e AS( INSERT INTO users (first_name, last_name, age) (SELECT 'qwerty1', '007', 19 WHERE NOT EXISTS
(SELECT FROM users WHERE first_name = 'qwerty1' and  last_name = '007' and age = 19)) RETURNING *)
SELECT * FROM e UNION SELECT * FROM users WHERE first_name = 'qwerty1' and last_name = '007' and age = 19;

INSERT INTO users (first_name, last_name, age)
SELECT 'dj', 'dj', 22
    WHERE NOT EXISTS (
    SELECT id, first_name, last_name, age
    FROM users
    WHERE first_name = 'dj' AND last_name = 'dj' and age = 22
    )
returning id;

select id from users where first_name = 'Dave' and last_name = 'Dave' and age = 30;

insert into users (first_name, last_name, age)
    (select 'firstName', 'lastName', 2 where not exists
            (select from users where first_name = 'firstName' and last_name = :lastName and age = :age));

create table subscriptions(
                              user_id integer,
                              type varchar,
                              week_count varchar,
                              foreign key (user_id) references users(id)
);

select p.timestamp_from as "timestamp_from", p.timestamp_to as "timestamp_to",
       u.id as "user_id", u.first_name as "first_name", u.last_name as "last_name",
       u.age as "age", p.average_week_count as "average_week_count"
from processed_interval_subscriptions p left join users u on u.id = p.user_id
where p.timestamp_from >= '2022-03-01 17:31:00' and '2022-03-01 17:31:00' <= p.timestamp_to;