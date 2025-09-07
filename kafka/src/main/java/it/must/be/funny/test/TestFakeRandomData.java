package it.must.be.funny.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestFakeRandomData {

    private static final List<String> PURCHASE_FREQUENCY = Arrays.asList("2 lần/tháng", "1 lần/tuần", "3 lần/tuần", "1 lần/tháng", "2 lần/tuần");
    private static final List<String> ACCESS_TIME = Arrays.asList("8:00 - 10:00 AM", "6:00 - 8:00 PM", "12:00 - 1:00 PM", "9:00 - 11:00 AM", "7:00 - 9:00 PM");
    private static final List<String> DEVICES = Arrays.asList("Điện thoại", "Laptop", "Máy tính bảng");
    private static final List<String> INTERESTED_PRODUCTS = Arrays.asList("Thời trang, mỹ phẩm", "Đồ điện tử", "Đồ gia dụng", "Đồ chơi trẻ em", "Sách");
    private static final List<String> SOCIAL_MEDIA = Arrays.asList("Instagram", "Facebook", "TikTok", "Twitter");
    private static final List<String> SEARCH_TRENDS = Arrays.asList("Thời trang, khuyến mãi", "Công nghệ mới, review", "Mẹo vặt, nấu ăn", "Sức khỏe, gia đình", "Sách, học tập");
    private static final List<String> FEEDBACK = Arrays.asList("Tích cực", "Trung lập", "Tiêu cực");

    private static final Random RANDOM = new Random();

    // Hàm tạo dữ liệu ngẫu nhiên cho một dòng
    private static String generateRandomRow(int userId) {
        String user = "User " + userId;
        String purchaseFrequency = PURCHASE_FREQUENCY.get(RANDOM.nextInt(PURCHASE_FREQUENCY.size()));
        String accessTime = ACCESS_TIME.get(RANDOM.nextInt(ACCESS_TIME.size()));
        String device = DEVICES.get(RANDOM.nextInt(DEVICES.size()));
        String product = INTERESTED_PRODUCTS.get(RANDOM.nextInt(INTERESTED_PRODUCTS.size()));
        String socialMedia = SOCIAL_MEDIA.get(RANDOM.nextInt(SOCIAL_MEDIA.size()));
        String searchTrend = SEARCH_TRENDS.get(RANDOM.nextInt(SEARCH_TRENDS.size()));
        String feedback = FEEDBACK.get(RANDOM.nextInt(FEEDBACK.size()));

        return user + "|" + purchaseFrequency + "|" + accessTime + "|" + device + "|" + product + "|" + socialMedia + "|" + searchTrend + "|" + feedback;
    }

    // Hàm ghi dữ liệu vào file
    public static void generateDataToFile(String filePath, int numberOfRows) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Ghi dòng header
            writer.write("Người dùng|Tần suất mua hàng|Thời gian truy cập|Thiết bị sử dụng|Sản phẩm quan tâm|Nền tảng mạng xã hội ưa thích|Xu hướng tìm kiếm|Phản hồi");
            writer.newLine();

            // Ghi dữ liệu ngẫu nhiên
            for (int i = 1; i <= numberOfRows; i++) {
                writer.write(generateRandomRow(i));
                writer.newLine();

                // Hiển thị tiến trình (tùy chọn)
                if (i % 1000000 == 0) {
                    System.out.println("Đã tạo " + i + " dòng dữ liệu.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Số dòng muốn tạo, ví dụ 100 triệu
        int numberOfRows = 100_000_000;
        // Đường dẫn file xuất ra
        String filePath = "fake_user_data.txt";

        // Gọi hàm tạo dữ liệu
        generateDataToFile(filePath, numberOfRows);

        System.out.println("Hoàn thành việc tạo dữ liệu.");
    }
}
