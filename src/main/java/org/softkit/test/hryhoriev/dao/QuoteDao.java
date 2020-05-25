package org.softkit.test.hryhoriev.dao;

import org.softkit.test.hryhoriev.entity.QuoteDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteDao extends JpaRepository<QuoteDto, Long> {

}
