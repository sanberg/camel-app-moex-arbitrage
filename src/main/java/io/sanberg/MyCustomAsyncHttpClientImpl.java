package io.sanberg;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;

public class MyCustomAsyncHttpClientImpl extends DefaultAsyncHttpClient {
    @Override
    public BoundRequestBuilder prepareGet(String url) {
        // OAuth token
        String id = "PKE5PQRGSKVQD6LQRJRK";
        String key = "yQ2lOJH8zFU1UVH4crFf0CMqe7Hmq7bTUTON4qX4";
        // Headers for the handshake request
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        httpHeaders.set("Apca-Api-Key-Id", id);
        httpHeaders.set("Apca-Api-Secret-Key", key);
        // Prepare the websocket upgrade handshake
        BoundRequestBuilder boundRequestBuilder = super.prepareGet(url);
        boundRequestBuilder.setHeaders(httpHeaders);
        return boundRequestBuilder;
    }
}
