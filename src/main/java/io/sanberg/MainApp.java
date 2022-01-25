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
        AlorTokenManagerRouteBuilder alorTokenManagerRouteBuilder = new AlorTokenManagerRouteBuilder();
        main.configure().addRoutesBuilder(alorTokenManagerRouteBuilder);
        main.bind("alorToken", alorTokenManagerRouteBuilder);
        main.configure().addRoutesBuilder(new WssOrderBookSubscriptionByTicker());
        main.configure().addRoutesBuilder(new WssConsumerMOEXRouteBuilder());
        main.run(args);
    }



}

