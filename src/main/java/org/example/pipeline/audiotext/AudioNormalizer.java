package org.example.pipeline.audiotext;

import org.example.models.VideoFile;
import java.io.File;

public class AudioNormalizer {

    private static final String LOUDNORM_FILTER = "loudnorm=I=-23:LRA=7:TP=-2";
    private static final String AUDIO_CODEC     = "aac";
    private static final String AUDIO_BITRATE   = "192k";
    private static final String OUTPUT_SUBPATH  = "/audio/ro_dub_synthetic.aac";

    public void normalize(VideoFile videoFile) throws Exception {
        String output = videoFile.getOutputDirectory() + OUTPUT_SUBPATH;
        new File(output).getParentFile().mkdirs();

        ProcessBuilder pb = new ProcessBuilder(
            "ffmpeg",
            "-i", videoFile.getFilename(),
            "-af", LOUDNORM_FILTER,
            "-vn",
            "-c:a", AUDIO_CODEC,
            "-b:a", AUDIO_BITRATE,
            "-y", output
        );
        pb.inheritIO();
        int exit = pb.start().waitFor();
        if (exit != 0) {
            throw new RuntimeException("ffmpeg audio normalization failed with exit code " + exit);
        }
        System.out.println("  OK normalized audio -> " + output);
    }
}
