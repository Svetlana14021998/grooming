package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "masters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@NamedEntityGraph(
    name = "master_with_services",
    attributeNodes = @NamedAttributeNode("services")
)
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

    @ManyToMany
    @JoinTable(name = "masters_services",
        joinColumns = @JoinColumn(name = "master_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<GroomingService> services;

    @Column(name = "photo_name",nullable = false)
    private String photoName;

}
