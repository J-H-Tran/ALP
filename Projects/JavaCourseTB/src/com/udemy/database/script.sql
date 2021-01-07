SELECT * FROM albums ORDER BY artist, name COLLATE NOCASE; --SQLite, sorts first by artist id and then by album name

select albums.name, songs.track, songs.title
from songs
join albums
on songs.album = albums._id
order by albums.name, songs.track; -- sort by album names first then track number. View looks like grouping of albums with tracks in ascending order

SELECT artists.name, albums.name, songs.track, songs.title
FROM songs
INNER JOIN albums ON songs.album = albums._id
INNER JOIN artists ON albums.artist = artists._id
WHERE artist._id = 21
ORDER BY artists.name, albums.name, songs.track;

SELECT artists.name, albums.name, songs.track, songs.title
FROM songs
INNER JOIN albums ON songs.album = albums._id
INNER JOIN artists ON albums.artist = artists._id
WHERE songs.title LIKE '%love%'
ORDER BY artists.name, albums.name, songs.track;
-- you can nest a select inside another select!!!
-- Structure is quite straightforward:
-- Specify columns of interest, JOIN any need tables, filter selections, order your result.

-- LIKE is case insensitive

SELECT artists.name, albums.name, songs.track, songs.title
FROM songs
INNER JOIN albums ON songs.album = albums._id
INNER JOIN artists ON albums.artist = artists._id
WHERE artists.name LIKE 'jeff%'
ORDER BY artists.name, albums.name, songs.track;

create view artist_list as
select artists.name, albums.name, songs.track, songs.title from songs
inner join albums on songs.album = albums._id
inner join artists on albums.artist = artists._id
order by artists.name, albums.name, songs.track;

-- SQL challenges
-- Select the titles of all the songs on the album Forbidden
SELECT songs.title AS song_title
from songs inner join albums
on songs.album = albums._id
where albums.name = 'Forbidden';

-- same as above but this time display the songs in track order. include track number to verify
SELECT songs.track AS track_number, songs.title AS song_title
from songs inner join albums
on songs.album = albums._id
where albums.name = 'Forbidden'
order by songs.track;

-- display all songs from band Deep Purple
SELECT songs.title from songs
inner join albums on songs.album=albums._id
inner join artists on albums.artist=artists._id
where artists.name='Deep Purple';

-- rename the band Mehitabel to One Kitten. Exception to rule to always fully qualify your column names. Set artists.name won't work just dpecify name
UPDATE artists
SET name = 'One Kitten'
WHERE name = 'Mehitabel';

-- verify it rename properly
Select * from artists;

-- 6. select titles of all songs by Aerosmith in aplha order, output only title
SELECT songs.title from songs
inner join albums on songs.album=albums._id
inner join artists on albums.artist=artists._id
where artists.name='Aerosmith'
order by songs.title;

-- 7. replace the column with count()
SELECT count(songs.title) from songs
inner join albums on songs.album=albums._id
inner join artists on albums.artist=artists._id
where artists.name='Aerosmith'
order by songs.title;

-- 8. go online find out how to get a list of songs from 6. without any dupes
SELECT distinct songs.title from songs
inner join albums on songs.album=albums._id
inner join artists on albums.artist=artists._id
where artists.name='Aerosmith'
order by songs.title;

-- 9. figure out how to get a count of the songs without duplicates, similar to 8
SELECT count(distinct songs.title) from songs
inner join albums on songs.album=albums._id
inner join artists on albums.artist=artists._id
where artists.name='Aerosmith'
order by songs.title;

-- 10. do 9. but to find the number of artists (1) and the number of albums
SELECT count(distinct artists.name) from songs
inner join albums on songs.album=albums._id
inner join artists on albums.artist=artists._id
where artists.name='Aerosmith'
order by songs.title;

SELECT count(distinct albums.name) from songs
inner join albums on songs.album=albums._id
inner join artists on albums.artist=artists._id
where artists.name='Aerosmith'
order by songs.title;


























