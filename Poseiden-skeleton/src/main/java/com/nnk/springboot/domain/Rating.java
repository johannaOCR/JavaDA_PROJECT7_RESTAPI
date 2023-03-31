package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    Integer id;
    @Column(name = "moodysRating")
    String moodysRating;
    @Column(name = "sandPRating")
    String sandPRating;
    @Column(name = "fitchRating")
    String fitchRating;
    @Column(name = "orderNumber")
    Integer orderNumber;
}
