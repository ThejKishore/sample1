package com.kish.learn.application.business.template.model;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record AchTemplate(
        String vendorName,
        String vendorType,
        List<NTAccount> from,
        AchRecipient to
)
        implements Template{ }
