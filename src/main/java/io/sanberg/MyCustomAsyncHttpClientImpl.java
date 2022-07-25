package io.sanberg;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;

public class MyCustomAsyncHttpClientImpl extends DefaultAsyncHttpClient {


    public MyCustomAsyncHttpClientImpl(DefaultAsyncHttpClientConfig build) {
        super(build);
    }

    @Override
    public BoundRequestBuilder prepareGet(String url) {
        String id = "PKE5PQRGSKVQD6LQRJRK";
        String key = "ZZ";
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        httpHeaders.set("Apca-Api-Key-Id", id);
        httpHeaders.set("Apca-Api-Secret-Key", key);
        BoundRequestBuilder boundRequestBuilder = super.prepareGet(url);
        boundRequestBuilder.setHeaders(httpHeaders);
        return boundRequestBuilder;
    }
}
