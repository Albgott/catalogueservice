package com.albgott.catalogueservice.shared.domain.event;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}
