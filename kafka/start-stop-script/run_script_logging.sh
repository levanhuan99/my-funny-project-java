#!/bin/bash

# Đường dẫn đến lớp chính
MAIN_CLASS="it.must.be.funny.KafkaMain"

# Đường dẫn đến thư mục chứa các thư viện phụ thuộc
LIB_DIR="target/lib"

# Đường dẫn đến tệp JAR chính
JAR_FILE="target/kafka-1.0-SNAPSHOT.jar"

# Đường dẫn đến thư mục chứa tệp cấu hình (nếu cần)
CONFIG_DIR="target/config"

# Đường dẫn đến tệp ghi PID
PID_FILE="app.pid"

# Đường dẫn đến tệp log
LOG_FILE="app.log"

# Xây dựng classpath từ các thư viện trong thư mục lib và thư mục cấu hình
if [ ! -d "$LIB_DIR" ]; then
    echo "Directory $LIB_DIR does not exist."
    exit 1
fi

CLASSPATH=$JAR_FILE
for jar in $LIB_DIR/*.jar; do
    CLASSPATH=$CLASSPATH:$jar
done

if [ -d "$CONFIG_DIR" ]; then
    CLASSPATH=$CLASSPATH:$CONFIG_DIR
else
    echo "Configuration directory $CONFIG_DIR does not exist."
    exit 1
fi

# Kiểm tra sự tồn tại của tệp JAR chính
if [ ! -f "$JAR_FILE" ]; then
    echo "JAR file $JAR_FILE does not exist."
    exit 1
fi

# Tạo thư mục chứa tệp log nếu chưa tồn tại
mkdir -p $(dirname "$LOG_FILE")

echo "classpath: $CLASSPATH"
# Chạy ứng dụng Java
nohup java -Xmx$HEAP_SIZE -cp $CLASSPATH $MAIN_CLASS > $LOG_FILE 2>&1 &

# Ghi PID vào tệp PID_FILE
echo $! > $PID_FILE

echo "Application started with PID $(cat $PID_FILE) and logging to $LOG_FILE"