package com.orioninc.codegen.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Subscription
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-06T21:58:06.610273300+03:00[Europe/Moscow]")
public class Subscription   {
  @JsonProperty("user")
  private SubscriptionUser user;

  /**
   * Gets or Sets subscriptionType
   */
  public enum SubscriptionTypeEnum {
    LITE("LITE"),
    
    PREMIUM("PREMIUM"),
    
    CLASSIC("CLASSIC");

    private String value;

    SubscriptionTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SubscriptionTypeEnum fromValue(String value) {
      for (SubscriptionTypeEnum b : SubscriptionTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("subscriptionType")
  private SubscriptionTypeEnum subscriptionType;

  @JsonProperty("weekNumber")
  private Integer weekNumber;

  public Subscription user(SubscriptionUser user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @ApiModelProperty(value = "")

  @Valid

  public SubscriptionUser getUser() {
    return user;
  }

  public void setUser(SubscriptionUser user) {
    this.user = user;
  }

  public Subscription subscriptionType(SubscriptionTypeEnum subscriptionType) {
    this.subscriptionType = subscriptionType;
    return this;
  }

  /**
   * Get subscriptionType
   * @return subscriptionType
  */
  @ApiModelProperty(value = "")


  public SubscriptionTypeEnum getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(SubscriptionTypeEnum subscriptionType) {
    this.subscriptionType = subscriptionType;
  }

  public Subscription weekNumber(Integer weekNumber) {
    this.weekNumber = weekNumber;
    return this;
  }

  /**
   * Get weekNumber
   * @return weekNumber
  */
  @ApiModelProperty(value = "")


  public Integer getWeekNumber() {
    return weekNumber;
  }

  public void setWeekNumber(Integer weekNumber) {
    this.weekNumber = weekNumber;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscription subscription = (Subscription) o;
    return Objects.equals(this.user, subscription.user) &&
        Objects.equals(this.subscriptionType, subscription.subscriptionType) &&
        Objects.equals(this.weekNumber, subscription.weekNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, subscriptionType, weekNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Subscription {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
    sb.append("    weekNumber: ").append(toIndentedString(weekNumber)).append("\n");
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

