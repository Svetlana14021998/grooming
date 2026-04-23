package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "masters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ru_full_name",nullable = false,unique = true)
    private String ruFullName;

    @Column(name = "en_full_name",nullable = false,unique = true)
    private String enFullName;

    @Column(name = "ru_experience",nullable = false)
    private String ruExperience;

    @Column(name = "en_experience",nullable = false)
    private String enExperience;

    @Column(name = "ru_specialisation",nullable = false)
    private String ruSpecialisation;
    @Column(name = "en_specialisation",nullable = false)
    private String enSpecialisation;
    @Column(name = "photo_name",nullable = false)
    private String photoName;

}
