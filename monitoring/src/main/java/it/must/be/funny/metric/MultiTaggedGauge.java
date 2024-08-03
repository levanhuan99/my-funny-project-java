package it.must.be.funny.metric;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiTaggedGauge {
    private final String name;
    private final String description;
    private final String[] tagNames;
    private final MeterRegistry registry;
    private final Map<String, AtomicInteger> atomicIntegerMap = new ConcurrentHashMap<>();
    private final Map<String, Gauge> gaugeMap = new ConcurrentHashMap<>();

    public MultiTaggedGauge(String name, String description, MeterRegistry registry, String ... tags) {
        this.name = name;
        this.description = description;
        this.tagNames = tags;
        this.registry = registry;
    }

    public void change(int changeValue, String ... tagValues){
        String valuesString = Arrays.toString(tagValues);
        if(tagValues.length != tagNames.length) {
            throw new IllegalArgumentException("Counter tags mismatch! Expected args are "
                    + Arrays.toString(tagNames)+", provided tags are " + valuesString);
        }
        AtomicInteger atomicInteger = atomicIntegerMap.get(valuesString);
        Gauge gauge = gaugeMap.get(valuesString);
        if(atomicInteger == null) {
            List<Tag> tags = new ArrayList<>(tagNames.length);
            for(int i = 0; i<tagNames.length; i++) {
                tags.add(new ImmutableTag(tagNames[i], tagValues[i]));
            }
            atomicInteger = new AtomicInteger(0);
            gauge = Gauge.builder(name, atomicInteger, AtomicInteger::get).tags(tags)
                    .description(description).register(registry);
            gaugeMap.put(valuesString, gauge);
            atomicIntegerMap.put(valuesString, atomicInteger);
        }
        atomicInteger.addAndGet(changeValue);

    }
}
