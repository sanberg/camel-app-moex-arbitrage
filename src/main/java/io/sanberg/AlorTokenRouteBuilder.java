package io.sanberg;

import io.netty.handler.codec.http.HttpMethod;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

public class AlorTokenRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        JacksonDataFormat tokenDF = new JacksonDataFormat(AlorTokenManager.class);
        from("timer:scheduler?period=60000")
                .log("Refreshing Alor token")
                .setHeader(Exchange.HTTP_METHOD).constant(HttpMethod.POST)
                .to("https://oauth.alor.ru/refresh?token=4dacc9d0-ac35-4caa-bc6d-f38c0a876e7d")
                //.log("Response: ${body}")
                .unmarshal(tokenDF)
                .log("unmarshalled ${body}");

    }
}

