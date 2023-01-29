package email.feedme.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.QueueOutput;

import java.util.Optional;

/**
 * Azure Functions to manage email subscribers list with HTTP Trigger.
 */
public class SubscribersFunction {
    /**
     * This function listens at endpoint "/api/subscribers".
     */
    @FunctionName("subscribers")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            @QueueOutput(name = "msg", queueName = "feedme-subscriber-queue",
                    connection = "AzureWebJobsStorage") OutputBinding<String> msg,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request on /api/subscriber.");

        // Parse query parameter
        final String email = request.getQueryParameters().get("email");

        if (email == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass email as a query param").build();
        }

        msg.setValue(email);
        return request.createResponseBuilder(HttpStatus.OK).build();
    }
}
