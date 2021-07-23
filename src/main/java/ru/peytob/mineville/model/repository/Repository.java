package ru.peytob.mineville.model.repository;

public class Repository<T extends AbstractRegistrable> extends ImmutableRepository<T> {
    public void add(T registrable) {
        registrableByIds.put(registrable.getId(), registrable);
        registrableByNames.put(registrable.getRepositoryName(), registrable);
    }

    public void remove(T registrable) {
        registrableByIds.remove(registrable.getId());
        registrableByNames.remove(registrable.getRepositoryName());
    }

    public void remove(Integer repositoryId) {
        T registrable = get(repositoryId);
        remove(registrable);
    }

    public void remove(String repositoryName) {
        T registrable = get(repositoryName);
        remove(registrable);
    }
}
