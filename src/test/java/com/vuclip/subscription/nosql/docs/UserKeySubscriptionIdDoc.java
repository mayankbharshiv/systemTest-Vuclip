/**
 * 
 */
package com.vuclip.subscription.nosql.docs;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserKeySubscriptionIdDoc implements Serializable {

	private static final long serialVersionUID = -6416514525035588895L;
	public Long subscriptionId;
}
