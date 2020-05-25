package org.softkit.test.hryhoriev.jobs;

import lombok.extern.slf4j.Slf4j;
import org.softkit.test.hryhoriev.service.IEXCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateQuoteJob {
    @Autowired
    private IEXCloudService iexCloudService;

    @EventListener(ApplicationReadyEvent.class)
    private void loadDataByCompanies() {
        log.debug("Start new loop for load company data");
        iexCloudService.loadDataByEnabledCompanies(iexCloudService.getAllTradingCompanies());
        loadDataByCompanies();
    }
}
