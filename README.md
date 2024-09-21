# Dev Eficiente Challenge
<p align="center">
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java badge" />
<img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Spring badge" />
<img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white" alt="Swagger badge" />
<img src="https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL badge" />
<img src="https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white" alt="Git badge" />
</p>
  
<p align="center">
  <img src="docs/project.gif" alt="GIF of the Swagger Docs" />
</p> 
  
## Running
Open the terminal in the repository root folder, and then execute the following command:
```shell
./mvnw spring-boot:run
```
PS.: if you face permission issues, just run the command bellow, and then run the command above after again.
```shell
chmod +x mvnw
```
Then, you can just go to http://localhost:8080 and access the Swagger Docs. 
## Explaining the project  
Challenge proposed by Alberto Souza in the Dev Eficiente course, which consists of creating a system (in this case an API)
that simulates the functionality of the Casa do Código website.
## Stack  
 - Language: Java v21;
 - Framework: Spring & Spring Boot;
 - Docs: Swagger/OpenAPI;
 - RDBMS: MySQL;
## Entities  
 - Author;
 - Book;
 - Category;
 - Country;
 - State;
 - Coupon;
 - Purchase.
## DB Diagram  
<p align="center">
  <img src="docs/diagram.png" alt="GIF of the Swagger Docs" />
</p> 