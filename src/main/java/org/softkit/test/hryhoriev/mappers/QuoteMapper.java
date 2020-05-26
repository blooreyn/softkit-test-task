package org.softkit.test.hryhoriev.mappers;

import org.softkit.test.hryhoriev.constants.MapperIgnoredFields;
import org.softkit.test.hryhoriev.entity.QuoteDto;
import org.springframework.stereotype.Component;
import pl.zankowski.iextrading4j.api.stocks.Quote;

@Component
public class QuoteMapper extends GenericEntityMapper<Quote, QuoteDto> {

    public QuoteMapper() {
        super(Quote.class, QuoteDto.class, MapperIgnoredFields.QUOTE_DTO);
    }

}
