package org.softkit.test.hryhoriev.dao;

import java.util.List;
import org.softkit.test.hryhoriev.entity.QuoteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuoteDao extends JpaRepository<QuoteDto, Long> {

    @Query(value = "SELECT * " +
            "FROM t_quote" +
            " WHERE symbol = ?1",
            nativeQuery = true)
    QuoteDto findBySymbol(String symbol);

    @Query(value = "SELECT *" +
            " FROM t_quote" +
            " ORDER BY latest_price, company_name ASC LIMIT ?1",
            nativeQuery = true)
    List<QuoteDto> findLimitOrderByQuoteValue(int limit);

    @Query(value = "SELECT * " +
            "FROM t_quote " +
            "WHERE change_percent IS NOT NULL " +
            "ORDER BY ABS(change_percent) DESC LIMIT ?1",
            nativeQuery = true)
    List<QuoteDto> findLimitOrderByChangePercent(int limit);

}
