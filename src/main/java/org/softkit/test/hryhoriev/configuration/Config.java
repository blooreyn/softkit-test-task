package org.softkit.test.hryhoriev.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

@Configuration
@PropertySource("classpath:application.properties")
@Slf4j
public class Config {

    @Bean
    public IEXCloudClient getIEXCloudClient(@Value("${iex.token.publish}") String token) {
        IEXCloudClient iexTradingClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1_SANDBOX,
                new IEXCloudTokenBuilder()
                        .withPublishableToken(token)
                        .build());
        log.info("Create IEXTradingClient with token {} success", token);
        return iexTradingClient;
    }
}