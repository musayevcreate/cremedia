package com.cremedia.cremedia.models.entity;

import com.cremedia.cremedia.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Role() {
        this.id = 2;
    }

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

}
