package org.softkit.test.hryhoriev.service;

import org.softkit.test.hryhoriev.dao.QuoteDao;
import org.softkit.test.hryhoriev.entity.QuoteDto;
import org.softkit.test.hryhoriev.mappers.QuoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zankowski.iextrading4j.api.exception.IEXTradingException;
import pl.zankowski.iextrading4j.api.refdata.v1.ExchangeSymbol;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.rest.request.refdata.v1.SymbolsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class IEXCloudService {

    private final AtomicLong threadTimeDelimiter = new AtomicLong(System.currentTimeMillis());
    private static final long MIN_REQUEST_DELAY = 10L;

    @Autowired
    private IEXCloudClient cloudClient;

    @Autowired
    private QuoteDao quoteDao;

    @Autowired
    private QuoteMapper quoteMapper;

    public List<ExchangeSymbol> getAllTradingCompanies() {
        return cloudClient.executeRequest(new SymbolsRequestBuilder().build());
    }

    public void loadDataByEnabledCompanies(List<ExchangeSymbol> exchangeSymbolList) {
        exchangeSymbolList.parallelStream()
                .filter(ExchangeSymbol::getEnabled)
                .forEach(this::loadDateByCompany);
    }

    public void loadDateByCompany(ExchangeSymbol exchangeSymbol) {
        try {
            while ((System.currentTimeMillis() - threadTimeDelimiter.get()) < MIN_REQUEST_DELAY) {
                Thread.sleep(MIN_REQUEST_DELAY);
            }
            threadTimeDelimiter.set(System.currentTimeMillis());
            QuoteDto newQuote = quoteMapper.mapEntityToDto(cloudClient.executeRequest(new QuoteRequestBuilder()
                            .withSymbol(exchangeSymbol.getSymbol())
                            .build()),
                    new QuoteDto());

            QuoteDto quoteInDB = quoteDao.findBySymbol(newQuote.getSymbol());

            if (Objects.nonNull(quoteInDB)) {
                newQuote.setId(quoteInDB.getId());
            }
            quoteDao.save(newQuote);

        } catch (IEXTradingException e) {
            loadDateByCompany(exchangeSymbol);
        } catch (InterruptedException ignored) {
        }
    }

}
