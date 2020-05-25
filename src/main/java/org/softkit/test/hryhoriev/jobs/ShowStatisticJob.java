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

import java.time.LocalDate;

@Service
@EnableScheduling
@Slf4j
public class ShowStatisticJob {
    private static final int LIMIT = 5;
    public static final String COMPANY_NAME = "Company name";
    public static final String LATEST_PRICE = "Latest price";
    public static final String PERCENT_CHANGE = "Percent change";


    @Autowired
    private IEXCloudService iexCloudService;

    @Scheduled(cron = "${show.statistic.cron}")
    @Transactional(isolation = Isolation.READ_COMMITTED)
    private void showData() {
        log.debug("Start new loop for show statistic data");
        AsciiTable gratesValueTable = new AsciiTable();
        gratesValueTable.getContext().setGrid(A7_Grids.minusBarPlusEquals());
        gratesValueTable.getContext().setWidth(90);

        gratesValueTable.addRule();
        gratesValueTable.addRow(COMPANY_NAME, LATEST_PRICE);
        gratesValueTable.addRule();
        iexCloudService.getQuotesByLargestValue(LIMIT)
                .forEach(quote -> {
                    gratesValueTable.addRule();
                    gratesValueTable.addRow(quote.getCompanyName(), quote.getLatestPrice());
                });
        gratesValueTable.addRule();

        System.out.println("The top 5 company by highest stocks value as of " + LocalDate.now());
        System.out.println(gratesValueTable.render());
        System.out.println();

        AsciiTable gratesChangePercentTable = new AsciiTable();
        gratesChangePercentTable.getContext().setGrid(A7_Grids.minusBarPlusEquals());
        gratesChangePercentTable.getContext().setWidth(90);

        gratesChangePercentTable.addRule();
        gratesChangePercentTable.addRow(COMPANY_NAME, PERCENT_CHANGE);
        gratesChangePercentTable.addRule();
        iexCloudService.getQuotesByLargestPercentChanges(LIMIT)
                .forEach(quote -> {
                    gratesChangePercentTable.addRule();
                    gratesChangePercentTable.addRow(quote.getCompanyName(), quote.getChangePercent());
                });
        gratesChangePercentTable.addRule();

        System.out.println("The top 5 company by highest stocks value as of " + LocalDate.now());
        System.out.println(gratesChangePercentTable.render());
        System.out.println();

    }
}
