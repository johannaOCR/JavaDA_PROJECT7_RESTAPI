package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Min(value = 1, message = "CurveId must be positive")
    @NotNull(message = "must not be null")
    private Integer curveId;
    private Timestamp asOfDate;

    @DecimalMin(value = "0.01", inclusive = false, message = "Term must be positive")
    private Double term;

    @DecimalMin(value = "0.01", inclusive = false, message = "Term must be positive")
    private Double value;
    private Timestamp creationDate;


    public CurvePoint() {
    }

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CurvePoint that = (CurvePoint) o;
        return id != null && Objects.equals(id, that.id);
    }
}
