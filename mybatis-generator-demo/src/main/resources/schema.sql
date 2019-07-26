CREATE TABLE house (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  room_num varchar(20) DEFAULT '' ,
  city varchar(20) DEFAULT '' ,
  community_id bigint(20) DEFAULT NULL,
  community_name varchar(255) DEFAULT '' ,
  building_id bigint(20) DEFAULT NULL ,
  building_name varchar(255) DEFAULT '' ,
  floor int(4) DEFAULT '1',
  house_type varchar(20) DEFAULT '' ,
  owner varchar(255) DEFAULT '' ,
  mobile varchar(20) DEFAULT '' ,
  enable int(1)
);


