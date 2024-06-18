FROM redis
# 配置文件复制到容器/data目录下
COPY redis.conf /data
# 创建容器的时候启用该配置
CMD ["redis-server", "/data/redis.conf"]