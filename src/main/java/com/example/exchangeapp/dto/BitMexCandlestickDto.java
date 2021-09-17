package com.example.exchangeapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BitMexCandlestickDto {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("timestamp")
    private Date timestamp;
    @JsonProperty("open")
    private Double open;
    @JsonProperty("high")
    private Double high;
    @JsonProperty("low")
    private Double low;
    @JsonProperty("close")
    private Double close;
    @JsonProperty("trades")
    private Double trades;
    @JsonProperty("volume")
    private Double volume;
    @JsonProperty("vwap")
    private Double vwap;
    @JsonProperty("lastSize")
    private Double lastSize;
    @JsonProperty("turnover")
    private Double turnover;
    @JsonProperty("homeNotional")
    private Double homeNotional;
    @JsonProperty("foreignNotional")
    private Double foreignNotional;
}
