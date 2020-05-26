package org.softkit.test.hryhoriev.service;

import lombok.extern.slf4j.Slf4j;
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

@Service
@Slf4j
public class IEXCloudService {

    @Autowired
    private IEXCloudClient cloudClient;

    @Autowired
    private QuoteDao quoteDao;

    @Autowired
    private QuoteMapper quoteMapper;

    public List<ExchangeSymbol> getAllTradingCompanies() {
        List<ExchangeSymbol> exchangeSymbolList = cloudClient.executeRequest(new SymbolsRequestBuilder().build());
        log.debug("Success load ExchangeSymbol list.List size = {}", exchangeSymbolList.size());
        return exchangeSymbolList;
    }

    public void loadDataByEnabledCompanies(List<ExchangeSymbol> exchangeSymbolList) {
        exchangeSymbolList.parallelStream()
                .filter(ExchangeSymbol::getEnabled)
                .forEach(this::loadDateByCompany);
    }

    public void loadDateByCompany(ExchangeSymbol exchangeSymbol) {
        try {
            QuoteDto newQuote = quoteMapper.mapEntityToDto(cloudClient.executeRequest(new QuoteRequestBuilder()
                            .withSymbol(exchangeSymbol.getSymbol())
                            .build()),
                    new QuoteDto());

            log.debug("Success load Quote by symbol = {}", exchangeSymbol.getSymbol());
            QuoteDto quoteInDB = quoteDao.findBySymbol(newQuote.getSymbol());
            if (Objects.nonNull(quoteInDB)) {
                newQuote.setId(quoteInDB.getId());
            }
            quoteDao.save(newQuote);

        } catch (IEXTradingException e) {
            log.warn("Fail to load data by company {}", exchangeSymbol.getName());
            loadDateByCompany(exchangeSymbol);
        }
    }

    public List<QuoteDto> getQuotesByLargestValue(int limit) {
        return quoteDao.findLimitOrderByQuoteValue(limit);
    }

    public List<QuoteDto> getQuotesByLargestPercentChanges(int limit) {
        return quoteDao.findLimitOrderByChangePercent(limit);
    }

}
