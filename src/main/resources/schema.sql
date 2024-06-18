create database if not exists mybat;
use mybat;
create table if not exists `user`(
    `id` bigint not null auto_increment,
    `name` varchar(255) not null comment '姓名',
    `age` int not null comment '年齡',
    `create_time` datetime comment '創建時間',
    `update_time` datetime comment '更新時間',
    primary key(`id`)
)engine=INNODB default charset=utf8;
# insert into user(`name`, `age`) values("小款", 20),("小硕", 18);

create table if not exists `student`(
    `id` bigint not null auto_increment,
    `stu_no` varchar(255) not null comment '學號',
    `name` varchar(255) not null comment '名字',
    `point` int comment '分數',
    primary key (`id`)
)engine=INNODB default charset=utf8;

create table if not exists `grade`(
    `id` bigint not null auto_increment,
    `name` varchar(255) not null comment '年級名稱',
    primary key (`id`)
)engine=INNODB default charset utf8;

create table if not exists `subject`(
    `id` bigint not null auto_increment,
    `name` varchar(255) not null comment '科目名稱',
    primary key (`id`)
)engine=INNODB default charset=utf8;

create table if not exists `subject_and_student`(
    `id` bigint not null auto_increment primary key,
    `subject_id` bigint not null comment '科目id',
    `student_id` bigint not null comment '学生id'
)engine=INNODB default charset=utf8;

#idea不支持数据库alter语句 if not exists
# alter table `user` add if not exists `grade_id` bigint;
# alter table `user` modify `id` bigint;
# alter table `user` add `create_time` datetime;
# alter table `user` add `update_time` datetime;
# alter table `user` add `create_by_id` bigint;
# alter table `user` add `update_by_id` bigint;

create table if not exists `file_info` (
    `mongo_id` varchar(255) not null unique key comment 'mongoId',
    `compress_mongo_id` varchar(255) comment '压缩后mongoId',
    `file_name` varchar(255) comment '文件名称',
    `file_suffix` varchar(255) comment '文件后缀',
    `file_size` bigint comment '文件大小',
    `compress_size` bigint comment '压缩后大小',
    `upload_user_id` varchar(255) comment '上传人id',
    `file_type` varchar(255) comment '文件类型',
    `remark` text comment '备注'
) engine=INNODB default charset=utf8