package com.study.rest.body_composition_api_demo;

import jakarta.persistence.*;

// @Entityでbody_compositionsテーブルにmapping
@Entity
@Table(name = "body_compositions")
public class BodyComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String userId;  
    private Double weight;   // 体重(kg)
    private Double bodyFat;  // 体脂率(%)

    public BodyComposition() {}

    // 登録時にControllerから呼ばれる
    public BodyComposition(String userId, Double weight, Double bodyFat) {
        this.userId = userId;
        this.weight = weight;
        this.bodyFat = bodyFat;
    }

  
    public Long getId() { return id; }
    public String getUserId() { return userId; }
    public Double getWeight() { return weight; }
    public Double getBodyFat() { return bodyFat; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setWeight(Double weight) { this.weight = weight; }
    public void setBodyFat(Double bodyFat) { this.bodyFat = bodyFat; }
}