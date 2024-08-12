package com.kish.learn.application.business.template.model;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record WireInTemplate(
        String vendorName,
        String vendorType,
        WireRecipient from,
        List<NTAccount> to
        )
        implements Template{ }
