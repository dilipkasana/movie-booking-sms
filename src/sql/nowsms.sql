CREATE DATABASE IF NOT EXISTS nowsms;
CREATE TABLE IF NOT EXISTS `nowsms`.`alert` (
  `mobile` varchar(20) NOT NULL,
  `msg` varchar(300) NOT NULL,
  `date` int(10) NOT NULL,
  `time` int(100) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `nowsms`.`login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `nowsms`.`book` (
  `book_id` int(20) NOT NULL,
  `show_id` int(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `tickets` int(20) NOT NULL,
  `price` int(20) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `nowsms`.`draft` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `sender` varchar(20) NOT NULL,
  `smsprefix` varchar(20) NOT NULL,
  `fullsms` varchar(200) NOT NULL,
  `msgdate` varchar(20) NOT NULL,
  `msgtime` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `nowsms`.`inbox` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `sender` varchar(20) NOT NULL,
  `smsprefix` varchar(20) NOT NULL,
  `fullsms` varchar(200) NOT NULL,
  `msgdate` varchar(100) NOT NULL DEFAULT '20120205',
  `msgtime` varchar(100) NOT NULL DEFAULT '224108',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `nowsms`.`movie` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `theatre` varchar(20) NOT NULL,
  `movie` varchar(20) NOT NULL,
  `timing` varchar(20) NOT NULL,
  `price` int(5) NOT NULL,
  `ticket` int(5) NOT NULL,
  `date` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `theatre` (`theatre`,`timing`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `nowsms`.`outbox` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `receiver` varchar(20) NOT NULL,
  `replysms` varchar(5000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `nowsms`.`sentitem` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(100) NOT NULL,
  `msg` varchar(2000) NOT NULL,
  `date` varchar(100) NOT NULL,
  `time` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
