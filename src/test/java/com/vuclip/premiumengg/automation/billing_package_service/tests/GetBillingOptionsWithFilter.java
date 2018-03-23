package com.vuclip.premiumengg.automation.billing_package_service.tests;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingPackage;
import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingResponse;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSHelper;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSValidationHelper;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kohitij_Das on 21/03/18.
 */
public class GetBillingOptionsWithFilter {

    private BPSHelper bpsHelper;
    private BPSValidationHelper validationHelper;

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        bpsHelper = new BPSHelper();
        validationHelper = new BPSValidationHelper();
    }

    @Test
    public void verify_get_billing_options_with_productId() throws Exception {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("productId", BillingPackage.PACKAGE1.getProductId());
        validationHelper.validate_billing_packages(bpsHelper
                .getBillingOptionWithFilters(params), BillingPackage.PACKAGE1, "billingPackages"
        );
    }

    @Test
    public void verify_get_billing_options_without_productId() throws Exception {
        final Map<String, Object> params = new HashMap<String, Object>();
        final Response response = bpsHelper.getBillingOptionWithFilters(params);
        response.then().assertThat().statusCode(400);
        validationHelper.validate_billing_response(response,
                BillingResponse.BADREQUEST
        );
    }

    @DataProvider(name = "validFilterOptions")
    public Object[][] validFilterOptions() {
        return new Object[][]{
                {"partnerId", BillingPackage.PACKAGE1.getPartnerId()},
                {"isCarrier", BillingPackage.PACKAGE1.isCarrier()},
                {"billingCode", BillingPackage.PACKAGE1.getBillingCode()},
                {"price", BillingPackage.PACKAGE1.getPrice()},
                {"country", BillingPackage.PACKAGE1.getCountry()},
                {"itemId", BillingPackage.PACKAGE1.getItemId()},
                {"itemTypeId", BillingPackage.PACKAGE1.getItemTypeId()},
                {"clientId", BillingPackage.PACKAGE1.getClientId()}
        };
    }

    @Test(dataProvider = "validFilterOptions")
    public void verify_get_billing_options_with_valid_filter(String filterName, Object filterValue) throws Exception {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("productId", BillingPackage.PACKAGE1.getProductId());
        params.put(filterName, filterValue);
        validationHelper.validate_billing_packages(bpsHelper
                .getBillingOptionWithFilters(params), BillingPackage.PACKAGE1, "billingPackages"
        );
    }

    @DataProvider(name = "invalidFilterOptions")
    public Object[][] invalidFilterOptions() {
        return new Object[][]{
                {"partnerId", 99},
                {"isCarrier", false},
                {"billingCode", "111222"},
                {"price", 9999},
                {"country", "dummy"},
                {"itemId", 99},
                {"itemTypeId", 99},
                {"clientId", 99}
        };
    }

    @Test(dataProvider = "invalidFilterOptions")
    public void verify_get_billing_options_with_invalid_filter(String filterName, Object filterValue) throws Exception {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("productId", BillingPackage.PACKAGE1.getProductId());
        params.put(filterName, filterValue);
        validationHelper.validate_billing_response(bpsHelper
                .getBillingOptionWithFilters(params), BillingResponse.NOTFOUND
        );
    }
}
