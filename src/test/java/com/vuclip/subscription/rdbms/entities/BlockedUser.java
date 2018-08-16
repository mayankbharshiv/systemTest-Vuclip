
package com.vuclip.subscription.rdbms.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "userId",
    "msisdn",
    "productId",
    "partnerId",
    "country",
    "blockType",
    "startDate",
    "endDate",
    "createDate",
    "modifyDate"
})

@Getter
@Setter
@ToString
public class BlockedUser implements Serializable {
	private static final long serialVersionUID = -6416514525035588895L;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("msisdn")
    private String msisdn;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("country")
    private String country;
    @JsonProperty("blockType")
    private String blockType;
    @JsonProperty("startDate")
    private Long startDate;
    @JsonProperty("endDate")
    private Long endDate;
    @JsonProperty("createDate")
    private Date createDate;
    @JsonProperty("modifyDate")
    private Date modifyDate;
  
}


