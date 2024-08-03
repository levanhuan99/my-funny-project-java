package it.must.be.funny.metric;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiTaggedDistributionSummary {
    private final String name;
    private final String description;
    private final String[] tagNames;
    private final MeterRegistry registry;
    private final Map<String, DistributionSummary> distributionSummaryMap = new ConcurrentHashMap<>();
    private final long[] buckets;
    private static final long[] defaultBuckets = new long[]{1, 2, 5, 10, 50, 100, 500, 1000, 2000};

    public MultiTaggedDistributionSummary(String name, String description,
                                          MeterRegistry registry,
                                          String[] tagNames, long[] buckets) {
        this.name = name;
        this.description = description;
        this.tagNames = tagNames;
        this.registry = registry;
        this.buckets = buckets;
    }
    public void record(double value, String ... tagValues) {
        String valuesString = Arrays.toString(tagValues);
        if (tagValues.length != tagNames.length) {
            throw new IllegalArgumentException("Counter tags mismatch! Expected args are "
                    + Arrays.toString(tagNames) + ", provided tags are " + valuesString);
        }
        DistributionSummary distributionSummary = distributionSummaryMap.get(valuesString);
        if (distributionSummary == null) {
            List<Tag> tags = new ArrayList<>(tagNames.length);
            for (int i = 0; i < tagNames.length; i++) {
                tags.add(new ImmutableTag(tagNames[i], tagValues[i]));
            }
            distributionSummary = DistributionSummary
                    .builder(name)
                    .description(description)
                    .tags(tags)
                    .sla(buckets.length == 0 ? defaultBuckets : buckets)
                    .publishPercentiles(0.5, 0.7, 0.8, 0.9, 0.95, 0.99)
                    .register(registry);
            distributionSummaryMap.put(valuesString, distributionSummary);
        }
        distributionSummary.record(value);
    }
}
