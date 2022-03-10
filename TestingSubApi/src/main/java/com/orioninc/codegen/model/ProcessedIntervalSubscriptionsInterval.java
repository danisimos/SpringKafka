package com.orioninc.codegen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ProcessedIntervalSubscriptionsInterval
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-06T21:58:06.174274800+03:00[Europe/Moscow]")
public class ProcessedIntervalSubscriptionsInterval   {
  @JsonProperty("timestampFrom")
  private Long timestampFrom = null;

  @JsonProperty("timestampTo")
  private Long timestampTo = null;

  public ProcessedIntervalSubscriptionsInterval timestampFrom(Long timestampFrom) {
    this.timestampFrom = timestampFrom;
    return this;
  }

  /**
   * Get timestampFrom
   * @return timestampFrom
  */
  @ApiModelProperty(value = "")


  public Long getTimestampFrom() {
    return timestampFrom;
  }

  public void setTimestampFrom(Long timestampFrom) {
    this.timestampFrom = timestampFrom;
  }

  public ProcessedIntervalSubscriptionsInterval timestampTo(Long timestampTo) {
    this.timestampTo = timestampTo;
    return this;
  }

  /**
   * Get timestampTo
   * @return timestampTo
  */
  @ApiModelProperty(value = "")


  public Long getTimestampTo() {
    return timestampTo;
  }

  public void setTimestampTo(Long timestampTo) {
    this.timestampTo = timestampTo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessedIntervalSubscriptionsInterval processedIntervalSubscriptionsInterval = (ProcessedIntervalSubscriptionsInterval) o;
    return Objects.equals(this.timestampFrom, processedIntervalSubscriptionsInterval.timestampFrom) &&
        Objects.equals(this.timestampTo, processedIntervalSubscriptionsInterval.timestampTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestampFrom, timestampTo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessedIntervalSubscriptionsInterval {\n");
    
    sb.append("    timestampFrom: ").append(toIndentedString(timestampFrom)).append("\n");
    sb.append("    timestampTo: ").append(toIndentedString(timestampTo)).append("\n");
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

