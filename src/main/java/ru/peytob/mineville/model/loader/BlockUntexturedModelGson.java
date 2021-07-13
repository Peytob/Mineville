package ru.peytob.mineville.model.loader;

import com.google.gson.annotations.Expose;

class BlockUntexturedModelGson {
    public BlockUntexturedFaces faces;

    @Expose(deserialize = false)
    public String textId;
}
