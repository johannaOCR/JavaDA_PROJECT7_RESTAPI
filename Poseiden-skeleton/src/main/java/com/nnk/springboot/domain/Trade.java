package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TradeId", nullable = false)
    Integer tradeId;
    @Column(name = "account")
    String account;
    @Column(name = "type")
    String type;
    @Column(name = "buyQuantity")
    Double buyQuantity;
    @Column(name = "sellQuantity")
    Double sellQuantity;
    @Column(name = "buyPrice")
    Double buyPrice;
    @Column(name = "sellPrice")
    Double sellPrice;
    @Column(name = "benchmark")
    String benchmark;
    @Column(name = "tradeDate")
    Timestamp tradeDate;
    @Column(name = "security")
    String security;
    @Column(name = "status")
    String status;
    @Column(name = "trader")
    String trader;
    @Column(name = "book")
    String book;
    @Column(name = "creationName")
    String creationName;
    @Column(name = "creationDate")
    Timestamp creationDate;
    @Column(name = "revisionName")
    String revisionName;
    @Column(name = "revisionDate")
    Timestamp revisionDate;
    @Column(name = "dealName")
    String dealName;
    @Column(name = "dealType")
    String dealType;
    @Column(name = "sourceListId")
    String sourceListId;
    @Column(name = "side")
    String side;

}
