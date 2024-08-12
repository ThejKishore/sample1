package com.kish.learn.application.business.template.model;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record WireTemplate(
        String vendorName,
        String vendorType,
        List<NTAccount> from,
        WireRecipient to
)
        implements Template{ }
