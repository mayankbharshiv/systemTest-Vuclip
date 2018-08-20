package com.vuclip.premiumengg.automation.configuration_service.common.models;

import java.util.HashMap;
import java.util.Map;

public enum ActivityType {

	ACTIVATION(1), RENEWAL(2), DEACTIVATION(3), WINBACK(4), ACTIVATION_RETRY(5), FREETRIAL_RENEWAL(
			6), DEACTIVATION_RETRY(7), SYSTEM_CHURN(8), UNBLOCK(9), BLACKLIST(10);

	private int activityId;

	private ActivityType(int activityId) {
		this.activityId = activityId;
	}

	public int getActivityId() {
		return activityId;
	}

	private static Map<Integer, ActivityType> activityTypeEnumMap = new HashMap<>();
	private static Map<String, ActivityType> activityTypeByNameap = new HashMap<>();

	static {
		for (ActivityType activityTypeEnum : ActivityType.values()) {
			activityTypeEnumMap.put(activityTypeEnum.activityId, activityTypeEnum);
			activityTypeByNameap.put(activityTypeEnum.toString(), activityTypeEnum);
		}
	}

	public static ActivityType getActivity(int activityId) {
		return activityTypeEnumMap.get(activityId);
	}

	public static ActivityType getActivity(String name) {
		return activityTypeByNameap.get(name);
	}
}
