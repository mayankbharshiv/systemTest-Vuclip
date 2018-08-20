package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "successful", "responseCode", "message", "country" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryResponseVO {

	private CountryVO country;
	private String message;
	private boolean isSuccessful;
	private int responseCode;
	

}
