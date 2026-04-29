package org.example.pipeline.ingest;

import org.example.models.VideoFile;

public class IngestService {

    private final IntegrityChecker integrityChecker;
    private final FormatValidator formatValidator;

    public IngestService() {
        this.integrityChecker = new IntegrityChecker();
        this.formatValidator = new FormatValidator();
    }

    public boolean process(VideoFile masterFile) {
        System.out.println("Starting Ingest Phase");
        try {
            boolean valid = integrityChecker.validateChecksum(masterFile);
            if (!valid) {
                System.out.println("Ingest Phase failed.");
                return false;
            }
            valid = formatValidator.validateFormat(masterFile);
            if (valid) {
                System.out.println("Ingest Phase completed successfully.");
            } else {
                System.out.println("Ingest Phase failed.");
            }
            return valid;
        } catch (Exception e) {
            throw new RuntimeException("Ingest phase error: " + e.getMessage(), e);
        }
    }
}
