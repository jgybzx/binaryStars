# RabbitMQ
消息推送到接收的流程，四个主体：消息生产者（provider），交换机（Exchange），队列（Queue），消息消费者（consumer）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210719135218224.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0pHWUJaWF9H,size_16,color_FFFFFF,t_70)

## 交换机
- **Direct Exchange**

直连型交换机，根据消息携带的路由键将消息投递给对应队列。

大致流程，有一个队列绑定到一个直连交换机上，同时赋予一个路由键 routing key 。
然后当一个消息携带着路由值为X，这个消息通过生产者发送给交换机时，交换机就会根据这个路由值X去寻找绑定值也是X的队列。

- **Fanout Exchange**

扇型交换机，这个交换机没有路由键概念，就算你绑了路由键也是无视的。 这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列。

- **Topic Exchange**

主题交换机，这个交换机其实跟直连交换机流程差不多，但是它的特点就是在它的路由键和绑定键之间是有规则的。
简单地介绍下规则：

(星号) * 用来表示一个单词 (必须出现的)

(井号) # 用来表示任意数量（零个或多个）单词

通配的绑定键是跟队列进行绑定的，举个小例子
队列Q1 绑定键为  &#42;.TT.*          
队列Q2绑定键为  TT.#
如果一条消息携带的路由键为 A.TT.B，那么队列Q1将会收到；
如果一条消息携带的路由键为TT.AA.BB，那么队列Q2将会收到；

原文链接：https://blog.csdn.net/qq_35387940/article/details/100514134