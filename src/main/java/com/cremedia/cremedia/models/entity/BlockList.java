package com.cremedia.cremedia.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "block_list")
public class BlockList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "blocker_id", referencedColumnName = "id")
    private User blocker;

    @ManyToOne
    @JoinColumn(name = "blocked_id", referencedColumnName = "id")
    private User blocked;

    @Column(name = "block_date")
    private Date blockDate;

}
