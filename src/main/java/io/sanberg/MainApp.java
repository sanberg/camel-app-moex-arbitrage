package io.sanberg;

import org.apache.camel.main.Main;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        StocksDataMap stocksDataMap = new StocksDataMap();
        main.bind("stocksDataMap", stocksDataMap);

        //Alor

//        AlorTokenManagerRouteBuilder alorTokenManagerRouteBuilder = new AlorTokenManagerRouteBuilder();
//        main.configure().addRoutesBuilder(alorTokenManagerRouteBuilder);
//        main.bind("alorToken", alorTokenManagerRouteBuilder);
//
//        main.configure().addRoutesBuilder(new AlorQuoteDataMOEXSubscribeByTickerRouteBuilder());
//        main.configure().addRoutesBuilder(new AlorQuoteDataSPBSubscribeByTickerRouteBuilder());
//        main.configure().addRoutesBuilder(new AlorQuoteDataConsumerRouteBuilder());
//        main.configure().addRoutesBuilder(new TelegramArbitrageNotifierRouteBuilder());

        //Alpaca

        //custom httpclient for ws auth by headers and custom config of buffer & frames
        MyCustomAsyncHttpClientImpl myCustomAsyncHttpClientImpl = new MyCustomAsyncHttpClientImpl(
                new DefaultAsyncHttpClientConfig.Builder()
                        .setMaxRequestRetry(0)
                        .setWebSocketMaxBufferSize(2621440)
                        .setWebSocketMaxFrameSize(2621440).build());

        main.bind("myCustomAsyncHttpClientImpl", myCustomAsyncHttpClientImpl);
        main.configure().addRoutesBuilder(new AlpacaConsumerUSRouteBuilder());
        main.configure().addRoutesBuilder(new AlpacaSubscriptionByTickerRouteBuilder());

        main.run(args);
    }


}

