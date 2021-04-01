drop table if exists author cascade;
create table author(
    id int primary key auto_increment,
    full_name varchar(255) not null
);

drop table if exists genre cascade;
create table genre(
    id int primary key auto_increment,
    name varchar(255) not null
);

drop table if exists book cascade;
create table book(
    id int primary key auto_increment,
    author_id int,
    genre_id int,
    title varchar(255),
    constraint book_genre_fk foreign key (genre_id) references genre (id),
    constraint book_author_fk foreign key (author_id) references author (id)
);
