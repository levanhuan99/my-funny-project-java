package it.must.be.funny.metric;

import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class MultiTaggedTimer {
    private final String name;
    private final String description;
    private final String[] tagNames;
    private final MeterRegistry registry;
    private final Map<String, Timer> timerHashMap = new ConcurrentHashMap<>();
    private final Duration[] durations;
    private static final Duration[] defaultDurations = new Duration[18];
    static {
        int jj = 0;
        for (int base: Arrays.asList(1, 2, 5)) {
            for (int mul: Arrays.asList(1, 10, 100)) {
                for (ChronoUnit timeUnit: Arrays.asList(ChronoUnit.MILLIS, ChronoUnit.SECONDS)) {
                    defaultDurations[jj++] = Duration.of(base * mul, timeUnit);
                }
            }
        }
    }

    public MultiTaggedTimer(String name, String description, MeterRegistry registry, String[] tagNames,
                            Duration[] durations) {
        this.name = name;
        this.description = description;
        this.tagNames = tagNames;
        this.registry = registry;
        this.durations = durations;
    }
    public void record(long value, TimeUnit timeUnit, String ... tagValues) {
        String valuesString = Arrays.toString(tagValues);
        if(tagValues.length != tagNames.length) {
            throw new IllegalArgumentException("Counter tags mismatch! Expected args are "
                    + Arrays.toString(tagNames) +", provided tags are " + valuesString);
        }
        Timer timer = timerHashMap.get(valuesString);
        if (timer == null) {
            List<Tag> tags = new ArrayList<>(tagNames.length);
            for (int i = 0; i < tagNames.length; i++) {
                tags.add(new ImmutableTag(tagNames[i], tagValues[i]));
            }
            timer = Timer
                    .builder(name)
                    .tags(tags)
                    .description(description)
                    .sla(durations.length == 0? defaultDurations : durations)
                    .publishPercentiles(0.1,0.3,0.5, 0.7, 0.8, 0.9, 0.95, 0.99)
                    .register(registry);
            timerHashMap.put(valuesString, timer);
        }
        timer.record(value, timeUnit);
    }
}
