package org.example.pipeline.packaging;

import org.example.models.VideoFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class DrmEncryptor {

    private static final String[] FILES_TO_ENCRYPT = {
        "video/h264/720p_h264.mp4",
        "video/h264/1080p_h264.mp4",
        "video/h264/4k_h264.mp4"
    };

    private static final int  XOR_MASK           = 0xFF;
    private static final long SCRAMBLE_BYTE_COUNT = 1024;

    public void encrypt(VideoFile videoFile) throws Exception {
        String outDir = videoFile.getOutputDirectory();

        for (String relativePath : FILES_TO_ENCRYPT) {
            File target = new File(outDir, relativePath);
            if (target.exists() && target.length() > 0) {
                simulateEncryption(target);
            }
        }
    }

    private void simulateEncryption(File file) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw");
             FileChannel channel = raf.getChannel()) {

            long size = Math.min(channel.size(), SCRAMBLE_BYTE_COUNT);
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, size);

            while (buffer.hasRemaining()) {
                byte b = buffer.get();
                buffer.put(buffer.position() - 1, (byte) (b ^ XOR_MASK));
            }
            channel.force(true);
        }
        System.out.println("  OK DRM Wrapper applied to -> " + file.getPath());
    }
}
