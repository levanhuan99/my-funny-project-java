package it.must.be.funny.creational;

/**
 * author by huanlv13
 * singleton degign pattern nhằm mục đích chỉ cho tạo 1 Intance duy nhất
 * mẫu dp này co thể được dùng để gọi lây kết nối đến csdl, cấu hình,...
 * NOTE:
 * biến STATIC và phương thức STATIC getIntance là rất quan trọng
 */
public class Singleton {

    // Biến static lưu trữ thể hiện UY NHẤT của Singleton
    private static Singleton singleton;

    //đặt access modifier là private để không cho phép tạo instance
    private Singleton() {
    }

    // Phương thức static để trả về thể hiện duy nhất của Singleton
    public static Singleton getInstance(){
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    public void otherFunc(){
        System.out.println("demo mesg");
    }
}
