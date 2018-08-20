package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkResponseVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkVO;
import org.apache.log4j.Logger;

public class AdNetworkUtil {
    private static Logger logger = Log4J.getLogger("AdNetworkUtil");

    public static AdNetworkRequestVO createMockRequestVO(Object... params) {
        logger.info("Create Mock Request for Adnetwork");
        return AdNetworkRequestVO.builder().name((String) params[0]).retryLimit((Integer) params[1])
                .requestParamName((String) params[2]).notificationUrl((String) params[3]).httpMethod((String) params[4])
                .notifyOnActivity((String) params[5]).status((String) params[6])
                .churnNotificationUrl((String) params[7]).sourceIdentifier((String) params[8]).build();
    }

    public static AdNetworkVO getMockAdNetworkVO(AdNetworkRequestVO request, String operationType) {
        logger.info("Get Mock Request for Adnetwork");
        return AdNetworkVO.builder().adNetworkId(1).name(request.getName()).retryLimit(request.getRetryLimit())
                .requestParamName(request.getRequestParamName()).notificationUrl(request.getNotificationUrl())
                .httpMethod(request.getHttpMethod()).notifyOnActivity(request.getNotifyOnActivity())
                .status(request.getStatus()).churnNotificationUrl(request.getChurnNotificationUrl())
                .sourceIdentifier(request.getSourceIdentifier()).operationType(operationType).build();
    }

    public static AdNetworkResponseVO getMockAdNetworkResponseVO(AdNetworkRequestVO request, String operationType,
                                                                 boolean isSuccessful, int responseCode, String message) {
        logger.info("Get Mock Request for Adnetwork");
        return AdNetworkResponseVO.builder()
                .adNetwork(new AdNetworkVO(0, request.getName(), request.getRetryLimit(), request.getRequestParamName(),
                        request.getNotificationUrl(), request.getHttpMethod(), request.getNotifyOnActivity(),
                        request.getStatus(), request.getChurnNotificationUrl(), request.getSourceIdentifier(),
                        operationType))
                .successful(isSuccessful).responseCode(responseCode).message(message).build();
    }

}
