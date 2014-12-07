DROP Database IF EXISTS `dblp`;

CREATE Database `dblp`;

USE dblp;

CREATE TABLE `Publication` (
  `publicationId` int(11) NOT NULL AUTO_INCREMENT,
  `publicationChannel` varchar(100) DEFAULT NULL,
  `citationCount` int(11) DEFAULT NULL,
  `authorNames` varchar(2000) DEFAULT NULL,
  `publicationTitle` varchar(1000) DEFAULT NULL,
  `year` varchar(5) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `keywords` varchar(200) DEFAULT NULL,
  `tags` varchar(200) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`publicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `Author` (
  `authorId` int(11) NOT NULL AUTO_INCREMENT,
  `authorName` varchar(100) NOT NULL,
  `institution` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`authorId`,`authorName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `AuthorPublicationMap` (
  `authorPublicationMapId` int(11) NOT NULL AUTO_INCREMENT,
  `publicationId` int(11) DEFAULT NULL,
  `authorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`authorPublicationMapId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `publicationId` int(11) DEFAULT NULL,
  `editors` varchar(500) DEFAULT NULL,
  `pages` varchar(50) DEFAULT NULL,
  `month` varchar(15) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `publisherAddress` varchar(200) DEFAULT NULL,
  `isbn` varchar(100) DEFAULT NULL,
  `series` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bookId`),
  KEY `publicationId` (`publicationId`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publicationId`) REFERENCES `Publication` (`publicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `BookChapterData` (
  `bookChapterDataId` int(11) NOT NULL AUTO_INCREMENT,
  `bookChapterName` varchar(200) DEFAULT NULL,
  `relevance` double DEFAULT NULL,
  PRIMARY KEY (`bookChapterDataId`),
  UNIQUE KEY `uq_bookChapterName` (`bookChapterName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `BookChapter` (
  `bookChapterId` int(11) NOT NULL AUTO_INCREMENT,
  `bookChapterDataId` int(11) DEFAULT NULL,
  `publicationId` int(11) DEFAULT NULL,
  `pages` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bookChapterId`),
  KEY `publicationId` (`publicationId`),
  KEY `bookChapterDataId` (`bookChapterDataId`),
  CONSTRAINT `bookchapter_ibfk_1` FOREIGN KEY (`publicationId`) REFERENCES `Publication` (`publicationId`),
  CONSTRAINT `bookchapter_ibfk_2` FOREIGN KEY (`bookChapterDataId`) REFERENCES `BookChapterData` (`bookChapterDataId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `Conference` (
  `conferenceId` int(11) NOT NULL AUTO_INCREMENT,
  `conferenceName` varchar(100) DEFAULT NULL,
  `conferenceLocation` varchar(50) DEFAULT NULL,
  `relevance` double DEFAULT NULL,
  PRIMARY KEY (`conferenceId`),
  UNIQUE KEY `uq_conferenceName` (`conferenceName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ConferencePaper` (
  `conferencePaperId` int(11) NOT NULL AUTO_INCREMENT,
  `conferenceId` int(11) DEFAULT NULL,
  `publicationId` int(11) DEFAULT NULL,
  `pages` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`conferencePaperId`),
  KEY `publicationId` (`publicationId`),
  KEY `conferenceId` (`conferenceId`),
  CONSTRAINT `conferencepaper_ibfk_1` FOREIGN KEY (`publicationId`) REFERENCES `Publication` (`publicationId`),
  CONSTRAINT `conferencepaper_ibfk_2` FOREIGN KEY (`conferenceId`) REFERENCES `Conference` (`conferenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Journal` (
  `journalId` int(11) NOT NULL AUTO_INCREMENT,
  `journalName` varchar(200) DEFAULT NULL,
  `relevance` double DEFAULT NULL,
  PRIMARY KEY (`journalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `JournalArticle` (
  `journalArticleId` int(11) NOT NULL AUTO_INCREMENT,
  `journalId` int(11) DEFAULT NULL,
  `publicationId` int(11) DEFAULT NULL,
  `pages` varchar(50) DEFAULT NULL,
  `volume` varchar(50) DEFAULT NULL,
  `columns` varchar(50) DEFAULT NULL,
  `month` varchar(30) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`journalArticleId`),
  KEY `publicationId` (`publicationId`),
  KEY `journalId` (`journalId`),
  CONSTRAINT `journalarticle_ibfk_1` FOREIGN KEY (`publicationId`) REFERENCES `Publication` (`publicationId`),
  CONSTRAINT `journalarticle_ibfk_2` FOREIGN KEY (`journalId`) REFERENCES `Journal` (`journalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `School` (
  `schoolId` int(11) NOT NULL AUTO_INCREMENT,
  `schoolName` varchar(100) DEFAULT NULL,
  `schoolLocation` varchar(50) DEFAULT NULL,
  `relevance` double DEFAULT NULL,
  PRIMARY KEY (`schoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PhdThesis` (
  `phdThesisId` int(11) NOT NULL AUTO_INCREMENT,
  `schoolId` int(11) DEFAULT NULL,
  `publicationId` int(11) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `advisorName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`phdThesisId`),
  KEY `publicationId` (`publicationId`),
  KEY `schoolId` (`schoolId`),
  CONSTRAINT `phdthesis_ibfk_1` FOREIGN KEY (`publicationId`) REFERENCES `Publication` (`publicationId`),
  CONSTRAINT `phdthesis_ibfk_2` FOREIGN KEY (`schoolId`) REFERENCES `School` (`schoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `WebPage` (
  `webPageId` int(11) NOT NULL AUTO_INCREMENT,
  `publicationId` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `accessDate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`webPageId`),
  KEY `publicationId` (`publicationId`),
  CONSTRAINT `webpage_ibfk_1` FOREIGN KEY (`publicationId`) REFERENCES `Publication` (`publicationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
