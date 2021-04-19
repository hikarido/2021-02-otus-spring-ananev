insert into author (full_name) values
('Hero Marin'),
('Mihail Afanasievich Bulgakov'),
('Oxana Geppert'),
('John Ronald Reuel Tolkien');

insert into genre (name) values
('classic'),
('horror'),
('fantasy'),
('science fiction'),
('humor');

insert into book (title, author_id, genre_id) values
('The Starcraft hand book', 1, 4),
('The Master and Margarita', 2, 1),
('The Community', 3, 4),
('The Lord Of the rings', 4, 3);
