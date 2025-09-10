package com.prakshit.jobportalsystem.repository;

import com.prakshit.jobportalsystem.entity.Job;
import com.prakshit.jobportalsystem.entity.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {

    //to find all jobs from employer
    List<Job> findByEmployerProfile_Id(UUID employerId);       //employerProfile → field in Job,id → field inside EmployerProfile,Together: findByEmployerProfile_Id(...).

    boolean  existsByEmployerProfile_Id(UUID employerId);

    // to find job by cityName by ignoring case
    List<Job> findByLocationIgnoreCase(String location);

    //to find jobs FULL_TIME,PART_TIME,INTERNSHIP
    List<Job> findByJobType(JobType jobType);

    // to find jobs where salary is greater than
    List<Job> findBySalaryGreaterThanEqual(BigDecimal salary);

    //to find jobs after the given date= 2025-09-10, this will fetch all jobs  grater than 2025-09-10
    List<Job> findByLastDateToApplyAfter(LocalDateTime lastDate);

    //to find jobs in between experience range
    List<Job> findByMinExperienceGreaterThanEqualAndMaxExperienceLessThanEqual(int minExp, int maxExp);

    // to find jobs by title,it will also search partial input,ex."developer" will match "java developer"
    @Query("SELECT j FROM Job j WHERE LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Job> searchByJobTitle(String keyword);                     // LIKE LOWER(CONCAT('%', :keyword, '%')) → match any job title that contains the keyword (case-insensitive).


    //to find jobs by skills it will also find partial input skills, if input ,"java" it will match ["Java", "Spring Boot", "SQL"]
    @Query("SELECT j FROM Job j JOIN j.skillsRequired s WHERE LOWER(s) LIKE LOWER(CONCAT('%', :skill, '%'))")
    List<Job> searchBySkill(String skill);
                                                                                //JOIN j.skillsRequired s → Joins the skillsRequired collection (since it’s stored in a separate table job_skills).
                                                                                //LIKE LOWER(CONCAT('%', :skill, '%')) → match skill partially (case-insensitive)

}
