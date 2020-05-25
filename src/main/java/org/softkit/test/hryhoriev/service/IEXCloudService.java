package org.softkit.test.hryhoriev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zankowski.iextrading4j.client.IEXCloudClient;

@Service
public class IEXCloudService {

    @Autowired
    private IEXCloudClient cloudClient;

}
