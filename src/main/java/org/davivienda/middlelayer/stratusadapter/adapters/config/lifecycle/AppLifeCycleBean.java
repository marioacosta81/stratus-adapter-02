package org.davivienda.middlelayer.stratusadapter.adapters.config.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class AppLifeCycleBean {
        private static final Logger LOGGER = LoggerFactory.getLogger( AppLifeCycleBean.class);
        void onStart(@Observes StartupEvent evt){
            LOGGER.info("The application is starting");
        }
        void onStop(@Observes ShutdownEvent evt){
            LOGGER.info("The application is stopping");
        }
}
