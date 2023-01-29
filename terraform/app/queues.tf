resource "azurerm_storage_queue" "email_subscriber" {
  name                 = "${var.project}-subscriber-queue"
  storage_account_name = azurerm_storage_account.feedme_dev.name
}