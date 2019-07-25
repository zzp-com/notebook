CREATE TABLE jianshu_test (
		Id int primary key auto_increment,#部门编号 整形 主键 自增长
    	Name varchar(18),#部门名称
   		description varchar(100)#描述

)
insert into jianshu_test values(Id,"zzp","制作者");

CREATE TABLE cz_user(
		Id varchar(100) primary key not null,#用户编号
    	Name varchar(100),#用户昵称
    	Email varchar(100),#用户邮箱
    	Password varchar(100),#用户密码
 		Regtime date
)
CREATE TABLE cz_text(
	text_id varchar(100) primary key not null,#文章编号
	text_title varchar(100),#文章主题
	text_content text,
	email varchar(100),
	text_time  datetime,
	folder_id varchar(100)
)

CREATE TABLE cz_folder(
	folder_id varchar(100) primary key not null,
	folder_title varchar(100),
	email varchar(100),
	folder_time datetime,
	folder_aid varchar(100)
)


