
package com.ff.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "shipmentRateRange",
    "rateTableName",
    "rateType",
    "effectiveDateDisplay",
    "endDateDisplay",
    "createdDateDisplay",
    "maxShipmentRate"
})
public class Mainmodel {

    @JsonProperty("shipmentRateRange")
    private ShipmentRateRange shipmentRateRange;
    @JsonProperty("rateTableName")
    private String rateTableName;
    @JsonProperty("rateType")
    private String rateType;
    @JsonProperty("effectiveDateDisplay")
    private String effectiveDateDisplay;
    @JsonProperty("endDateDisplay")
    private String endDateDisplay;
    @JsonProperty("createdDateDisplay")
    private String createdDateDisplay;
    @JsonProperty("maxShipmentRate")
    private String maxShipmentRate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("shipmentRateRange")
    public ShipmentRateRange getShipmentRateRange() {
        return shipmentRateRange;
    }

    @JsonProperty("shipmentRateRange")
    public void setShipmentRateRange(ShipmentRateRange shipmentRateRange) {
        this.shipmentRateRange = shipmentRateRange;
    }

    @JsonProperty("rateTableName")
    public String getRateTableName() {
        return rateTableName;
    }

    @JsonProperty("rateTableName")
    public void setRateTableName(String rateTableName) {
        this.rateTableName = rateTableName;
    }

    @JsonProperty("rateType")
    public String getRateType() {
        return rateType;
    }

    @JsonProperty("rateType")
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    @JsonProperty("effectiveDateDisplay")
    public String getEffectiveDateDisplay() {
        return effectiveDateDisplay;
    }

    @JsonProperty("effectiveDateDisplay")
    public void setEffectiveDateDisplay(String effectiveDateDisplay) {
        this.effectiveDateDisplay = effectiveDateDisplay;
    }

    @JsonProperty("endDateDisplay")
    public String getEndDateDisplay() {
        return endDateDisplay;
    }

    @JsonProperty("endDateDisplay")
    public void setEndDateDisplay(String endDateDisplay) {
        this.endDateDisplay = endDateDisplay;
    }

    @JsonProperty("createdDateDisplay")
    public String getCreatedDateDisplay() {
        return createdDateDisplay;
    }

    @JsonProperty("createdDateDisplay")
    public void setCreatedDateDisplay(String createdDateDisplay) {
        this.createdDateDisplay = createdDateDisplay;
    }

    @JsonProperty("maxShipmentRate")
    public String getMaxShipmentRate() {
        return maxShipmentRate;
    }

    @JsonProperty("maxShipmentRate")
    public void setMaxShipmentRate(String maxShipmentRate) {
        this.maxShipmentRate = maxShipmentRate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
