
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "successful",
    "message",
    "responseCode",
    "userStatus"
})

public class StateTransitionResponse {

    @JsonProperty("successful")
    public Boolean successful;
    @JsonProperty("message")
    public String message;
    @JsonProperty("responseCode")
    public String responseCode;
    @JsonProperty("userStatus")
    public UserStatus userStatus;

    @JsonProperty("successful")
    public Boolean getSuccessful() {
        return successful;
    }

    @JsonProperty("successful")
    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("responseCode")
    public String getResponseCode() {
        return responseCode;
    }

    @JsonProperty("responseCode")
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @JsonProperty("userStatus")
    public UserStatus getUserStatus() {
        return userStatus;
    }

    @JsonProperty("userStatus")
    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

	@Override
	public String toString() {
		return "StateTransitionResponse [successful=" + successful + ", message=" + message + ", responseCode="
				+ responseCode + ", userStatus=" + userStatus + ", getSuccessful()=" + getSuccessful()
				+ ", getMessage()=" + getMessage() + ", getResponseCode()=" + getResponseCode() + ", getUserStatus()="
				+ getUserStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


	
    

}
