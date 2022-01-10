package io.sanberg;

import org.json.JSONObject;

import java.io.IOException;


public class AlorTokenManager {
    public String AccessToken;

    public String getToken() {
//        OkHttpClient client = new OkHttpClient();
//        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType, "");
//        Request request = new Request.Builder()
//                .url("https://oauth.alor.ru/refresh?token=4dacc9d0-ac35-4caa-bc6d-f38c0a876e7d")
//                .method("POST", body)
//                .build();
//        Response response = client.newCall(request).execute();
//        JSONObject json = new JSONObject(response.body().string());
//        token = (String) json.get("AccessToken");
//        //System.out.println(token);
        return this.AccessToken;
    }
}
