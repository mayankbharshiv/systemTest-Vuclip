package com.vuclip.premiumengg.automation.ad_network_service.tests;

import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSDBUtils;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSTestContext;
import com.vuclip.premiumengg.automation.common.Log4J;
import org.testng.annotations.Test;

public class SavecountryDBTest {

    @Test(groups = {"bug", "positive"})
    public void activationSuccessfulAfterWinback() throws Exception {
        Log4J.getLogger().info("===============================>Starting test case " + SavecountryDBTest.class);
        ANSDBUtils.showProductAdnetworkDetailsTableData("Verify Table", String.valueOf(ANSTestContext.productId),
                String.valueOf(ANSTestContext.partnerId));
    }
}
