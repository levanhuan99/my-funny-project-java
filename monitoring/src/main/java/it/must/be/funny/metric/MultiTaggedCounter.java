package it.must.be.funny.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiTaggedCounter {
    private final String name;
    private final String description;
    private final String[] tagNames;
    private final MeterRegistry registry;
    private final Map<String, Counter> counters = new ConcurrentHashMap<>();

    public MultiTaggedCounter(String name, String description, MeterRegistry registry, String ... tags) {
        this.name = name;
        this.description = description;
        this.tagNames = tags;
        this.registry = registry;
    }

    public void increment(String ... tagValues){
        String valuesString = Arrays.toString(tagValues);
        if(tagValues.length != tagNames.length) {
            throw new IllegalArgumentException("Counter tags mismatch! Expected args are "
                    + Arrays.toString(tagNames)+", provided tags are " + valuesString);
        }
        Counter counter = counters.get(valuesString);
        if(counter == null) {
            List<Tag> tags = new ArrayList<>(tagNames.length);
            for(int i = 0; i<tagNames.length; i++) {
                tags.add(new ImmutableTag(tagNames[i], tagValues[i]));
            }
            counter = Counter.builder(name).tags(tags)
                    .description(description).register(registry);
            counters.put(valuesString, counter);
        }
        counter.increment();
    }

}
