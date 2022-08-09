package com.project.accesssystememulator.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author ilyin
 * @since 04.08.2022
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;

    public String name;

    public Department(String name) {
        this.name = name;
    }

}
