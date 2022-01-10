package io.sanberg;

import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        //file xpath checker
        //main.configure().addRoutesBuilder(new MyRouteBuilder());
        //timer
        //main.configure().addRoutesBuilder(new MyTestTimerRouteBuilder());
        //timer to log
        //main.configure().addRoutesBuilder(new MyTimerToLogRouteBuilder());
        //main.configure().addRoutesBuilder(new WssConsumerMOEXRouteBuilder());
        //main.configure().addRoutesBuilder(new WssOrderBookSubsctiptionByTicker("BIDU"));
        main.configure().addRoutesBuilder(new AlorTokenRouteBuilder());
        
        main.run(args);
    }



}
