# Job Portal System
A Spring Boot–based Job Portal System that enables Users, Employers, and Applicants to interact seamlessly. Employers can post jobs, applicants can apply, withdraw, and track application statuses, while users manage profiles and authentication.


## Features

- User Management: CRUD operations with unique validations (email, phone, userId).

- Employer Module: Create and manage job postings.

- Applicant Module: Manage applicant profiles and apply for jobs.

- Job Module: Search and filter jobs by title, location, type, salary, experience, and deadline.

- Job Application Workflow: 
  - Apply for jobs (duplicate prevention)
  - Withdraw application 
  - Update application status (APPLIED, ACCEPTED, REJECTED, WITHDRAW)

- Data Handling: Custom exception handling, validation, and relational mappings with JPA/Hibernate.

- BaseModel: UUID-based IDs with automatic createdAt & updatedAt timestamp tracking.

## Tech Stack

- Language: Java
- Framework: Spring Boot

- Database: MySQL

- ORM: Hibernate / JPA

- Validation: Jakarta Validation (JSR 380)

- Other Tools: Lombok, Postman

## Project Structure
- src/main/java/com/prakshit/jobportalsystem

- controller  ----->      # REST Controllers 
- dto         ----->      # Request & Response DTOs 
- entity    ----->        # JPA Entities (User, Employer, ApplicantProfile, Job, JobApplication) 
- exceptions   ----->     # Custom Exceptions 
- mapper      ----->      # Entity <-> DTO Mappers 
- repository   ----->     # Spring Data JPA Repositories 
- service     ----->      # Service Layer Interfaces & Implementations

## API Endpoints

## User APIs

- POST /users – Create User

- GET /users/{id} – Get User by ID

- PUT /users/{id} – Update User

- DELETE /users/{id} – Delete User

    ## Employer APIs

- POST /employers → Register Employer

- GET /employers/{employerId} → Get Employer by ID

- GET /employers → Get All Employers

- PUT /employers/{employerId} → Update Employer

- DELETE /employers/{employerId} → Delete Employer

    ## Applicant Profile APIs

- POST /applicants → Create Applicant Profile

- GET /applicants/{applicantId} → Get Applicant by ID

- GET /applicants → Get All Applicants

- PUT /applicants/{applicantId} → Update Applicant Profile

- DELETE /applicants/{applicantId} → Delete Applicant Profile

    ## Job APIs

- POST /jobs → Post Job

- GET /jobs/{jobId} → Get Job by ID

- GET /jobs → Get All Jobs

- GET /jobs/search?title=&location=&type=&salary=&experience=&deadline= → Search/Filter Jobs

- PUT /jobs/{jobId} → Update Job

- DELETE /jobs/{jobId} → Delete Job

    ## Job Application APIs

- POST /jobApplications → Apply for Job

- GET /jobApplications/applicationId/{applicationId} → Get Application by Application ID

- GET /jobApplications/jobId/{jobId} → Get Applications by Job ID

- GET /jobApplications/applicantId/{applicantId} → Get Applications by Applicant ID

- GET /jobApplications → Get All Applications

- PATCH /jobApplications/applicationId/{applicationId}/status?status=ACCEPTED → Update Application Status

- DELETE /jobApplications/applicationId/{applicationId} → Withdraw Application

- GET /jobApplications/status?status=APPLIED → Get Applications by Status

- GET /jobApplications/jobId/{jobId}/status?status=REJECTED → Get Applications by Job ID & Status

- GET /jobApplications/applicantId/{applicantId}/status?status=WITHDRAW → Get Applications by Applicant ID & Status

## Testing

- Verified API functionality using Postman collections.

- Tested edge cases: duplicate applications, invalid IDs, and validation failures.
  
    ## How to Run

1. Clone the repository

- git clone https://github.com/your-username/job-portal-system.git
cd job-portal-system


2. Configure database in application.properties

- spring.datasource.url=jdbc:mysql://localhost:3306/job_portal
- spring.datasource.username=root
- spring.datasource.password=yourpassword
- spring.jpa.hibernate.ddl-auto=update


3. Run the application

- mvn spring-boot:run


4. Access APIs via Postman or browser at http://localhost:8080

