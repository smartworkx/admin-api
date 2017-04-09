package nl.smartworkx.admin.model.bank;

import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

/**
 *
 */
@RepositoryEventHandler(BankFileUpload.class)
public class BankFileUploadEventHandler {

    @HandleAfterCreate
    public void handleAfterCreate(BankFileUpload bankFileUpload){
        System.out.print("Event handler works");
    }
}
