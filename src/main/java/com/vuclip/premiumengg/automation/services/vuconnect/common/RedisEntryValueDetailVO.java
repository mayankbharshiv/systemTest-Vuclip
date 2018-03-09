package com.vuclip.premiumengg.automation.services.vuconnect.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.io.StringWriter;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "requestId",
        "chargingDate",
        "nextBillingDate",
        "carrierApiResponseStatus"
})
public class RedisEntryValueDetailVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String requestId;

    @JsonProperty("chDate")
    private String chargingDate;

    @JsonProperty("nbd")
    private String nextBillingDate;

    @JsonProperty("cApiStatus")
    private BillingRequestStatusEnum carrierApiResponseStatus;


    /**
     * Marshal string.
     *
     * @return the string
     */
    public String marshal() {
        StringWriter stringWriter = new StringWriter();
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            jsonObjectMapper.writeValue(stringWriter, this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return stringWriter.toString();
    }

    public String getRequestId() {
        return requestId;
    }


    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public String getChargingDate() {
        return chargingDate;
    }


    public void setChargingDate(String chargingDate) {
        this.chargingDate = chargingDate;
    }


    public String getNextBillingDate() {
        return nextBillingDate;
    }


    public void setNextBillingDate(String nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }


    public BillingRequestStatusEnum getCarrierApiResponseStatus() {
        return carrierApiResponseStatus;
    }


    public void setCarrierApiResponseStatus(BillingRequestStatusEnum carrierApiResponseStatus) {
        this.carrierApiResponseStatus = carrierApiResponseStatus;
    }


    public static enum BillingRequestStatusEnum {


        IN_PROGRESS("In_Progress"),
        SUCCESS("Success"),
        CARRIER_TIMEOUT("Carrier_Timeout");

        private String value;

        BillingRequestStatusEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }


    }

}
