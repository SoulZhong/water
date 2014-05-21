#! /bin/bash

cd `dirname $0`
BIN_DIR=`pwd`
cd ..
HOME=`pwd`
CONFIG_DIR=$HOME/config
LIB_DIR=$HOME/lib
CLASSES_DIR=$HOME/classes

LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

case "$1" in

  start)
  	nohup java -server -XX:ErrorFile=./log/hs_err_pid.log -Xloggc:./log/gc.log -XX:-HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:+UseConcMarkSweepGC -Xmx64m -Xms32m -classpath $LIB_JARS:$BIN_DIR:$CLASSES_DIR:$CONFIG_DIR com.itag.water.platform.StartService &
  	echo " =================== Server Started =================== "
    echo $! > $BIN_DIR/server.pid
    ;;

  stop)
    kill `cat $BIN_DIR/server.pid`
    rm -rf $BIN_DIR/server.pid
    ;;

  restart)
    $0 stop
    sleep 1
    $0 start
    ;;

  *)
    echo "Usage: run.sh {start|stop|restart}"
    ;;

esac

exit 0