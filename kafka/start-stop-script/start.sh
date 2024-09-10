#!/bin/bash

# Đường dẫn tới file JAR sau khi build
JAR_FILE="./build-output/dist/$(ls ./build-output/dist | grep '.jar')"
CONFIG_DIR="./build-output/config"
LIB_DIR="./build-output/lib"
PID_FILE="./build-output/app.pid"

# Kiểm tra nếu file JAR tồn tại
if [[ -f "$JAR_FILE" ]]; then
    echo "Đang chạy ứng dụng từ file JAR: $JAR_FILE"

    # Kiểm tra thư viện và cấu hình
    if [[ -d "$LIB_DIR" ]]; then
        echo "Sử dụng các thư viện từ: $LIB_DIR"
        LIBS=$(find "$LIB_DIR" -name "*.jar" | tr '\n' ':')
    else
        echo "Không tìm thấy thư mục lib"
        exit 1
    fi

    if [[ -d "$CONFIG_DIR" ]]; then
        echo "Sử dụng các file cấu hình từ: $CONFIG_DIR"
    else
        echo "Không tìm thấy thư mục config"
        exit 1
    fi

    # Thiết lập giới hạn heap size và tạo file PID
    HEAP_SIZE_MIN="512m"
    HEAP_SIZE_MAX="1024m"

    # Chạy ứng dụng Java với giới hạn heap size và ghi PID vào file
    java -cp "$LIBS$JAR_FILE" \
         -Xms$HEAP_SIZE_MIN \
         -Xmx$HEAP_SIZE_MAX \
         -Dconfig.dir="$CONFIG_DIR" \
         -Dpidfile="$PID_FILE" \
         com.example.MainClass &

    # Lưu PID của tiến trình vào file PID
    echo $! > "$PID_FILE"
    echo "Ứng dụng đang chạy với PID: $(cat "$PID_FILE")"

else
    echo "Không tìm thấy file JAR trong thư mục ./build-output/dist"
    exit 1
fi
