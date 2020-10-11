package com.training.salon.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "master")
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "master_id")
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @Column(name = "time_end", nullable = false)
    private LocalTime timeEnd;

    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @OneToMany( mappedBy = "master",  cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Comment> comments;

}
