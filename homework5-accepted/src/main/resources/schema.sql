drop table if exists author cascade;
create table author(
    id long primary key auto_increment,
    full_name varchar(255) not null
);

drop table if exists genre cascade;
create table genre(
    id long primary key auto_increment,
    name varchar(255) not null
);

drop table if exists book cascade;
create table book(
    id long primary key auto_increment,
    author_id long,
    genre_id long,
    title varchar(255)
    constraint `fk_book_author_id` foreign key (`author_id`) references `author` (`id`)
    constraint `fk_book_genre_id` foreign key (`genre_id`) references `genre` (`id`)
);
