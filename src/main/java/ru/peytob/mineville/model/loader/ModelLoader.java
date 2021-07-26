package ru.peytob.mineville.model.loader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import ru.peytob.mineville.model.graphic.block.BlockFace;
import ru.peytob.mineville.model.graphic.block.BlockFacePoints;
import ru.peytob.mineville.model.loader.base.BaseBlockModel;
import ru.peytob.mineville.model.loader.marshall.BlockModelMarshall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ModelLoader extends AbstractResourcesLoader {
    private final Gson gson;

    public ModelLoader(String namespace)  {
        super(namespace);
        this.gson = new Gson();
    }

    public List<BaseBlockModel> loadModels(File modelsDirectory) throws IOException {
        List<BaseBlockModel> acc = new LinkedList<>();
        loadModels(modelsDirectory, acc);
        return acc;
    }

    private void loadModels(File modelsDirectory, List<BaseBlockModel> acc) throws IOException {
        // Checks if directory is empty.
        File[] fileList = modelsDirectory.listFiles();
        if (fileList == null) {
            return;
        }

        for (File file : fileList) {
            if (file.isFile()) {
                BaseBlockModel model = loadModel(file);
                acc.add(model);
            }

            else {
                loadModels(file, acc);
            }
        }
    }

    public BaseBlockModel loadModel(File file) throws IOException {
        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            BlockModelMarshall loadedModel = gson.fromJson(reader, BlockModelMarshall.class);

            if (loadedModel.repositoryName == null) {
                String modelName = file.getName();
                int pointIndex = modelName.lastIndexOf('.');

                if (pointIndex == -1) {
                    loadedModel.repositoryName = file.getName();
                } else {
                    loadedModel.repositoryName = file.getName().substring(0, pointIndex);
                }
            }

            return new BaseBlockModel(
                    getNamespace() + "::" + loadedModel.repositoryName,
                    new BlockFace(new BlockFacePoints(loadedModel.faces.north)),
                    new BlockFace(new BlockFacePoints(loadedModel.faces.south)),
                    new BlockFace(new BlockFacePoints(loadedModel.faces.west)),
                    new BlockFace(new BlockFacePoints(loadedModel.faces.east)),
                    new BlockFace(new BlockFacePoints(loadedModel.faces.top)),
                    new BlockFace(new BlockFacePoints(loadedModel.faces.bottom))
            );
        }
    }
}
