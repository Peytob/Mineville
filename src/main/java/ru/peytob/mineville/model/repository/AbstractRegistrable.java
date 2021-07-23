package ru.peytob.mineville.model.repository;

import ru.peytob.mineville.model.annotation.Immutable;

@Immutable
public abstract class AbstractRegistrable {
    private final String repositoryName;
    private final Integer id;

    public AbstractRegistrable(String repositoryName, Integer id) {
        this.repositoryName = repositoryName;
        this.id = id;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public Integer getId() {
        return id;
    }
}
