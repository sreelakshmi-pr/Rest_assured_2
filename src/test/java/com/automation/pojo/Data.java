package com.automation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Data {

    public int year;

    public double price;

    @JsonProperty("CPU model")
    public String cpuModel;

    @JsonProperty("Hard disk size")
    public String hardDiskSize;
}
