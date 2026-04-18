package org.example.pipeline;

import org.example.models.VideoFile;

public class FormatValidator {
    public boolean validateFormat(VideoFile file) {
        System.out.println("Validating format and headers for " + file.getFilename());
        return true;
    }
}
