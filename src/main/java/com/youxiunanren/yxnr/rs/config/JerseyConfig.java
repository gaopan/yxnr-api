package com.youxiunanren.yxnr.rs.config;

import com.youxiunanren.yxnr.modules.authentication.AuthenticationResource;
import com.youxiunanren.yxnr.modules.health.HealthResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/service")
@Component
public class JerseyConfig extends ResourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(JerseyConfig.class);

    public JerseyConfig(){
        logger.info("Start - Jersey configuration");

        register(HealthResource.class);
        register(AuthenticationResource.class);
    }
}
