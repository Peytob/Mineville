package ru.peytob.mineville.model.loader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class ModelLoader {
    private final Gson gson;
    private final Map<String, BlockUntexturedModelGson> loadedData;

    public ModelLoader() {
        this.gson = new Gson();
        this.loadedData = new HashMap<>();
    }

    public BlockUntexturedModelGson loadModel(File file) throws IOException {
        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            BlockUntexturedModelGson loadedModel = gson.fromJson(reader, BlockUntexturedModelGson.class);

            if (loadedModel.textId == null) {
                String modelName = file.getName();
                int pointIndex = modelName.lastIndexOf('.');

                if (pointIndex == -1) {
                    loadedModel.textId = file.getName();
                }

                else {
                    loadedModel.textId = file.getName().substring(0, pointIndex);
                }
            }

            loadedData.put(loadedModel.textId, loadedModel);
            return loadedModel;
        }
    }

    public BlockUntexturedModelGson get(String key) throws IllegalArgumentException {
        BlockUntexturedModelGson model = loadedData.get(key);

        if (model == null) {
            throw new IllegalArgumentException("Key " + key + " not found");
        }

        return model;
    }
}
