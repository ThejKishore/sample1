package com.kish.learn.application.business.template.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record WireRecipient(Long recipientId, String recipientName, String recipientEmail , String bankDetails) {
}
