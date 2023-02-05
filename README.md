# Feedme.email

Feedme.email is a lightweight application which provides funcionality to allow readers of a blog/website to subscribe to receive rss feed based newsletters. You can find more information on what made us build this project at https://www.matthewba.in/

Feedme.email is built as a serverless application leveraging Microsofts Azure cloud infrastructure. The project uses [Terraform](https://www.terraform.io/) to manage the Azure infrastructure.
 

## Development environment

You can use any IDE of your choice however the project is currently being developed using [Microsoft VS Code](https://code.visualstudio.com/) and we recommend you use it. The instructions below will guide you through the setting up a developement environment in VS Code to build and deploy the code to your own Azure account.

You can find details on how to create an Azure account [here](https://azure.microsoft.com/en-gb/free/)

### VS Code Set-up
Once you have VS Code installed, navigate to the Exentions menu and install the following:
- Azure Functions (Microsoft)    
- Azure Account (Microsoft)
- Azure App Service (Microsoft)
- Azure CLI Tools (Microsoft)
- Azure Developer CLI (Microsoft)
- Azure Terraform (Microsoft)
- GitHub Pull Requests and Issues (GitHub)
- Gradle for Java (Microsoft)
- HashiCorp Terraform (HashiCorp)
- Maven for Java (Microsoft)
- REST API Client (Unjin Jang)



### Dependencies

You'll need to ensure you have the following dependencies and programming languages are installed and configured on your machine:

- git
- gradle 7.6
- java SDK 17
- terraform
- [Azure Functions Core Tools](https://learn.microsoft.com/en-us/azure/azure-functions/functions-run-local?tabs=v4%2Cwindows%2Ccsharp%2Cportal%2Cbash#install-the-azure-functions-core-tools)


### Downloading Source Code and Build

Once VS Code has been configured the following steps should be followed to download the code and build.

If you are planning on contributing to the project you will need to create a fork of the repository in your own GitHub account and clone this.

If you are just building this for yourself then you can clone the public repository [click here for more details on how to use git in VS Code](https://code.visualstudio.com/docs/sourcecontrol/overview)

Azure requires the application name and storeage account to be unique globally, both names are generated from the project name, as such you'll need to update the project name to be unique (include your initials) in terraform\app\terraform.tfvars and update the queue name in src\main\java\email\feedme\functions\SubscribersFunction.java to refect the same.

#### Initialise Terraform and Deploy infrastructure to Azure

- This project uses Terraform to deploy and manage Azure infrastructure

 Open a terminal window, navigate to ./terraform/app/ dir and run 'terraform init'

 Login to you azure account 
```sh
az login
```
Validate the terraform files  
```sh
terraform validate
```
Review the changes that terraform will make 
```sh
terraform plan
```

Deploy to your Azure account 
```sh
terraform apply
```


### Running the code locally

Once you've deployed the required infrastructure to your azure account you can run the code locally. In order to do this you'll get to fetch the settings required by the function to connect to deployed infrastructure by running the following, ensure you update the project name based on what you've set in your project.

```sh
func azure functionapp fetch-app-settings <projectname>-function-app-dev'
```

Check the local.settings.json file and confirm that the connection settings have been updated to reflect the connection key

To run the function locally either run the azureFunctionsRun gradle task or go to the 'Run and Debug' Tab via the right menu (or via Ctrl + Shift + D) and select 'Run Azure Function' from 'RUN AND DEBUG' drop down list

This should start a local server and you should see the following:

```sh
Functions:

        subscribers: [POST] http://localhost:7071/api/subscribers

For detailed output, run func with --verbose flag.
[2023-02-05T19:03:29.624Z] Java HotSpot(TM) 64-Bit Server VM warning: Options -Xverify:none and -noverify were deprecated in JDK 13 and will likely be r[2023-02-05T19:03:30.995Z] Worker process started and initialized.
[2023-02-05T19:03:34.739Z] Host lock lease acquired by instance ID '0000000000000000000000002F3D0C97'.
```
Using curl or a REST client you should be able to post an email address to the function.