package nl.smartworkx.admin.application;

import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.bank.BankFileUpload;
import nl.smartworkx.admin.model.bank.BankFileUploadRepository;
import nl.smartworkx.admin.model.bank.Mt940ImporterService;

/**
 *
 */
@Service
public class BankFileUploadService {

    private final BankFileUploadRepository repository;

    private final Mt940ImporterService mt940ImporterService;

    public BankFileUploadService(BankFileUploadRepository repository, Mt940ImporterService mt940ImporterService) {
        this.repository = repository;
        this.mt940ImporterService = mt940ImporterService;
    }

    public void create(BankFileUpload bankFileUpload){
        this.repository.save(bankFileUpload);
        this.mt940ImporterService.importMt940(bankFileUpload.getContent());
    }
}
