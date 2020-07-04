package com.nospher.rankup.util.storage.cache;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
public abstract class NspCaffeineCache<K, V> {

    private final Cache<K, V> CACHE;

    public void insert(K key, V value) {
        this.CACHE.put(key, value);
    }

    public V fetch(Predicate<V> predicate) {
        return this.CACHE.asMap().values().stream()
                .filter(Objects::nonNull)
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public Collection<V> fetchAll() {
        return this.CACHE.asMap().values();
    }
}
