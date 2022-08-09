package com.project.accesssystememulator.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

/**
 * @author ilyin
 * @since 04.08.2022
 */

@Getter
@Setter(value = PRIVATE)
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID CARD = randomUUID();

    @Enumerated(STRING)
    Role type;
}
