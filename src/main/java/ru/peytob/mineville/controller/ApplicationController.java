package ru.peytob.mineville.controller;

import ru.peytob.mineville.controller.loader.ResourcesLoader;
import ru.peytob.mineville.controller.loader.ShadersLoader;
import ru.peytob.mineville.math.Mat4;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.model.game.world.Octree;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.view.WorldDrawer;

import java.io.IOException;
import java.nio.file.Path;

public class ApplicationController {
    WindowController windowController;
    ShadersPack pack;
    Resources resources;
    WorldDrawer worldDrawer;
    Octree octree;

    public ApplicationController() {
        windowController = new WindowController("Mineville", 800, 600);
        windowController.show();
        resources = new Resources();

        try {
            pack = new ShadersLoader().loadShaderPack(Path.of("src/main/resources/shaders"));
            pack.getWorldShader().use();
            pack.getWorldShader().setModelMatrix(Mat4.computeIdentity());
            pack.getWorldShader().setViewMatrix(Mat4.computeIdentity());
            pack.getWorldShader().setProjectionMatrix(Mat4.computeIdentity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResourcesLoader loader = new ResourcesLoader();
        this.resources = loader.loadResources();
        octree = new Octree();

        octree.setBlock(0, 0, 0, resources.getBlockRepository().getBlock("grass"));
        octree.setBlock(1, 1, 1, resources.getBlockRepository().getBlock("grass"));
        octree.setBlock(2, 2, 2, resources.getBlockRepository().getBlock("grass"));
        octree.setBlock(2, 5, 2, resources.getBlockRepository().getBlock("grass"));

        resources.getBlockRepository().getBlocksStream().forEach(block -> System.out.println(block.getTextId()));

        worldDrawer = new WorldDrawer(windowController);
    }

    public void run() {
        while (!windowController.isShouldClose()) {
            windowController.pullEvents();

            worldDrawer.clear();
            pack.getWorldShader().use();
            worldDrawer.draw(octree);
            worldDrawer.display();
        }
    }

    public void destroy() {
        windowController.destroy();
    }
}
