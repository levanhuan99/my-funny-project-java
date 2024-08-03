package it.must.be.funny.actuator;

import com.sun.net.httpserver.HttpServer;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

public class Actuator {

    // Prometheus registry which holds all registered metrics and their properties
    private PrometheusMeterRegistry prometheusMeterRegistry;

    // Http server serves metric pulling requests
    private HttpServer server;

    // Endpoint path of http server serves pulling requests
    private static final String endpointPath = "/metrics";

    // Singleton instance of actuator
    private static Actuator actuator;

    private Actuator() {

    }

    private Actuator(String applicationName) {
        prometheusMeterRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        prometheusMeterRegistry.config().commonTags("application", applicationName);

        // These classes are for exposing JVM specific metrics
        new ClassLoaderMetrics().bindTo(prometheusMeterRegistry);
        new JvmMemoryMetrics().bindTo(prometheusMeterRegistry);
        new JvmGcMetrics().bindTo(prometheusMeterRegistry);
        new ProcessorMetrics().bindTo(prometheusMeterRegistry);
        new JvmThreadMetrics().bindTo(prometheusMeterRegistry);
        new UptimeMetrics().bindTo(prometheusMeterRegistry);
//        new Log4j2Metrics().bindTo(prometheusMeterRegistry);
        new FileDescriptorMetrics().bindTo(prometheusMeterRegistry);
    }

    public static Actuator getInstance(String applicationName) {
        if (actuator == null) {
            actuator = new Actuator(applicationName);
        }

        return actuator;
    }

    public static PrometheusMeterRegistry getPrometheusMeterRegistry() {
        if (actuator == null) {
            System.out.println("Actuator instance had not registered yet.");
            System.exit(1);
        }
        return actuator.prometheusMeterRegistry;
    }

    public void start(int port) throws IOException {
        // create HTTP server endpoint which will expose endpoint to /metrics
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(java.util.concurrent.Executors.newFixedThreadPool(2));

        server.createContext(
                endpointPath,
                httpExchange -> {
                    String response = prometheusMeterRegistry.scrape();
                    httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes().length);
                    try (OutputStream os = httpExchange.getResponseBody()) {
                        os.write(response.getBytes());
                    }
                });

        server.start();

        System.out.println("Prometheus metric exporter has started at port {}"+ port);
    }

    public void stop() {
        server.stop(0);
    }
}
