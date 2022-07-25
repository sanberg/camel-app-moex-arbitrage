package io.sanberg;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class AlorTokenManagerRouteBuilder extends RouteBuilder {
    private String accessToken;


    @Override
    public void configure() throws Exception {
        from("timer://authscheduler?period=600000")//.startupOrder(1)
                .log("Refreshing Alor token")
                .setHeader(Exchange.HTTP_METHOD)
                .constant(HttpMethod.POST)
                .to("https://oauth.alor.ru/refresh?token=ZZ")
                .unmarshal().json(AlorToken.class)
                .process(exchange -> {
                    AlorToken response = exchange.getIn().getBody(AlorToken.class);
                    setAccessToken(response.getAccessToken());
                });
                //.log("${body}"); //+ method("alorTokenManager", "getToken"));
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

class AlorToken {
    private String accessToken;

    public AlorToken() {
    }

    public AlorToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("AccessToken")
    public void setAccessToken(String AccessToken) {
        this.accessToken = AccessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}


