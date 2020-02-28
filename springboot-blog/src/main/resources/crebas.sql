/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018-07-13  21:23:04                         */
/*==============================================================*/


drop table if exists article_info;

drop table if exists category_info;

drop table if exists message_info;

drop table if exists user_info;

/*==============================================================*/
/* Table: article_info                                          */
/*==============================================================*/
create table article_info
(
   article_id           int not null auto_increment,
   user_id              int,
   category_id          int,
   article_title        varchar(500),
   article_content      varchar(4000),
   article_img          varchar(1000),
   article_recom        varchar(10),
   article_time         datetime,
   article_mark         varchar(10),
   primary key (article_id)
);

/*==============================================================*/
/* Table: category_info                                         */
/*==============================================================*/
create table category_info
(
   category_id          int not null auto_increment,
   category_name        varchar(300),
   category_alias       varchar(200),
   category_desc        varchar(2000),
   primary key (category_id)
);

/*==============================================================*/
/* Table: message_info                                          */
/*==============================================================*/
create table message_info
(
   message_id           int not null auto_increment,
   message_content      varchar(4000),
   message_time         datetime,
   message_name         varchar(200),
   message_mark         varchar(10),
   primary key (message_id)
);

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   user_id              int not null auto_increment,
   user_name            varchar(100),
   user_phone           varchar(100),
   user_account         varchar(100),
   user_password        varchar(100),
   user_mark            varchar(10) comment '-1 无效  1 有效',
   primary key (user_id)
);

alter table article_info add constraint FK_Reference_1 foreign key (user_id)
      references user_info (user_id) on delete restrict on update restrict;

alter table article_info add constraint FK_Reference_2 foreign key (category_id)
      references category_info (category_id) on delete restrict on update restrict;

