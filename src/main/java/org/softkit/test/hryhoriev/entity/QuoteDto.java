package org.softkit.test.hryhoriev.entity;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.Proxy;
import org.softkit.test.hryhoriev.utils.MapperUtil;
import pl.zankowski.iextrading4j.api.stocks.Quote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Arrays;

@Entity
@Table(name = "t_quote")
@Proxy(lazy = false)
public class QuoteDto {

    @Id
    @SequenceGenerator( name = "quoteSequence", sequenceName = "QUOTE_SEQUENCE", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "quoteSequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(unique = true)
    private String symbol;
    private String companyName;
    private String primaryExchange;
    private String calculationPrice;
    private BigDecimal open;
    private Long openTime;
    private BigDecimal close;
    private Long closeTime;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal volume;
    private BigDecimal latestPrice;
    private String latestSource;
    private String latestTime;
    private Long latestUpdate;
    private BigDecimal latestVolume;
    private BigDecimal iexRealtimePrice;
    private BigDecimal iexRealtimeSize;
    private Long iexLastUpdated;
    private BigDecimal delayedPrice;
    private Long delayedPriceTime;
    private BigDecimal extendedPrice;
    private BigDecimal extendedChange;
    private BigDecimal extendedChangePercent;
    private Long extendedPriceTime;
    private BigDecimal previousClose;
    private BigDecimal previousVolume;
    private BigDecimal change;
    private BigDecimal changePercent;
    private BigDecimal iexMarketPercent;
    private BigDecimal iexVolume;
    private BigDecimal avgTotalVolume;
    private BigDecimal iexBidPrice;
    private BigDecimal iexBidSize;
    private BigDecimal iexAskPrice;
    private BigDecimal iexAskSize;
    private BigDecimal marketCap;
    private BigDecimal peRatio;
    @Column(name = "week52_high")
    private BigDecimal week52High;
    @Column(name = "week52_low")
    private BigDecimal week52Low;
    private BigDecimal ytdChange;
    private Long lastTradeTime;
    @Column(name = "is_us_market_open")
    private Boolean isUSMarketOpen;

    public QuoteDto() {
    }

    public QuoteDto(Quote quote) {
        MapperUtil.mapEntityToDto(quote, this, Arrays.asList("id"));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(BigDecimal latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public Long getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(Long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public BigDecimal getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(BigDecimal latestVolume) {
        this.latestVolume = latestVolume;
    }

    public BigDecimal getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public void setIexRealtimePrice(BigDecimal iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    public BigDecimal getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public void setIexRealtimeSize(BigDecimal iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    public Long getIexLastUpdated() {
        return iexLastUpdated;
    }

    public void setIexLastUpdated(Long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    public BigDecimal getDelayedPrice() {
        return delayedPrice;
    }

    public void setDelayedPrice(BigDecimal delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    public Long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public void setDelayedPriceTime(Long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    public BigDecimal getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(BigDecimal extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public BigDecimal getExtendedChange() {
        return extendedChange;
    }

    public void setExtendedChange(BigDecimal extendedChange) {
        this.extendedChange = extendedChange;
    }

    public BigDecimal getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public void setExtendedChangePercent(BigDecimal extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    public Long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    public void setExtendedPriceTime(Long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    public BigDecimal getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(BigDecimal previousClose) {
        this.previousClose = previousClose;
    }

    public BigDecimal getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(BigDecimal previousVolume) {
        this.previousVolume = previousVolume;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(BigDecimal changePercent) {
        this.changePercent = changePercent;
    }

    public BigDecimal getIexMarketPercent() {
        return iexMarketPercent;
    }

    public void setIexMarketPercent(BigDecimal iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    public BigDecimal getIexVolume() {
        return iexVolume;
    }

    public void setIexVolume(BigDecimal iexVolume) {
        this.iexVolume = iexVolume;
    }

    public BigDecimal getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public void setAvgTotalVolume(BigDecimal avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    public BigDecimal getIexBidPrice() {
        return iexBidPrice;
    }

    public void setIexBidPrice(BigDecimal iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    public BigDecimal getIexBidSize() {
        return iexBidSize;
    }

    public void setIexBidSize(BigDecimal iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    public BigDecimal getIexAskPrice() {
        return iexAskPrice;
    }

    public void setIexAskPrice(BigDecimal iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    public BigDecimal getIexAskSize() {
        return iexAskSize;
    }

    public void setIexAskSize(BigDecimal iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(BigDecimal peRatio) {
        this.peRatio = peRatio;
    }

    public BigDecimal getWeek52High() {
        return week52High;
    }

    public void setWeek52High(BigDecimal week52High) {
        this.week52High = week52High;
    }

    public BigDecimal getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(BigDecimal week52Low) {
        this.week52Low = week52Low;
    }

    public BigDecimal getYtdChange() {
        return ytdChange;
    }

    public void setYtdChange(BigDecimal ytdChange) {
        this.ytdChange = ytdChange;
    }

    public Long getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(Long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    @Column(name = "is_u_s_market_open")
    public Boolean getUSMarketOpen() {
        return isUSMarketOpen;
    }

    public void setUSMarketOpen(Boolean USMarketOpen) {
        isUSMarketOpen = USMarketOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuoteDto quoteDto = (QuoteDto) o;

        if (!symbol.equals(quoteDto.symbol)) return false;
        return companyName.equals(quoteDto.companyName);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + companyName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("symbol", symbol)
                .add("companyName", companyName)
                .add("primaryExchange", primaryExchange)
                .add("calculationPrice", calculationPrice)
                .add("open", open)
                .add("openTime", openTime)
                .add("close", close)
                .add("closeTime", closeTime)
                .add("high", high)
                .add("low", low)
                .add("volume", volume)
                .add("latestPrice", latestPrice)
                .add("latestSource", latestSource)
                .add("latestTime", latestTime)
                .add("latestUpdate", latestUpdate)
                .add("latestVolume", latestVolume)
                .add("iexRealtimePrice", iexRealtimePrice)
                .add("iexRealtimeSize", iexRealtimeSize)
                .add("iexLastUpdated", iexLastUpdated)
                .add("delayedPrice", delayedPrice)
                .add("delayedPriceTime", delayedPriceTime)
                .add("extendedPrice", extendedPrice)
                .add("extendedChange", extendedChange)
                .add("extendedChangePercent", extendedChangePercent)
                .add("extendedPriceTime", extendedPriceTime)
                .add("previousClose", previousClose)
                .add("previousVolume", previousVolume)
                .add("change", change)
                .add("changePercent", changePercent)
                .add("iexMarketPercent", iexMarketPercent)
                .add("iexVolume", iexVolume)
                .add("avgTotalVolume", avgTotalVolume)
                .add("iexBidPrice", iexBidPrice)
                .add("iexBidSize", iexBidSize)
                .add("iexAskPrice", iexAskPrice)
                .add("iexAskSize", iexAskSize)
                .add("marketCap", marketCap)
                .add("peRatio", peRatio)
                .add("week52High", week52High)
                .add("week52Low", week52Low)
                .add("ytdChange", ytdChange)
                .add("lastTradeTime", lastTradeTime)
                .add("isUSMarketOpen", isUSMarketOpen)
                .toString();
    }
}
