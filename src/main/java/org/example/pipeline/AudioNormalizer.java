package org.example.pipeline;

import org.example.models.VideoFile;

public class AudioNormalizer {
    public void normalize(VideoFile videoFile) {
        System.out.println("Standardizing audio volume to -23 LUFS for " + videoFile.getFilename());
    }
}
