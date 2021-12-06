package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

public class MyTestTimerRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:foo?period=500").log("HelloWorld");
    }
}
