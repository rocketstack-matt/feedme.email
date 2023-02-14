/*resource "azurerm_communication_service" "feedme_dev" {
  name = "${var.project}cs${var.environment}"
  resource_group_name = azurerm_resource_group.feedme_dev.name
  //location = var.location
  tags = {
    environment = var.environment
  }
}*/


resource "azapi_resource" "email_service" {
  type = "Microsoft.Communication/emailServices@2022-07-01-preview"
  name = "${var.project}es${var.environment}"
  location = "global"
  parent_id = azurerm_resource_group.feedme_dev.id
  tags = {
    environment = var.environment
  }
  body = jsonencode({
    properties = {
      dataLocation = "United States"
    }
  })
}

resource "azapi_resource" "feedme_email_domain" {
  type = "Microsoft.Communication/emailServices/domains@2022-07-01-preview"
  name = "AzureManagedDomain"
  location = "global"
  parent_id = azapi_resource.email_service.id
  tags = {
    environment = var.environment
  }
  body = jsonencode({
    properties = {
      domainManagement = "AzureManaged"
      userEngagementTracking = "Disabled"
      validSenderUsernames = {"DoNotReply": "DoNotReply"}
    }
  })
}


resource "azapi_resource" "communication_service" {
  type = "Microsoft.Communication/communicationServices@2022-07-01-preview"
  name = "${var.project}cs${var.environment}"
  location = "global"
  parent_id = azurerm_resource_group.feedme_dev.id
  tags = {
    environment = var.environment
  }
  body = jsonencode({
    properties = {
      dataLocation = "United States"
      linkedDomains = [
        azapi_resource.feedme_email_domain.id
      ]
    }
  })
}




