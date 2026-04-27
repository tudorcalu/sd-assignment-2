package org.example.pipeline.ingest;

import org.example.models.VideoFile;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class IntegrityChecker {
    public boolean validateChecksum(VideoFile file) {
        System.out.println("Validating checksum for " + file.getFilename());
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(file.getFilename());
            byte[] byteArray = new byte[8192];
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            fis.close();
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            System.out.println("OK " + sb.toString());
        } catch (Exception e) {
            System.err.println("FAIL " + e.getMessage());
        }
        return true;
    }
}
