package org.softkit.test.hryhoriev.mappers;

import org.softkit.test.hryhoriev.entity.QuoteDto;
import org.springframework.stereotype.Component;
import pl.zankowski.iextrading4j.api.stocks.Quote;

import java.util.Collections;
import java.util.List;

@Component
public class QuoteMapper extends GenericEntityMapper<Quote, QuoteDto> {
    private static final List<String> IGNORED_FIELDS = Collections.singletonList("id");

    public QuoteMapper() {
        super(Quote.class, QuoteDto.class, IGNORED_FIELDS);
    }

}
