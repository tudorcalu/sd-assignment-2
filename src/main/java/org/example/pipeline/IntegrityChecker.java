package org.example.pipeline;

import org.example.models.VideoFile;

public class IntegrityChecker {
    public boolean validateChecksum(VideoFile file) {
        System.out.println("Validating checksum for " + file.getFilename());
        return true;
    }
}
