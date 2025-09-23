# Job Portal System
A **Job Portal System** built with **Spring Boot, Spring Security, and JWT authentication**.  
This system enables **Applicants, Employers, and Admins** to interact in a secure and role-based environment.  
Employers can post jobs, applicants can apply, withdraw, and track application statuses, while users manage profiles and authentication.


## Features

- User Management: CRUD operations with unique validations (email, phone, userId).


### Authentication & Authorization
- User **registration** (Sign-Up) new users with roles: `APPLICANT`, `EMPLOYER`, `ADMIN`
- **Login** with email & password → returns a **JWT token**
- - **JWT filter integration** → every request is validated
- Role-based access control:
    - **Applicant** → Apply to jobs, manage profile
    - **Employer** → Post Jobs and manage job listings, view applicants
    - **Admin** → Manage users, monitor the system
  



###  Employer
- Register/Login as Employer
- Create, update, and delete job postings
- View applicants who applied for their jobs
###  Applicant
- Register/Login as Applicant
- Create & update applicant profile
- Apply for jobs, update or delete Job applications
- Track application status

### Admin
- Manage user accounts (CRUD)
- View all job applications
- Monitor system activity

- Job Module: Search and filter jobs by title, location, type, salary, experience, and deadline.

### Public Access
- Anyone can **register/login**
- Anyone can **browse job listings**
- Anyone can  Search and filter jobs by title, location, type, salary, experience, and deadline.


- Data Handling: Custom exception handling, validation, and relational mappings with JPA/Hibernate.

- BaseModel: UUID-based IDs with automatic createdAt & updatedAt timestamp tracking.

## Tech Stack

- Language: Java
- **Java 17**
- **Spring Boot **
- **Spring Security + JWT**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **Maven**
- **Lombok**
- **Jakarta Validation API**
- Other Tools: Postman

## Project Structure
- src/main/java/com/prakshit/jobportalsystem

- controller  ----->      # REST Controllers (Auth, User, Employer, Applicant, Job, JobApplication)
- dto         ----->      # Request & Response DTOs 
- entity    ----->        # JPA Entities (User, Employer, ApplicantProfile, Job, JobApplication) 
- exceptions   ----->     # Custom Exceptions 
- mapper      ----->      # Entity <-> DTO Mappers 
- repository   ----->     # Spring Data JPA Repositories 
- service     ----->      # Service Layer Interfaces & Implementations
  security  ----->        # JWT, Filters, Security Config

## Security Rules

  
## How to Run

    Endpoint                                     Access
    `/auth/register`, `/auth/login`              Public
    `GET /jobs/**`                               Public
    `POST /jobs`                                 Employer only                   
    `/jobApplications/**`                        Applicant → create/update/delete<br>Employer/Admin → view | 
    `/applicant/**`                              Applicant → create/update/delete<br>Public → view  
    `/employers/**`                              Employer only                    
    `/users/**`                                  Public GET<br>Admin → update/delete 
    `anyRequest()`                               Authenticated

1. Clone the repository

2. Configure database in application.properties

- spring.datasource.url=jdbc:mysql://localhost:3306/job_portal
- spring.datasource.username=
- spring.datasource.password=
- spring.jpa.hibernate.ddl-auto=update


3. Run the application

- mvn spring-boot:run


4. Test with Postman

- POST /auth/register → Create a user
- POST /auth/login → Get JWT token
- Use token in Authorization: Bearer <token> header

## API Examples
 ## Register User
- POST /auth/register
- Content-Type: application/json

{
- "name": "John Doe",
- "email": "john@example.com",
- "password": "password123",
- "userRole": "APPLICANT"

    }
    
  ## Login

- POST /auth/login 

- Content-Type: application/json

{
- "email": "john@example.com",
- "password": "password123"
}


## Create Job (Employer only)
- POST /jobs
- Authorization: Bearer <token>
- Content-Type: application/json

{
- "title": "Java Developer",
- "description": "Looking for a skilled Java backend developer",
- "location": "Remote"
}

## Author

Prakshit Nagrale
- Backend Developer | Java + Spring Boot Enthusiast