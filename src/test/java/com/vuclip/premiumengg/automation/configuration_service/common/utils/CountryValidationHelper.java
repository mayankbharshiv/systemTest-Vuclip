package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import java.util.Map;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CountryResponseVO;
import com.vuclip.premiumengg.automation.utils.AppAssert;

public class CountryValidationHelper {
	private static Logger logger = Log4J.getLogger("CSValidationHelper");

	public static void validateTableRecord(Map<String, Object> map, CountryResponseVO countryResponseVO) {
		logger.info("Verify Country Table");
		AppAssert.assertEqual(map.get("country_name").toString(), countryResponseVO.getCountry().getCountryName());
		AppAssert.assertEqual(map.get("currency").toString(), countryResponseVO.getCountry().getCurrency());
		AppAssert.assertEqual(map.get("country_code").toString(), countryResponseVO.getCountry().getCountryCode());
		AppAssert.assertEqual(map.get("timezone").toString(), countryResponseVO.getCountry().getTimezone());

	}

	public static void validateResponse(CountryResponseVO actual, CountryResponseVO expected) {
		if(actual.getCountry().getOperationType()!=null) {
			AppAssert.assertEqual(actual.getMessage(), expected.getMessage(), "Verification for message");
			AppAssert.assertEqual(actual.getResponseCode(), expected.getResponseCode(), "verification for respose code");
			AppAssert.assertEqual(actual.isSuccessful(), expected.isSuccessful());
			AppAssert.assertEqual(actual.getCountry().getCountryName(), expected.getCountry().getCountryName());
			AppAssert.assertEqual(actual.getCountry().getCurrency(), expected.getCountry().getCurrency());
			AppAssert.assertEqual(actual.getCountry().getOperationType(), expected.getCountry().getOperationType());
			AppAssert.assertEqual(actual.getCountry().getTimezone(), expected.getCountry().getTimezone());
			AppAssert.assertEqual(actual.getCountry().getCountryCode(), expected.getCountry().getCountryCode());
		}
		
		else
		{
			AppAssert.assertEqual(actual.getMessage(), expected.getMessage(), "Verification for message");
			AppAssert.assertEqual(actual.getResponseCode(), expected.getResponseCode(), "verification for respose code");
			AppAssert.assertEqual(actual.isSuccessful(), expected.isSuccessful());
			AppAssert.assertEqual(actual.getCountry().getCountryName(), expected.getCountry().getCountryName());
			AppAssert.assertEqual(actual.getCountry().getCurrency(), expected.getCountry().getCurrency());
			AppAssert.assertEqual(actual.getCountry().getTimezone(), expected.getCountry().getTimezone());
			AppAssert.assertEqual(actual.getCountry().getCountryCode(), expected.getCountry().getCountryCode());
		}
		
	}
}
