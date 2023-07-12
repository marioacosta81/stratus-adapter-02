package org.davivienda.middlelayer.stratusadapter;


import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {

    public static void main(String ... args){
        Quarkus.run(CustonMain.class,args);
    }

    public static class CustonMain implements QuarkusApplication{

        @Override
        public int run(String... args) throws Exception {
            System.out.println("Run custom main method");
            Quarkus.waitForExit();
            return 0;
        }
    }



}
