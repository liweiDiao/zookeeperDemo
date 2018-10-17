# zookeeper
zookeeper主从服务切换demo

# zookeeper简单配置（具体可以网上搜索，有很多文章）

zookeeper解压即可使用。

1.配置Zookeeper的配置文件
进入zookeeper的conf目录，修改配置文件名
[root@localhost zookeeper-3.4.12]# cd conf
[root@localhost conf]# mv zoo_sample.cfg zoo.cfg
[root@localhost conf]# vim zoo.cfg 

主要修改：
dataDir=/usr/local/zookeeper-3.4.12/data

server.0=192.168.253.1:2888:3888
server.1=192.168.253.1:2889:3889
server.2=192.168.253.1:2890:3890

首先 修改 dataDir，顾名思义就是【数据目录】了，修改成我们自定义的即可。
表单server.X的条目列出构成ZooKeeper服务的服务器。当服务器启动时，它通过查找数据目录中的文件myid来知道它是哪个服务器 


server.X=A:B:C
X-代表服务器编号
A-代表ip
B和C-代表端口，这个端口用来系统之间通信

2、 根据dataDir进行X的配置【这里三台服务器不一样】
找到Zookeeper目录，新建data文件夹，并且在data文件夹下面创建一个文件，叫myid，并且在文件里写入server.X对应的X
如：server.0=192.168.253.1:2888:3888 这个zk下面的myid文件直接写0，server.1=192.168.253.1:2889:3889 这个zk下面的myid文件直接写1。

最后直接启动服务器 zkServer.sh start
输出：

[root@localhost zookeeper-3.4.12]# zkServer.sh start
ZooKeeper JMX enabled by default
Using config: /usr/local/zookeeper-3.4.12/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED

是否启动成功，执行以下命令
zkServer.sh status






