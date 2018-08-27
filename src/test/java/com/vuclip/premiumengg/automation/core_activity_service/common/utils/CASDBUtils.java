package com.vuclip.premiumengg.automation.core_activity_service.common.utils;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.DBUtils;

public class CASDBUtils {

	private static Logger logger = Log4J.getLogger("CASDBUtils");

	public static void dbCleanUp(int productId, int partnerId) {
		logger.info("Delete product and partner related config from database");
		DBUtils.cleanTable("product_partner_country_mapping", "product_id=" + productId + "partner_id=" + partnerId);
		DBUtils.cleanTable("product_info", "product_id=" + productId);
		DBUtils.cleanTable("partner_info", "partner_id=" + partnerId);

	}

}
