package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CountryRequestVO;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
import org.apache.log4j.Logger;

public class CSUtils {
    public static String uBSMockURL = null;
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


    public static int boolToInt(boolean b) {
        return Boolean.compare(b, false);
    }
}
