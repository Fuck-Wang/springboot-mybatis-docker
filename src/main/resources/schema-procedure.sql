
# 该环境集成mysql受限，不能完全释放mysql能力，存储过程不能在项目启动时自动执行，需要的话只能手动执行，故此把该存储过程文件单独拎出来
use mybat;
# 忽略分号，把//范围内的语句当成一个语句块整体执行
delimiter //
# 此处“create procedure”和方法名“alter_column”之间应当加上“if not exists”，但是该环境不支持，在数据库控制台可以，原因不明
create procedure alter_column(in tab_name varchar(255), in col_name varchar(255), in col_type varchar(255))
begin
#     判断给定表中是否存在某个属性字段
# @result, @变量名称--直接声明一个变量的格式
    SELECT COLUMN_NAME into @result from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME =tab_name AND COLUMN_NAME =col_name;
# 如果该属性字段不存在
    if @result is null then
#         拼接alter增加属性字段，该add的动作可以取消，可以把整个sql作为参数传入
        set @alter_sql = concat('alter table ', tab_name, ' add ', col_name, ' ', col_type);
#         prepare预定义语句，from后面只能跟随@开头的变量否则会报错
        prepare exec_sql from @alter_sql;
        execute exec_sql;
#         释放exec_sql
        DEALLOCATE prepare exec_sql;
#     end if;
end;//
delimiter ;
call alter_column('student', 'grade_id', 'bigint');
call alter_column('student', 'subject_id', 'bigint');

#===================================================




















