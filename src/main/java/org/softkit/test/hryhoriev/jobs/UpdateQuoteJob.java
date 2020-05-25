package org.softkit.test.hryhoriev.jobs;

import org.softkit.test.hryhoriev.service.IEXCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UpdateQuoteJob {
    @Autowired
    private IEXCloudService iexCloudService;

    @EventListener(ApplicationReadyEvent.class)
    private void loadDataByCompanies() {
        iexCloudService.loadDataByEnabledCompanies(iexCloudService.getAllTradingCompanies());
        loadDataByCompanies();
    }
}
