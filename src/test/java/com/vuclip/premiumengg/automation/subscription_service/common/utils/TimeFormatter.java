package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeFormatter {

	public static SimpleDateFormat javaDateFormatter;
	public static SimpleDateFormat javaDatetimeFormatter;
	public static SimpleDateFormat dateTimeOnlyFormatter;

	static {
		javaDateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		javaDatetimeFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		dateTimeOnlyFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

	}

}