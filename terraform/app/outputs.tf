output "function_app_name" {
  value = azurerm_linux_function_app.feedme_dev.name
  description = "Deployed function app name"
}

output "function_app_default_hostname" {
  value = azurerm_linux_function_app.feedme_dev.default_hostname
  description = "Deployed function app hostname"
}