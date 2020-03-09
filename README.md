# 基于Spring Boot RocketMQ搭建的消息中心应用DEMO

# 各组件主要作用如下：
- Spring Cloud Config：消息配置(如topic、ConsumerGroup、ProducerGroup)中心。
- Eureka：应用服务注册中心，负责项目中各服务的发现与提供调用。
- MongoDB：由于消息的事务关系不强且Mongodb格式文档自由(json存储，随意增删字段)，所以使用Mongodb存储各个应用发送过来的消息(主要为Sms、Email等)，每次消费前通过RocketMQ的Message ID查询Mongo保证消息幂等性避免重复消费，消费成功后保存消息。
- RocketMQ：消息接收、存储、发送

## 运行流程
1. 运行RocketMQ name-server与broker,如`mqnamesrv -n 127.0.0.1:9876`,`mqbroker -n 127.0.0.1:9876`
2. 运行eureka-app
3. 运行配置中心config-server
4. 运行消息中心message-center
5. 运行message-center单元测试类(SmsSendTest)或运行question-app访问`localhost:8080/question/toUser?userId=xxx`进行消费测试，消息中心控制台打印出日志信息与Mongo sms_message_log成功新增了数据即项目搭建完成


##  (待)扩展点：
1. RocketMQ的发送者应用可在配置文件中设置rocketmq.producer.retry-times-when-send-failed/retry-times-when-send-async-failed属性配置rocketmq同步/异步发送消息失败后的重试次数，不设置则默认都为2
2. 当业务执行操作结果失败时仍然入库的原因是有时业务执行过程中可能会包含调用第三方的操作，当第三方报错时会导致业务操作结果失败，而第三方的操作是不可控的，所以先把报错结果保存便于追溯，且有业务需要时也可通过定时任务查库重新执行业务
3. 该例子中只用了一个消息配置文件，实际开发中消息配置需根据项目所需配置到对应的项目配置文件，如question-app的消息配置(如topc、producerGroup)应在其项目中的配置文件(如application.yml、apollo的namespace)中配置
4. 该项目中的NameServer、Broker并没有集群部署，Broker集群部署后配置同步双写避免主机写入后尚未同步到从机就宕机导致消息丢失的情况(有意向的自行百度：RocketMQ 同步双写)