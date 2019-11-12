```
java -jar -Xms256m -Xmx256m spring-mybatis-plus-demo.jar --spring.profiles.active=prod --server.port=8080
nohup java -jar -Xms256m -Xmx256m spring-mybatis-plus-demo.jar --spring.profiles.active=prod --server.port=8080 >./logs/spring-mybatis-plus-demo/spring-mybatis-plus-demo.log &
```
#### 查看实时日志
```$xslt
tail -f ./logs/spring-mybatis-plus-demo/spring-mybatis-plus-demo.log
```