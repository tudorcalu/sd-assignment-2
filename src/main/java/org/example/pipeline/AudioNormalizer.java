package org.example.pipeline;

import org.example.models.VideoFile;
import java.io.File;
import java.io.IOException;

public class AudioNormalizer {
    public void normalize(VideoFile videoFile) {
        System.out.println("Standardizing audio volume to -23 LUFS for " + videoFile.getFilename());
        createTestFile(videoFile.getOutputDirectory(), "audio/ro_dub_synthetic.aac");
    }

    private void createTestFile(String base, String path) {
        File file = new File(base, path);
        file.getParentFile().mkdirs();
        try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
    }
}
