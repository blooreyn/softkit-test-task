package org.softkit.test.hryhoriev.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Proxy;
import org.softkit.test.hryhoriev.constants.TableFields;
import org.softkit.test.hryhoriev.constants.TableNames;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "quoteSequence", sequenceName = "QUOTE_SEQUENCE", allocationSize = 1)
@Table(name = TableNames.QUOTE)
@Proxy(lazy = false)
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class QuoteDto {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quoteSequence")
    @Column(name = TableFields.ID , nullable = false, updatable = false)
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
    @Column(name = TableFields.WEEK_52_HIGH)
    private BigDecimal week52High;
    @Column(name = TableFields.WEEK_52_LOW)
    private BigDecimal week52Low;
    private BigDecimal ytdChange;
    private Long lastTradeTime;
    @Column(name = TableFields.IS_US_MARKET_OPEN)
    private Boolean isUSMarketOpen;
}
