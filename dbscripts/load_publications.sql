use dblp;

set autocommit = 0;
set unique_checks = 0;
set foreign_key_checks = 0;

LOAD DATA LOCAL INFILE '/home/nasa/csv/publication.csv' INTO TABLE Publication FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/author.csv' INTO TABLE Author FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/bookChapter.csv' INTO TABLE BookChapter FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/bookChapterData.csv' INTO TABLE BookChapterData FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/books.csv' INTO TABLE Book FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

#LOAD DATA LOCAL INFILE '/home/nasa/csv/conference.csv' INTO TABLE Conference FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

#commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/conferencePaper.csv' INTO TABLE ConferencePaper FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/journal.csv' INTO TABLE Journal FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/Users/ironstone/Desktop/csv/journalArticle.csv' INTO TABLE JournalArticle FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/phd.csv' INTO TABLE PhdThesis FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/school.csv' INTO TABLE School FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/webPage.csv' INTO TABLE WebPage FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/publicationAuthor.csv' INTO TABLE AuthorPublicationMap FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;