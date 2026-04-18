package org.example.pipeline;

import org.example.models.VideoFile;

public class IngestService {
    private IntegrityChecker integrityChecker;
    private FormatValidator formatValidator;

    public IngestService() {
        this.integrityChecker = new IntegrityChecker();
        this.formatValidator = new FormatValidator();
    }

    public boolean process(VideoFile masterFile) {
        System.out.println("Starting Ingest Phase");
        boolean isValid = integrityChecker.validateChecksum(masterFile);
        if (isValid) {
            isValid = formatValidator.validateFormat(masterFile);
        }
        
        if (isValid) {
            System.out.println("Ingest Phase completed successfully.");
        } else {
            System.out.println("Ingest Phase failed.");
        }
        
        return isValid;
    }
}
