//package com.cremedia.cremedia.models.entity;
//
//import com.cremedia.cremedia.enums.RoleEnum;
//import com.cremedia.cremedia.enums.StatusEnum;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name="statuses")
//@Data
//@AllArgsConstructor
//@Builder
//public class Status {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    public Status() {
//        this.id = 1;
//        this.type = StatusEnum.PUBLIC;
//    }
//
//    @Enumerated(EnumType.STRING)
//    private StatusEnum type;
//}
