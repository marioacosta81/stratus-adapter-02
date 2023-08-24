package org.davivienda;


import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusMain
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String ... args){
        Quarkus.run(CustonMain.class,args);
    }

    public static class CustonMain implements QuarkusApplication{

        @Override
        public int run(String... args) throws Exception {
            LOGGER.info("Run custom main method");
            Quarkus.waitForExit();
            return 0;
        }
    }



}
