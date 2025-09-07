package it.must.be.funny.structural.facade;

public class Order {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.buyNow("user123", "Product A", 2, "VISA 1234-5678-9012-3456", 100.0);
    }
}

class UserChecker {
    public boolean isValidUser(String userId) {
        // Giả sử kiểm tra người dùng từ cơ sở dữ liệu
        System.out.println("Checking user validity for user: " + userId);
        return true; // Giả sử người dùng luôn hợp lệ
    }
}

class InventoryChecker {
    public boolean isInStock(String product, int quantity) {
        // Giả sử kiểm tra tồn kho từ cơ sở dữ liệu hoặc hệ thống quản lý kho
        System.out.println("Checking inventory for " + quantity + " of " + product);
        return true; // Giả sử luôn có hàng trong kho
    }
}

class PaymentProcessor {
    public boolean processPayment(String paymentDetails, double amount) {
        // Giả sử xử lý thanh toán qua cổng thanh toán
        System.out.println("Processing payment of " + amount + " with details: " + paymentDetails);
        return true; // Giả sử thanh toán thành công
    }
}

class ShoppingCart {
    public double calculateTotal(String[] products, double[] prices) {
        double total = 0;
        for (double price : prices) {
            total += price;
        }
        System.out.println("Calculating total: " + total);
        return total;
    }
    public boolean addToCart(String product, int quantity) {
        // Giả sử thêm sản phẩm vào giỏ hàng
        System.out.println("Adding " + quantity + " of " + product + " to cart");
        return true; // Giả sử thêm thành công
    }
}


/* Giải thích mẫu Facade Pattern:
- Mục đích: Facade Pattern nhằm mục đích cung cấp một giao diện đơn giản và dễ sử dụng cho một hệ thống phức tạp.
- Cách hoạt động: Facade Pattern tạo ra một lớp "mặt tiền" (facade) để ẩn đi các chi tiết phức tạp bên trong hệ thống,
 giúp người dùng tương tác với hệ thống một cách dễ dàng hơn.
- Lợi ích: Giúp giảm sự phụ thuộc giữa các thành phần trong hệ thống, làm cho mã nguồn dễ đọc và bảo trì hơn.
 */
class OrderFacade{
    private UserChecker userChecker;
    private InventoryChecker inventoryChecker;
    private PaymentProcessor paymentProcessor;
    private ShoppingCart shoppingCart;
    public OrderFacade() {
        this.userChecker = new UserChecker();
        this.inventoryChecker = new InventoryChecker();
        this.paymentProcessor = new PaymentProcessor();
        this.shoppingCart = new ShoppingCart();
    }

    public boolean buyNow(String userId, String product, int quantity, String paymentDetail, double price) {
        if (!this.userChecker.isValidUser(userId)) {
            System.out.println("Invalid user.");
            return false;
        }

        if (!this.inventoryChecker.isInStock(product, quantity)) {
            System.out.println("Product is out of stock.");
            return false;
        } 
        if(!this.shoppingCart.addToCart(product, quantity)){
            System.out.println("Failed to add product to cart.");
            return false;
        }
        double total = this.shoppingCart.calculateTotal(new String[]{product}, new double[]{price});
        if (!this.paymentProcessor.processPayment(paymentDetail, total)) {
            System.out.println("Payment failed.");
            return false;
        }
        System.out.println("Order placed successfully for " + quantity + " of " + product);
        return true;
    }
}