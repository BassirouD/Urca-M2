package org.diallo.controller;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.AllArgsConstructor;
import org.diallo.entities.Candidate;
import org.diallo.services.interfaces.ICandidatService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/candidat")
public class CandidatController {
    private ICandidatService iCandidatService;
    @Value("${firebase.storage.bucket}")
    private String bucketName;

    @GetMapping("/all")
    public List<Candidate> candidateList() throws ExecutionException, InterruptedException {
        return iCandidatService.candidatList();
    }

    @PostMapping("/create")
    public Candidate createCandidat(@RequestBody Candidate candidate) {
        return iCandidatService.create(candidate);
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException, IOException {
        InputStream serviceAccount = new ClassPathResource("classpath:config/diallo-koula-shop-firebase-adminsdk-hjvit-677802c5c6.json").getInputStream();
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
        BlobId blobId = BlobId.of(bucketName, Objects.requireNonNull(file.getOriginalFilename()));
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        // Upload the file to Firebase Storage
        storage.create(blobInfo, file.getBytes());
        // You can save additional metadata or retrieve the download URL if needed
        return "Image uploaded successfully!";
    }

    @PutMapping("/update")
    public Candidate updateCandidat(@RequestBody Candidate candidate) {
        return iCandidatService.update(candidate);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteCustomer(@PathVariable String id) throws ExecutionException, InterruptedException {
        return iCandidatService.delete(id);
    }
}
