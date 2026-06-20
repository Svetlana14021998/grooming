package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.ArticleType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "useful_articles")
@NoArgsConstructor
@AllArgsConstructor
public class UsefulArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ArticleType type;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @ManyToOne(targetEntity = Master.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private Master master;

    @CreationTimestamp
    @Column(name = "creating_date")
    private LocalDate creatingDate;

    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "views_count")
    private Long viewsCount;

    @Column(name = "reading_time_minutes")
    private Integer readingTimeMinutes;
}
