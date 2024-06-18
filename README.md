# springboot-mybatis-docker

本项目使用springboot集成了mybatis + redis + mongo，并使用docker进行了部署。

项目根目录下的docker文件夹是部署文件夹，项目本身用不到，仅用于做项目部署，可把docker文件夹从项目中抽出去。
部署步骤：
1.项目打包成jar包
2.把打包好的jar包复制到docker目录下的app目录中去
3.docker文件夹上传到安装有docker的linux环境下
4.进入docker文件夹下执行命令行 “docker compose -f compose.yml up -d” 启动所有服务
5.执行完以上步骤，mongo尚缺少基础数据，不能通信，根据txt文本 “mongo创建数据库命令行.txt” 中所载，一条条命令行执行下去即可