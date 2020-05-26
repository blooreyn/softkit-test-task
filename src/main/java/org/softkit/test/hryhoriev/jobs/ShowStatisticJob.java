package org.softkit.test.hryhoriev.jobs;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.a7.A7_Grids;
import lombok.extern.slf4j.Slf4j;
import org.softkit.test.hryhoriev.service.IEXCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

@Service
@EnableScheduling
@Slf4j
public class ShowStatisticJob {
    private static final int LIMIT = 5;

    public static final String COMPANY_NAME = "Company name";
    public static final String LATEST_PRICE = "Latest price";
    public static final String PERCENT_CHANGE = "Percent change";

    public static final String HIGHEST_STOCKS_VALUE_MESSAGE =
            "The top" + LIMIT + " company by highest stocks value as of ";
    public static final String BIGGEST_QUOTE_PERCENT_CHANGE_MESSAGE =
            "The top " + LIMIT + " company by biggest percent change as of ";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LL/yyyy HH:mm:ss");

    @Autowired
    private IEXCloudService iexCloudService;

    @Scheduled(cron = "${show.statistic.cron}")
    @Transactional(isolation = Isolation.READ_COMMITTED)
    private void showData() {
        log.debug("Start new loop for show statistic data");

        showTable(HIGHEST_STOCKS_VALUE_MESSAGE + LocalDateTime.now().format(formatter),
                new String[]{COMPANY_NAME, LATEST_PRICE}, this::addHighestQuoteValueToTable);

        showTable(BIGGEST_QUOTE_PERCENT_CHANGE_MESSAGE + LocalDateTime.now().format(formatter),
                new String[]{COMPANY_NAME, PERCENT_CHANGE}, this::addQuoteWithBiggestPercentChangeToTable);

    }

    private void showTable(String message, Object[] columnNames, Consumer<AsciiTable> consumer) {
        System.out.println(message);
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.getContext().setGrid(A7_Grids.minusBarPlusEquals());
        asciiTable.getContext().setWidth(90);

        asciiTable.addRule();
        asciiTable.addRow(columnNames);
        asciiTable.addRule();

        consumer.accept(asciiTable);

        asciiTable.addRule();
        System.out.println(asciiTable.render());
        System.out.println();
    }

    private void addHighestQuoteValueToTable(AsciiTable asciiTable) {
        iexCloudService.getQuotesByLargestValue(LIMIT)
                .forEach(quote -> {
                    asciiTable.addRule();
                    asciiTable.addRow(quote.getCompanyName(), quote.getLatestPrice());
                });
    }

    private void addQuoteWithBiggestPercentChangeToTable(AsciiTable asciiTable) {
        iexCloudService.getQuotesByLargestPercentChanges(LIMIT)
                .forEach(quote -> {
                    asciiTable.addRule();
                    asciiTable.addRow(quote.getCompanyName(), quote.getChangePercent());
                });
    }

}
