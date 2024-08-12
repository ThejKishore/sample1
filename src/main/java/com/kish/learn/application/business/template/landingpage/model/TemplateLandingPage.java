package com.kish.learn.application.business.template.landingpage.model;

import com.kish.learn.application.business.template.model.TemplateType;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.Diffable;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;


@Builder(toBuilder = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TemplateLandingPage implements Diffable<TemplateLandingPage> {
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

        String sponsorCode;

        String recipient;

        @Type(ListArrayType.class)
        @Column(
                name = "ntAccnts",
                columnDefinition = "bigint[]"
        )
        List<Long> ntAccntSk;

        @Override
        public DiffResult<TemplateLandingPage> diff(TemplateLandingPage templateLandingPage) {
                return new DiffBuilder<TemplateLandingPage>(this, templateLandingPage, ToStringStyle.JSON_STYLE)
                        .append("templateName", this.templateName, templateLandingPage.templateName)
                        .append("templateType", this.templateType, templateLandingPage.templateType)
                        .append("recipient", this.recipient, templateLandingPage.recipient)
                        .append("accounts", this.ntAccntSk, templateLandingPage.ntAccntSk)
                        .build();
        }
}
