package email.feedme.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.QueueTrigger;

import email.feedme.util.EmailUtil;

public class VerifyEmailFunction {
    @FunctionName("VerifyEmail")
    public void run (@QueueTrigger(name="msg",queueName = "feedme01-subscriber-queue",connection = "AzureWebJobsStorage")
     String msg,
    final ExecutionContext context) {
        
        //to do: send an email to the address
        try {
            EmailUtil.SEND_EMAIL(msg);
        } catch (Exception ex)
        {
            context.getLogger().warning("Failed to send email");
            context.getLogger().warning(ex.toString());
        }
        context.getLogger().info("Queue message processed: " + msg);
        
    }
}
