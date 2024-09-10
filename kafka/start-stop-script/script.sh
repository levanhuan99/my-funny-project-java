#!/bin/bash

#Đường dâ file main
MAIN_CLASS="it.must.be.funny.KafkaMain"

# Đường dẫn đến thư mục chứa các thư viện phụ thuộc
LIB_DIR="target/lib"

# Đường dẫn đến tệp JAR chính
JAR_FILE="target/kafka-1.0-SNAPSHOT.jar"

# Đường dẫn đến thư mục chứa tệp cấu hình (nếu cần)
CONFIG_DIR="target/config"

# Xây dựng classpath từ các thư viện trong thư mục lib
CLASSPATH=$JAR_FILE
for jar in $LIB_DIR/*.jar; do
    CLASSPATH=$CLASSPATH:$jar
done

# Xuất biến môi trường (nếu cần)
export CONFIG_DIR

# Cấu hình heap memory
HEAP_SIZE="3G"

# Đường dẫn đến tệp ghi PID
PID_FILE="app.pid"

# Chạy ứng dụng Java
nohup java -Xmx$HEAP_SIZE  -cp $CLASSPATH $MAIN_CLASS > app.log 2>&1 &


# Ghi PID vào tệp PID_FILE
echo $! > $PID_FILE

echo "Application started with PID $(cat $PID_FILE) and logging to app.log"
