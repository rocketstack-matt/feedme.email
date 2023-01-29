resource "azurerm_application_insights" "feedme_dev" {
  name                = "${var.project}-application-insights-${var.environment}"
  location            = var.location
  resource_group_name = azurerm_resource_group.feedme_dev.name
  application_type    = "java"

  tags = {
    environment = "dev"
  }
}

resource "azurerm_service_plan" "feedme_dev" {
  name                = "${var.project}-app-service-plan-${var.environment}"
  resource_group_name = azurerm_resource_group.feedme_dev.name
  location            = var.location
  os_type             = "Linux"
  sku_name            = "Y1"

  tags = {
    environment = "dev"
  }
}

resource "azurerm_linux_function_app" "feedme_dev" {
  name                       = "${var.project}-function-app-${var.environment}"
  resource_group_name        = azurerm_resource_group.feedme_dev.name
  location                   = var.location

  storage_account_name       = azurerm_storage_account.feedme_dev.name
  storage_account_access_key = azurerm_storage_account.feedme_dev.primary_access_key

  service_plan_id            = azurerm_service_plan.feedme_dev.id

  site_config {
    application_stack {
      java_version = "17"
    }
  }

  tags = {
    environment = "dev"
  }
}