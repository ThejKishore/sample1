package com.kish.learn.application.business.template.model;

import com.kish.learn.application.business.template.audit.AuditEntityHelper;

public sealed abstract class Template implements AuditEntityHelper<Template> permits WireTemplate,TransferTemplate , WireInTemplate, AchTemplate {
}
