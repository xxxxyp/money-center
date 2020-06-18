package com.fourboy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class MoneyCenterApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(MoneyCenterApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (path == null) {
            path = "";
        }
        log.info("\n----------------------------------------------------------\n\tApplication midland-zhuanjie-web is running! Access URLs:\n\tLocal: \t\thttp://localhost:" + port + path + "/\n\tExternal: \thttp://" + ip + ":" + port + path + "/\n\tswagger-ui: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\tDoc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n----------------------------------------------------------");

    }

}
