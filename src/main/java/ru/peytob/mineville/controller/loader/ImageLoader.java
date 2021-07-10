package ru.peytob.mineville.controller.loader;

import de.matthiasmann.twl.utils.PNGDecoder;
import ru.peytob.mineville.model.graphic.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ImageLoader {
    public ImageLoader() {

    }

    public Image loadFromFile(String path) throws IOException {
        try (InputStream inStream = new FileInputStream(path)) {
            PNGDecoder decoder = new PNGDecoder(inStream);

            ByteBuffer buffer = ByteBuffer.allocateDirect(Image.BYTES_PER_PIXEL * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buffer, Image.BYTES_PER_PIXEL * decoder.getWidth(), PNGDecoder.Format.RGBA);
            buffer.flip();

            return new Image(buffer, decoder.getWidth(), decoder.getHeight());
        }
    }
}
