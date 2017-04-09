package nl.smartworkx.admin.web;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import nl.smartworkx.admin.application.BankFileUploadService;
import nl.smartworkx.admin.model.bank.BankFileUpload;

/**
 *
 */
@RepositoryRestController
@RequestMapping("bank-file-uploads")
public class BankFileUploadController {

    private BankFileUploadService service;

    public BankFileUploadController(BankFileUploadService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void create(@RequestParam("file") MultipartFile file) throws Exception {
        String stream = new  String(file.getBytes());
        service.create(new BankFileUpload(stream));
    }
}
