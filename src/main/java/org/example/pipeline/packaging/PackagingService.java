package org.example.pipeline.packaging;

import org.example.models.VideoFile;

public class PackagingService {

    private final ManifestGenerator manifestGenerator;
    private final DrmEncryptor drmEncryptor;
    private final BundleBuilder bundleBuilder;

    public PackagingService() {
        this.manifestGenerator = new ManifestGenerator();
        this.drmEncryptor = new DrmEncryptor();
        this.bundleBuilder = new BundleBuilder();
    }

    public void process(VideoFile videoFile) {
        try {
            manifestGenerator.generateManifests(videoFile);
            drmEncryptor.encrypt(videoFile);
            bundleBuilder.buildBundle(videoFile);
        } catch (Exception e) {
            throw new RuntimeException("Packaging phase error: " + e.getMessage(), e);
        }
        System.out.println("Packaging Phase completed successfully");
    }
}
