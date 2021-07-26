package ru.peytob.mineville.model.loader;

class AbstractResourcesLoader {
    private final String namespace;

    public AbstractResourcesLoader(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }
}
