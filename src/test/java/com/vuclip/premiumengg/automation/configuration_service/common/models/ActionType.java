package com.vuclip.premiumengg.automation.configuration_service.common.models;
import java.util.HashMap;
import java.util.Map;

public enum ActionType {
	CONSENT(1, false), CONSENT_WITH_CHARGING(2, true), REGISTRATION(3, false), CHARGING(4, true), DEACTIVATE_CONSENT(5,
			false), PROCESS_DEACTIVATE(6, false), PROCESS_BLACKLIST(7, false);

	private Integer actionId;
	private boolean involvesCharging;

	private static Map<Integer, ActionType> actionTypeEnumMap = new HashMap<>();
	private static Map<String, ActionType> actionTypeByNameMap = new HashMap<>();

	private ActionType(Integer actionId, boolean involvesCharging) {
		this.actionId = actionId;
		this.involvesCharging = involvesCharging;
	}

	static {
		for (ActionType actionTypeEnum : ActionType.values()) {
			actionTypeEnumMap.put(actionTypeEnum.actionId, actionTypeEnum);
			actionTypeByNameMap.put(actionTypeEnum.toString(), actionTypeEnum);
		}
	}

	public static ActionType getAction(int actionId) {
		return actionTypeEnumMap.get(actionId);
	}

	public static ActionType getAction(String name) {
		return actionTypeByNameMap.get(name);
	}

	public int getActionId() {
		return actionId;
	}

	public boolean isChargingInvolved() {
		return involvesCharging;
	}
}