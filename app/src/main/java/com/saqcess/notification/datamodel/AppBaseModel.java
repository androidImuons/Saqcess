package com.saqcess.notification.datamodel;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class AppBaseModel {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_MMMDDYYYY = "MMM dd, yyyy";
    public static final String DATE_DDMMYYYY = "dd/MM/yyyy";
    public static final String hh_mm_a = "hh:mm a";
    public static final String DATETIME_MMMDDYYYY_hh_mm_ss_a = "dd-MM-yyyy hh:mm:ss a";

    public String getFormatedDateString(String format, long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        return simpleDateFormat.format(calendar.getTime());
    }

    public String getValidTimeText(long value) {
        long hours = TimeUnit.SECONDS.toHours(value);
        long minutes = TimeUnit.SECONDS.toMinutes(value - TimeUnit.HOURS.toSeconds(hours));
        long second = value - TimeUnit.HOURS.toSeconds(hours) - TimeUnit.MINUTES.toSeconds(minutes);
        StringBuilder builder = new StringBuilder();
        if (hours < 0)
            builder.append("-");
        builder.append(String.format(Locale.US, "%02dh", Math.abs(hours)));

        if (minutes < 0) {
            builder.append(" -");
        } else {
            builder.append(" ");
        }
        builder.append(String.format(Locale.US, "%02dm", Math.abs(minutes)));

        if (second < 0) {
            builder.append(" -");
        } else {
            builder.append(" ");
        }

        builder.append(String.format(Locale.US, "%02ds", Math.abs(second)));

        return builder.toString();

    }


    public boolean isValidString(String data) {
        return data != null && !data.trim().isEmpty();
    }

    public String getValidString(String data) {
        return data == null ? "" : data;
    }

    public boolean isValidList(List list) {
        return list != null && list.size() > 0;
    }

    public boolean isValidObject(Object object) {
        return object != null;
    }

    public String getValidDecimalFormat(String value) {
        if (!isValidString(value)) {
            return "0.00";
        }
        double netValue = Double.parseDouble(value);
        return getValidDecimalFormat(netValue);
    }

    public String getValidDecimalFormat(double value) {
        return String.format(Locale.ENGLISH, "%.2f", value);
    }
}
