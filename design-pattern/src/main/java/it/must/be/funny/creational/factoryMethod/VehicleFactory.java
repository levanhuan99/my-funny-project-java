package it.must.be.funny.creational.factoryMethod;


/**
 * xu ly cac bai toan:
 * 1. Quản lý kết nối cơ sở dữ liệu: mysql, prostgre,mssql,...
 * 2. Hệ thống giao diện người dùng (UI): macos,windown,...
 * 3. Xử lý tài liệu (Document Processing): pdf,word,...
 * 4. Hệ thống thông báo (Notification System): mail, sms,...
 * 5. Xử lý thanh toán (Payment Processing): creditcar,paypal,...
 * 6. Hệ thống trò chơi (Game Development):
 * 7. Xử lý dữ liệu từ file (File Parsing):json, xml,...
 * 8. Hệ thống quản lý nhân sự (Human Resource Management): fulltime, partime,...
 */
public abstract class VehicleFactory {

    public abstract Vehicle createVehicle();

    public void driveVehicle(){
        Vehicle vehicle = createVehicle();
        vehicle.drive();
    }
    public void getBrokenVehicle(String something){
        Vehicle vehicle = createVehicle();
        vehicle.getBrokenBySomething(something);
    }
    public void fixVehicle(){
        Vehicle vehicle = createVehicle();
        vehicle.fix();
    }
}

