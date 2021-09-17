package com.example.exchangeapp.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ohcl")
@Data
public class BitMexCandlestickEntity extends BaseEntity {

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "open")
    private Double open;

    @Column(name = "high")
    private Double high;

    @Column(name = "low")
    private Double low;

    @Column(name = "close")
    private Double close;

    @Column(name = "trades")
    private Double trades;

    @Column(name = "volume")
    private Double volume;

    @Column(name = "vwap")
    private Double vwap;

    @Column(name = "lastSize")
    private Double lastSize;

    @Column(name = "turnover")
    private Double turnover;

    @Column(name = "homeNotional")
    private Double homeNotional;

    @Column(name = "foreignNotional")
    private Double foreignNotional;

}
