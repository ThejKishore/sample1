package com.kish.learn.application.business.template.model;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TemplateWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long templateId;

    String templateName;

    @Enumerated(EnumType.STRING)
    TemplateType templateType;

    LocalDate createdDate;

    String createBy;

    LocalDate updatedDate;

    String updateBy;

    @Type(JsonBinaryType.class)
    @Column(name = "template", columnDefinition = "jsonb")
    private Template template;

}