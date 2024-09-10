#!/bin/bash

# Đường dẫn tới file PID
PID_FILE="./build-output/app.pid"

# Kiểm tra nếu file PID tồn tại
if [[ -f "$PID_FILE" ]]; then
    PID=$(cat "$PID_FILE")
    if [[ -n "$PID" ]]; then
        # Kiểm tra nếu tiến trình đang chạy
        if ps -p $PID > /dev/null; then
            echo "Đang dừng tiến trình với PID: $PID"
            kill $PID
            # Kiểm tra xem tiến trình có dừng không
            if [[ $? -eq 0 ]]; then
                echo "Tiến trình đã dừng thành công."
                rm "$PID_FILE"  # Xóa file PID sau khi tiến trình đã dừng
            else
                echo "Không thể dừng tiến trình với PID: $PID"
            fi
        else
            echo "Tiến trình với PID $PID không còn chạy."
            rm "$PID_FILE"  # Xóa file PID nếu tiến trình không còn chạy
        fi
    else
        echo "File PID không chứa PID hợp lệ."
    fi
else
    echo "Không tìm thấy file PID."
fi
