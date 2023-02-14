terraform {
  required_providers {
    azurerm = {
      source = "hashicorp/azurerm"
      # Root module should specify the maximum provider version
      # The ~> operator is a convenient shorthand for allowing only patch releases within a specific minor release.
      version = "~> 3.41.0"
    }

    azapi = {
      source = "azure/azapi"
      version = "~> 1.3.0"
    }
  }
}

provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "feedme_dev" {
  name = "${var.project}-${var.environment}"
  location = var.location

  tags = {
    environment =  var.environment
  }
}

