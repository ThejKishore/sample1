package com.kish.learn.application;

import com.kish.learn.application.business.template.TemplateWrapperRepository;
import com.kish.learn.application.business.template.audit.AuditEntity;
import com.kish.learn.application.business.template.audit.AuditRepo;
import com.kish.learn.application.business.template.landingpage.TemplatePageRepository;
import com.kish.learn.application.business.template.landingpage.model.TemplateLandingPage;
import com.kish.learn.application.business.template.model.*;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * The entry point of the Spring Boot application.
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@PWA(name = "Money Movement template" , shortName = "mm_template")
//@PWA(name = "Tran Template" , )
@NpmPackage(value = "@fontsource/roboto", version = "4.5.0")
@Theme(value = "my-template")
@Slf4j
public class Application implements AppShellConfigurator {

    public static final String CREATE_BY = "Test1234";
    public static final long ACCNT_NO_1 = 45343;
    public static final long ACCNT_NO_2 = 23232;
    public static final long ACCNT_NO_3 = 32232;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(TemplatePageRepository templatePageRepository,
                                               TemplateWrapperRepository templateWrapperRepository,
                                               AuditRepo auditRepo) {
        return args -> {
            createTemplateForTesting(templateWrapperRepository,auditRepo);
            createLandingPageEntry(templatePageRepository,auditRepo);
        };

    }

    private void createTemplateForTesting(TemplateWrapperRepository templateWrapperRepository,AuditRepo auditRepo) {
        //Wire out
        TemplateWrapper wireOut = TemplateWrapper.builder()
                .createBy(CREATE_BY)
                .updateBy(CREATE_BY)
                .createdDate(LocalDate.now().minusDays(3))
                .updatedDate(LocalDate.now())
                .templateName("Test_Wire_Out")
                .templateType(TemplateType.WIRE_OUT)
                .template(WireTemplate.builder()
                        .vendorName("Vendor 1")
                        .vendorType("Vendor 2")
                        .from(List.of(
                                NTAccount.builder().accntSk(ACCNT_NO_1).accntName("test accnt1").srcType(1).accntDescription("test account 1 for testing purpose ").build(),
                                NTAccount.builder().accntSk(ACCNT_NO_2).accntName("test accnt2").srcType(1).accntDescription("test account 2 for testing purpose ").build(),
                                NTAccount.builder().accntSk(ACCNT_NO_3).accntName("test accnt3").srcType(1).accntDescription("test account 3 for testing purpose ").build()
                        ))
                        .to(WireRecipient.builder()
                                .bankName("Boa Bank")
                                .recipientId(12345l)
                                .recipientName("Thej")
                                .recipientEmail("test@test.com")
                                .build())
                        .build())
                .build();
        //Transfer
        TemplateWrapper transfer = TemplateWrapper.builder()
                .createBy(CREATE_BY)
                .updateBy(CREATE_BY)
                .createdDate(LocalDate.now().minusDays(2))
                .updatedDate(LocalDate.now().minusDays(1))
                .templateName("Test_Transfer")
                .templateType(TemplateType.TRANSFER)
                .template(TransferTemplate.builder()
                        .vendorName("Vendor 1")
                        .vendorType("Vendor 2")
                        .from(List.of(
                                NTAccount.builder().accntSk(ACCNT_NO_1).accntName("test accnt1").srcType(1).accntDescription("test account 1 for testing purpose ").build()
                        ))
                        .to(List.of(
                                NTAccount.builder().accntSk(ACCNT_NO_2).accntName("test accnt2").srcType(1).accntDescription("test account 2 for testing purpose ").build()
                        ))
                        .build())
                .build();
        //ACH
        TemplateWrapper ach = TemplateWrapper.builder()
                .createBy(CREATE_BY)
                .updateBy(CREATE_BY)
                .createdDate(LocalDate.now().minusDays(3))
                .updatedDate(LocalDate.now())
                .templateName("Test_ACH_BANKING")
                .templateType(TemplateType.ACH)
                .template(AchTemplate.builder()
                        .vendorName("Vendor 1")
                        .vendorType("Vendor 2")
                        .from(List.of(
                                NTAccount.builder().accntSk(ACCNT_NO_1).accntName("test accnt1").srcType(1).accntDescription("test account 1 for testing purpose ").build(),
                                NTAccount.builder().accntSk(ACCNT_NO_2).accntName("test accnt2").srcType(1).accntDescription("test account 2 for testing purpose ").build()
                        ))
                        .to(AchRecipient.builder()
                                .bankName("Boa Bank")
                                .recipientId(12345l)
                                .recipientName("Thej")
                                .recipientEmail("test@test.com")
                                .build()
                        )
                        .build())
                .build();
        //Wire IN
        TemplateWrapper wireIn = TemplateWrapper.builder()
                .createBy(CREATE_BY)
                .updateBy(CREATE_BY)
                .createdDate(LocalDate.now().minusDays(3))
                .updatedDate(LocalDate.now().minusDays(1))
                .templateName("Test_Wire_IN")
                .templateType(TemplateType.WIRE_IN)
                .template(WireInTemplate.builder()
                        .vendorName("Vendor 1")
                        .vendorType("Vendor 2")
                        .from(WireRecipient.builder()
                                .bankName("Boa Bank")
                                .recipientId(12345l)
                                .recipientName("Thej")
                                .recipientEmail("test@test.com")
                                .build()
                        )
                        .to(List.of(
                                NTAccount.builder().accntSk(ACCNT_NO_2).accntName("test accnt2").srcType(1).accntDescription("test account 2 for testing purpose ").build(),
                                NTAccount.builder().accntSk(ACCNT_NO_3).accntName("test accnt3").srcType(1).accntDescription("test account 3 for testing purpose ").build()
                        ))
                        .build())
                .build();

        List<TemplateWrapper> templates = List.of(wireOut,wireIn,ach,transfer);
        templateWrapperRepository.persistAll(templates);
        List<AuditEntity> templateWrapperAudits = templates.stream()
                .map(wrap -> wrap.diffs(null, "template_wrapper", CREATE_BY))
                .flatMap(Collection::stream)
                .toList();
        auditRepo.persistAll(templateWrapperAudits);
    }

    private static void createLandingPageEntry(TemplatePageRepository templatePageRepository,AuditRepo auditRepo) {
        var rec1 = TemplateLandingPage.builder()
                .sponsorCode("Test")
                .recipient("BOA-Thej")
                .createBy(CREATE_BY)
                .updateBy(CREATE_BY)
                .createdDate(LocalDate.now().minusDays(3))
                .updatedDate(LocalDate.now())
                .templateType(TemplateType.WIRE_OUT)
                .templateName("Test_Wire_Out")
                .ntAccntSk(List.of(ACCNT_NO_1, ACCNT_NO_2, ACCNT_NO_3))
                .build();
        var rec2 = TemplateLandingPage.builder()
                .sponsorCode("Test")
                .recipient("BOA-Shanaya")
                .createBy(CREATE_BY)
                .updateBy(CREATE_BY)
                .createdDate(LocalDate.now().minusDays(2))
                .updatedDate(LocalDate.now().minusDays(1))
                .templateType(TemplateType.TRANSFER)
                .templateName("Test_Transfer")
                .ntAccntSk(List.of(ACCNT_NO_1, ACCNT_NO_2))
                .build();
        var rec3 =TemplateLandingPage.builder()
                .sponsorCode("Test")
                .recipient("BOA-Abirami")
                .createBy(CREATE_BY)
                .updateBy(CREATE_BY)
                .createdDate(LocalDate.now().minusDays(3))
                .updatedDate(LocalDate.now())
                .templateType(TemplateType.ACH)
                .templateName("Test_ACH_BANKING")
                .ntAccntSk(List.of(ACCNT_NO_1, ACCNT_NO_2))
                .build();
        var rec4 = TemplateLandingPage.builder()
                .sponsorCode("Test")
                .recipient("BOA-Bharathi")
                .createBy(CREATE_BY)
                .updateBy(CREATE_BY)
                .createdDate(LocalDate.now().minusDays(3))
                .updatedDate(LocalDate.now().minusDays(1))
                .templateType(TemplateType.WIRE_IN)
                .templateName("Test_Wire_IN")
                .ntAccntSk(List.of(ACCNT_NO_2, ACCNT_NO_3))
                .build();
        List<TemplateLandingPage> records = List.of(rec1, rec2, rec3, rec4);
        List<AuditEntity> createAudits = records.stream()
                .map(d -> d.diffs(null,"template_landing_page",CREATE_BY))
                .flatMap(Collection::stream)
                .toList();

        templatePageRepository.persistAll(records);
        auditRepo.persistAll(createAudits);


        templatePageRepository.findAll().forEach(d -> log.info(" {} ",d));
    }

}
