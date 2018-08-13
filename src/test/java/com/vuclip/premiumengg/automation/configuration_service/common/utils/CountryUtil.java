package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CountryRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CountryResponseVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CountryVO;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

public class CountryUtil {
	public static int productId;
	private static Logger logger = Log4J.getLogger("CSUtils");

	/**
	 * @param JsonFileName
	 * @param type
	 * @return
	 */
	public static <T> T loadJson(String JsonFileName, Class<T> type) {
		return ObjectMapperUtils
				.readValue("src/test/resources/configurations/configuration-service/request/" + JsonFileName, type);
	}

	public static CountryRequestVO generateSavePartner() {
		CountryRequestVO savePartner = loadJson("savePartner.json", CountryRequestVO.class);
		logger.info("save Partner values generated: " + savePartner.toString());
		return savePartner;
	}

	public static CountryRequestVO createMockRequestVO(Object... params) {

		return CountryRequestVO.builder().countryName((String) params[0]).countryCode((String) params[1])
				.timezone((String) params[2]).currency((String) params[3]).build();
	}

	public static CountryResponseVO getMockCountryVO(CountryRequestVO request, String operationType, boolean isSuccessful, int responseCode, String message) {
		return CountryResponseVO.builder().country(new CountryVO(0, request.getCountryName(), request.getCountryCode(),
				request.getTimezone(), request.getCurrency(), operationType)).isSuccessful(isSuccessful).responseCode(responseCode).message(message).build();
	}

	public static int boolToInt(boolean b) {
		return Boolean.compare(b, false);
	}
}
