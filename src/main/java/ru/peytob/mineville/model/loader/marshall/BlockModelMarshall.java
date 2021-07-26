package ru.peytob.mineville.model.loader.marshall;

import com.google.gson.annotations.Expose;

public class BlockModelMarshall {
    public BlockFaceMarshall faces;

    @Expose(deserialize = false)
    public String repositoryName;
}
