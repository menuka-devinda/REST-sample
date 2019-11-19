package com.menudev.api;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class MicrmeterRestHandler {

    private final MeterRegistry meterRegistry;

    public MicrmeterRestHandler(MeterRegistry meterRegistry) {

        this.meterRegistry = meterRegistry;
    }

    public MeterRegistry getMeterRegistry() {
        return meterRegistry;
    }

    public void handleMessage() {
        Counter counter = meterRegistry.counter("testCounter", "tagName", "value");
        counter.increment();
        System.out.println("counter \t" + counter.count());

    }
}
