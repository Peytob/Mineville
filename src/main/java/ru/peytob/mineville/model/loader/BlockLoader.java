package ru.peytob.mineville.model.loader;

import org.reflections.Reflections;
import ru.peytob.mineville.model.annotation.Block;
import ru.peytob.mineville.model.loader.base.BaseBlock;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class BlockLoader extends AbstractResourcesLoader {
    private final Reflections reflections;
    private Set<BaseBlock> instances;

    public BlockLoader(String namespace, String packageName) {
        super(namespace);
        this.reflections = new Reflections(packageName);
        this.instances = null;
    }

    public Set<BaseBlock> getBaseInstances() {
        if (instances != null) {
            return instances;
        }

        instances = createAnnotatedClassesInstances();
        return instances;
    }

    private Set<BaseBlock> createAnnotatedClassesInstances() {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Block.class);

        return annotated.stream()
                .filter(BaseBlock.class::isAssignableFrom) // Checks classes.
                .map(cls -> {
                    try {
                        return (BaseBlock) cls.getConstructor().newInstance();
                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                            IllegalAccessException e) {
                        return null; // Put logs there.
                    }
                }) // Get BaseBlock instances.
                .filter(Objects::nonNull) // Remove null.
                .collect(Collectors.toSet());
    }
}
