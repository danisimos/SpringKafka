package com.orioninc.codegen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;

/**
 * ProcessedIntervalSubscriptions
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-06T21:58:06.174274800+03:00[Europe/Moscow]")
public class ProcessedIntervalSubscriptions   {
  @JsonProperty("interval")
  private ProcessedIntervalSubscriptionsInterval interval;

  @JsonProperty("user")
  private User user;

  @JsonProperty("averageWeekCount")
  private Integer averageWeekCount;

  public ProcessedIntervalSubscriptions interval(ProcessedIntervalSubscriptionsInterval interval) {
    this.interval = interval;
    return this;
  }

  /**
   * Get interval
   * @return interval
  */
  @ApiModelProperty(value = "")

  @Valid

  public ProcessedIntervalSubscriptionsInterval getInterval() {
    return interval;
  }

  public void setInterval(ProcessedIntervalSubscriptionsInterval interval) {
    this.interval = interval;
  }

  public ProcessedIntervalSubscriptions user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @ApiModelProperty(value = "")

  @Valid

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public ProcessedIntervalSubscriptions averageWeekCount(Integer averageWeekCount) {
    this.averageWeekCount = averageWeekCount;
    return this;
  }

  /**
   * Get averageWeekCount
   * @return averageWeekCount
  */
  @ApiModelProperty(value = "")


  public Integer getAverageWeekCount() {
    return averageWeekCount;
  }

  public void setAverageWeekCount(Integer averageWeekCount) {
    this.averageWeekCount = averageWeekCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessedIntervalSubscriptions processedIntervalSubscriptions = (ProcessedIntervalSubscriptions) o;
    return Objects.equals(this.interval, processedIntervalSubscriptions.interval) &&
        Objects.equals(this.user, processedIntervalSubscriptions.user) &&
        Objects.equals(this.averageWeekCount, processedIntervalSubscriptions.averageWeekCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(interval, user, averageWeekCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessedIntervalSubscriptions {\n");
    
    sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    averageWeekCount: ").append(toIndentedString(averageWeekCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

