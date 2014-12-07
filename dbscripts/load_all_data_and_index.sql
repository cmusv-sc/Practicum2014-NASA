use dblp;

set autocommit = 0;
set unique_checks = 0;
set foreign_key_checks = 0;

LOAD DATA LOCAL INFILE '/home/nasa/csv/publication.csv' INTO TABLE Publication CHARACTER SET utf8mb4 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/author.csv' INTO TABLE Author CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/bookChapter.csv' INTO TABLE BookChapter CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/bookChapterData.csv' INTO TABLE BookChapterData CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/books.csv' INTO TABLE Book CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

#LOAD DATA LOCAL INFILE '/home/nasa/csv/conference.csv' INTO TABLE Conference CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

#commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/conferencePaper.csv' INTO TABLE ConferencePaper CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/journal.csv' INTO TABLE Journal CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/journalArticle.csv' INTO TABLE JournalArticle CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/phd.csv' INTO TABLE PhdThesis CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/school.csv' INTO TABLE School CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/webPage.csv' INTO TABLE WebPage CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

LOAD DATA LOCAL INFILE '/home/nasa/csv/publicationAuthor.csv' INTO TABLE AuthorPublicationMap CHARACTER SET UTF8 FIELDS TERMINATED BY '@@@' LINES TERMINATED BY '\n';

commit;

CREATE INDEX author_index ON Author(authorId, authorName);

commit;

CREATE INDEX school_index ON School(schoolId, schoolName);

commit;

ALTER TABLE `dblp`.`AuthorPublicationMap`
ADD INDEX `idx_publication_id` (`publicationId` ASC, `authorId` ASC);

commit;