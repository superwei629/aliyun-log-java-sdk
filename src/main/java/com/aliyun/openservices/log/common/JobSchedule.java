package com.aliyun.openservices.log.common;


import com.alibaba.fastjson.annotation.JSONField;
import com.aliyun.openservices.log.util.Args;
import com.aliyun.openservices.log.util.JsonUtils;
import net.sf.json.JSONObject;

import java.io.Serializable;


/**
 * When and how often to repeat the job
 */
public class JobSchedule implements Serializable {

    @JSONField
    private JobScheduleType type;

    /**
     * Interval in duration format e,g "60s", "1h". Required for {@code JobScheduleType.FIXED_RATE} only.
     */
    @JSONField
    private String interval;

    /**
     * Cron expression for CRON type.
     */
    @JSONField
    private String cronExpression;

    public JobScheduleType getType() {
        return type;
    }

    public void setType(JobScheduleType type) {
        this.type = type;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        Args.checkDuration(interval);
        this.interval = interval;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public void deserialize(JSONObject value) {
        type = JobScheduleType.fromString(value.getString("type"));
        interval = JsonUtils.readOptionalString(value, "interval");
        cronExpression = JsonUtils.readOptionalString(value, "cronExpression");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobSchedule that = (JobSchedule) o;

        if (getType() != that.getType()) return false;
        if (getInterval() != null ? !getInterval().equals(that.getInterval()) : that.getInterval() != null)
            return false;
        return cronExpression != null ? cronExpression.equals(that.cronExpression) : that.cronExpression == null;
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getInterval() != null ? getInterval().hashCode() : 0);
        result = 31 * result + (cronExpression != null ? cronExpression.hashCode() : 0);
        return result;
    }
}