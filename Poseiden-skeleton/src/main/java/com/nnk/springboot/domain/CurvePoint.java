package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    Integer id;
    @Column(name = "CurveId")
    Integer curveId;
    @Column(name = "asOfDate")
    Timestamp asOfDate;
    @Column(name = "term")
    Double term;
    @Column(name = "value")
    Double value;
    @Column(name = "creationDate")
    Timestamp creationDate;
}
