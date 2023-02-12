package email.feedme.util;

import java.util.ArrayList;

import com.azure.communication.email.*;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailContent;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailRecipients;
import com.azure.communication.email.models.SendEmailResult;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.Context;


public class EmailUtil {

    public static void SEND_EMAIL(final String email) {
        
    try {
        
        String endpoint = "https://feedme01csdev.communication.azure.com";
        AzureKeyCredential azureKeyCredential = new AzureKeyCredential("");

        EmailClient emailClient = new EmailClientBuilder()
            .endpoint(endpoint)
            .credential(azureKeyCredential)
            .buildClient();

        EmailAddress emailAddress = new EmailAddress(email);

        ArrayList<EmailAddress> addressList = new ArrayList<>();
        addressList.add(emailAddress);
        
        EmailRecipients emailRecipients = new EmailRecipients(addressList);
        
        EmailContent content = new EmailContent("Verify your email")
            .setPlainText("test message");
        
        EmailMessage emailMessage = new EmailMessage("DoNotReply@a44e378f-ba04-4944-8dcf-32e8dec3e3bc.azurecomm.net", content)
            .setRecipients(emailRecipients);
        
        SendEmailResult response = emailClient.send(emailMessage);
        System.out.println("Message Id: " + response.getMessageId());
    } catch (Exception ex)
    {
        throw ex;
    }
    }
}
