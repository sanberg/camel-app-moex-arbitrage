package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

public class MyTimerToLogRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:foo?period=500").to("log:testRoute1");
    }
}
