package org.softkit.test.hryhoriev.dao;

import org.softkit.test.hryhoriev.entity.QuoteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuoteDao extends JpaRepository<QuoteDto, Long> {


    @Query(value = "SELECT * FROM t_quote WHERE symbol = ?1",
            nativeQuery = true)
    QuoteDto findBySymbol(String symbol);

}
