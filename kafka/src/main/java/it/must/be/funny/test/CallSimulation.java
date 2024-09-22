package it.must.be.funny.test;

import it.must.be.funny.test.model.ACR;
import it.must.be.funny.test.model.ERB;
import it.must.be.funny.test.model.IDP;
import it.must.be.funny.test.model.RC;

import java.util.concurrent.Callable;

public class CallSimulation implements Callable<Void> {
    private final String caller;
    private final String callee;

    public CallSimulation(String caller, String callee) {
        this.caller = caller;
        this.callee = callee;
    }

    @Override
    public Void call() throws Exception {
        // Bắt đầu cuộc gọi
        IDP idp = new IDP(caller, callee);
        System.out.println("Bản tin IDP: " + idp);

        // Mô phỏng kết nối thành công
        Thread.sleep((int) (Math.random() * 10)); // Giả lập độ trễ kết nối ngẫu nhiên
        ERB erbConnected = new ERB(idp.getCallId(), "Connected");
        System.out.println("Bản tin ERB: " + erbConnected);

        // Mô phỏng cuộc gọi diễn ra
        Thread.sleep((int) (Math.random() * 10000)); // Giả lập thời gian cuộc gọi ngẫu nhiên
        ACR acr = new ACR(idp.getCallId(), Math.random() * 5); // Giả lập cước phí ngẫu nhiên
        System.out.println("Bản tin ACR: " + acr);

        // Mô phỏng ngắt kết nối
        Thread.sleep((int) (Math.random() * 5)); // Giả lập độ trễ ngắt kết nối ngẫu nhiên
        RC rc = new RC(idp.getCallId(), "Normal release");
        System.out.println("Bản tin RC: " + rc);

        // Mô phỏng báo cáo kết thúc cuộc gọi
        ERB erbReleased = new ERB(idp.getCallId(), "Released");
        System.out.println("Bản tin ERB: " + erbReleased);

        return null;
    }
}
