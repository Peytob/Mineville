package ru.peytob.mineville.model.repository;

import ru.peytob.mineville.model.annotation.Immutable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Immutable
public class ImmutableRepository<T extends AbstractRegistrable> {
    protected Map<Integer, T> registrableByIds;
    protected Map<String, T> registrableByNames;

    public ImmutableRepository() {
        this.registrableByIds = new HashMap<>();
        this.registrableByNames = new HashMap<>();
    }

    public T get(String repositoryName) {
        return registrableByNames.get(repositoryName);
    }

    public T get(Integer repositoryId) {
        return registrableByIds.get(repositoryId);
    }

    public boolean contains(String repositoryName) {
        return registrableByNames.containsKey(repositoryName);
    }

    public boolean contains(Integer repositoryId) {
        return registrableByIds.containsKey(repositoryId);
    }

    public int getCount() {
        return registrableByIds.size();
    }

    public Stream<T> getStream() {
        return registrableByIds.values().stream();
    }

    public Stream<T> getParallelStream() {
        return registrableByNames.values().parallelStream();
    }
}
