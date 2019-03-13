# zookeeper
 zookeeper主从服务切换demo.

 demo使用springboot、jdk1.8、zookeeper3.4.12、joda、slf4j+ log4j2
 
 #### 该demo实现 链接同一个zk， 启动2个jar服务，当其中一个服务停了或运行超时，另外一个服务就被切换为leader，适用于定时任务

### zookeeper简单配置（具体可以网上搜索，有很多文章）

zookeeper解压即可使用。

##### 1.配置Zookeeper的配置文件</br>
进入zookeeper的conf目录，修改配置文件名</br></br>
[root@localhost zookeeper-3.4.12]# cd conf</br>
[root@localhost conf]# mv zoo_sample.cfg zoo.cfg</br>
[root@localhost conf]# vim zoo.cfg </br>

主要修改：</br>
dataDir=/usr/local/zookeeper-3.4.12/data</br>

server.0=192.168.253.1:2888:3888</br>
server.1=192.168.253.1:2889:3889</br>
server.2=192.168.253.1:2890:3890</br>

首先 修改 dataDir，顾名思义就是【数据目录】了，修改成我们自定义的即可。</br>
表单server.X的条目列出构成ZooKeeper服务的服务器。当服务器启动时，它通过查找数据目录中的文件myid来知道它是哪个服务器 </br>


server.X=A:B:C</br>
X-代表服务器编号</br>
A-代表ip</br>
B和C-代表端口，这个端口用来系统之间通信</br>

##### 2、 根据dataDir进行X的配置【这里三台服务器不一样】</br>
找到Zookeeper目录，新建data文件夹，并且在data文件夹下面创建一个文件，叫myid，并且在文件里写入server.X对应的X
如：server.0=192.168.253.1:2888:3888 这个zk下面的myid文件直接写0，server.1=192.168.253.1:2889:3889 这个zk下面的myid文件直接写1。</br>

最后直接启动服务器 zkServer.sh start</br>
输出：</br>

[root@localhost zookeeper-3.4.12]# zkServer.sh start</br>
ZooKeeper JMX enabled by default</br>
Using config: /usr/local/zookeeper-3.4.12/bin/../conf/zoo.cfg</br>
Starting zookeeper ... STARTED</br>

是否启动成功，执行以下命令</br>
zkServer.sh status</br>


其中springboot的.yml配置文件，可以在部署jar包的同级目录下建立config目录，将application-pro.yml、application-dev.yml、application.yml 这些配置文件放到config目录下，即可将jar包引用外置配置文件。    





