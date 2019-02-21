package com.taco.loco.application.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddedServerConfiguration {

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.identification}")
    private String serverHeaderValue;

    public TomcatServletWebServerFactory containerFactory() {
        return new TomcatServletWebServerFactory() {
            protected void customizeConnector(Connector connector) {
                super.customizeConnector(connector);
                connector.setProperty("server", serverHeaderValue);
                connector.setPort(Integer.parseInt(serverPort));
            }
        };
    }

}


