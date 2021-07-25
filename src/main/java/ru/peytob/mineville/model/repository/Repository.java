package ru.peytob.mineville.model.repository;

import ru.peytob.mineville.model.annotation.Immutable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Immutable
public class Repository<T extends AbstractRegistrable> {
    protected Map<Integer, T> registrableByIds;
    protected Map<String, T> registrableByNames;

    public Repository() {
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

    void add(T registrable) {
        registrableByIds.put(registrable.getId(), registrable);
        registrableByNames.put(registrable.getRepositoryName(), registrable);
    }

    void remove(T registrable) {
        registrableByIds.remove(registrable.getId());
        registrableByNames.remove(registrable.getRepositoryName());
    }

    void remove(Integer repositoryId) {
        T registrable = get(repositoryId);
        remove(registrable);
    }

    void remove(String repositoryName) {
        T registrable = get(repositoryName);
        remove(registrable);
    }
}
