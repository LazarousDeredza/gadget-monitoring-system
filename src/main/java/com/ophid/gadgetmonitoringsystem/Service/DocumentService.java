package com.ophid.gadgetmonitoringsystem.Service;





import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;


import com.ophid.gadgetmonitoringsystem.Entity.Documents;
import com.ophid.gadgetmonitoringsystem.Repository.DocumentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class DocumentService {

    @Autowired
    DocumentsRepo documentsRepo;
    public static String uploadDirectory;
    private final Path fileStorageLocation;

    public DocumentService() {
        this.fileStorageLocation = Paths.get(uploadDirectory).toAbsolutePath().normalize();
    }

    public String saveFile(Documents document) throws IOException {
        this.documentsRepo.save(document);
        return "success";
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            System.out.println(filePath + " " + resource);

            return resource;

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


        public Documents store(MultipartFile file, String folder, String ID) throws IOException {



        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String size = String.valueOf(file.getSize());
        String filePath = Paths.get(uploadDirectory, file.getOriginalFilename()).toString();
        String DocumentPath = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + folder+ File.separator +ID;
        new File(DocumentPath);
        if (!Files.exists(Paths.get(DocumentPath), new LinkOption[0])) {
            Files.createDirectories(Paths.get(DocumentPath));
        }

        if (fileName.contains(" ")) {
            fileName = fileName.replace(" ", "_");
        }

        byte[] data = file.getBytes();
        Path path = Paths.get(DocumentPath + File.separator + fileName);
        Files.write(path, data, new LinkOption[0]);
        Documents document = new Documents(fileName, filePath, size, file.getContentType());

        return (Documents)this.documentsRepo.save(document);
    }



    static {
        uploadDirectory = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "drones";
    }

}
