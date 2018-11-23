#!/bin/bash

APP_NAME=/data/appcenter/zookeeperDemo-0.0.1-SNAPSHOT/zookeeperDemo-0.0.1-SNAPSHOT.jar
JVM="-server -Xms1024m -Xmx1024m -XX:PermSize=64M -XX:MaxNewSize=128m -XX:MaxPermSize=128m -Djava.awt.headless=true -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled"

usage() { 
echo "Usage: sh ִprocessing.sh [start|stop|restart|status]"
exit 1
} 

is_exist(){ 
pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}'`
if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi

} 

start(){ 
is_exist 
if [ $? -eq "0" ]; then 
echo "${APP_NAME} is already running. pid=${pid} ." 
else 
nohup java $JVM -jar  $APP_NAME > log.out 2>&1 &
echo "${APP_NAME} running. pid=$! ."
fi
} 

stop(){ 
is_exist 
if [ $? -eq "0" ]; then 
kill -9 $pid 
else 
echo "${APP_NAME} is not running" 
fi 
} 

status(){ 
is_exist 
if [ $? -eq "0" ]; then 
echo "${APP_NAME} is running. Pid is ${pid}" 
else 
echo "${APP_NAME} is NOT running." 
fi 
} 

restart(){ 
stop 
start 
} 

case "$1" in 
"start") 
start 
;; 
"stop") 
stop 
;; 
"status") 
status 
;; 
"restart") 
restart 
;; 
*) 
start
;; 
esac