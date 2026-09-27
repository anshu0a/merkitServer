package com.merkit.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "time_capsules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeCapsule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 10000)
    private String description;

    private Double latitude;
    private Double longitude;
    private String location;

    private LocalDateTime openDate;

    @ManyToMany
    @JoinTable(
            name = "time_capsule_views",
            joinColumns = @JoinColumn(name = "time_capsule_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> views = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "time_capsule_likes",
            joinColumns = @JoinColumn(name = "time_capsule_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likes = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "thumbnail_id")
    private CloudinaryFile thumbnail;

    @OneToMany(
            mappedBy = "timeCapsule",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CloudinaryFile> attachments = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}