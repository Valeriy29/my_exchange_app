package com.example.exchangeapp.service;

import com.example.exchangeapp.ws.WebsocketClientEndpoint;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

    @Override
    public void getExchangeCandlestick(String exchangeUrl, Consumer<String> saveInfo) {
        try {
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(exchangeUrl));

            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    saveInfo.accept(message);
                    log.info(message);
                }
            });

            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }

}
