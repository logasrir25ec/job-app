package com.example.data

import com.example.model.ApplicationStatus
import com.example.model.JobListing
import com.example.model.MockJobData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import java.util.UUID

class JobRepository(private val jobDao: JobDao) {

    val allApplications: Flow<List<JobApplicationEntity>> = jobDao.getAllApplications()
    val bookmarkedApplications: Flow<List<JobApplicationEntity>> = jobDao.getBookmarkedApplications()
    val userProfile: Flow<UserProfileEntity?> = jobDao.getUserProfile()

    suspend fun ensureInitialData() {
        val existingProfile = jobDao.getUserProfile().firstOrNull()
        if (existingProfile == null) {
            val defaultProfile = UserProfileEntity()
            jobDao.insertOrUpdateUserProfile(defaultProfile)
        }

        val existingApps = jobDao.getAllApplications().firstOrNull()
        if (existingApps.isNullOrEmpty()) {
            // Seed a couple of realistic starter tracked items
            val starterApps = listOf(
                JobApplicationEntity(
                    id = "core-vlsi-01",
                    jobId = "core-vlsi-01",
                    title = "ASIC Digital Verification Engineer",
                    company = "Qualcomm",
                    category = "CORE",
                    subCategory = "VLSI & Semiconductor",
                    location = "Bangalore, India",
                    salary = "₹18 - ₹26 LPA",
                    status = ApplicationStatus.INTERVIEWING.name,
                    appliedDateMillis = System.currentTimeMillis() - 86400000L * 4,
                    interviewDateMillis = System.currentTimeMillis() + 86400000L * 3,
                    notes = "Passed online aptitude & SystemVerilog screening. Next round: RTL simulation & UVM architecture on MS Teams.",
                    nextStep = "Revise UVM sequence mechanisms and constrained-random testbench debugging.",
                    isBookmarked = true
                ),
                JobApplicationEntity(
                    id = "sw-ai-01",
                    jobId = "sw-ai-01",
                    title = "Machine Learning Infrastructure Engineer",
                    company = "Anthropic",
                    category = "SOFTWARE",
                    subCategory = "AI & Machine Learning",
                    location = "San Francisco, CA / Remote",
                    salary = "$220,000 - $310,000",
                    status = ApplicationStatus.APPLIED.name,
                    appliedDateMillis = System.currentTimeMillis() - 86400000L * 2,
                    notes = "Submitted custom resume highlighting PyTorch distributed tensors and C++ CUDA experience.",
                    nextStep = "Await technical phone screen recruiter invite.",
                    isBookmarked = true
                ),
                JobApplicationEntity(
                    id = "hyb-rob-01",
                    jobId = "hyb-rob-01",
                    title = "Robotics Software & Motion Planning Engineer",
                    company = "Boston Dynamics",
                    category = "HYBRID",
                    subCategory = "Robotics & Automation",
                    location = "Waltham, MA",
                    salary = "$140,000 - $185,000",
                    status = ApplicationStatus.SAVED.name,
                    appliedDateMillis = System.currentTimeMillis() - 86400000L * 1,
                    notes = "Fascinating quadruped trajectory optimization role. Tailoring cover letter today.",
                    nextStep = "Finalize ROS2 sample portfolio demo.",
                    isBookmarked = true
                )
            )
            for (app in starterApps) {
                jobDao.insertOrUpdateApplication(app)
            }
        }
    }

    suspend fun saveApplication(application: JobApplicationEntity) {
        jobDao.insertOrUpdateApplication(application.copy(updatedAt = System.currentTimeMillis()))
    }

    suspend fun toggleBookmark(job: JobListing, isCurrentlyBookmarked: Boolean) {
        val existing = jobDao.getApplicationById(job.id).firstOrNull()
        if (existing != null) {
            val updated = existing.copy(
                isBookmarked = !isCurrentlyBookmarked,
                updatedAt = System.currentTimeMillis()
            )
            jobDao.insertOrUpdateApplication(updated)
        } else {
            val newApp = JobApplicationEntity(
                id = job.id,
                jobId = job.id,
                title = job.title,
                company = job.company,
                category = job.category.name,
                subCategory = job.subCategory,
                location = job.location,
                salary = job.salaryRange,
                status = ApplicationStatus.SAVED.name,
                isBookmarked = true,
                updatedAt = System.currentTimeMillis()
            )
            jobDao.insertOrUpdateApplication(newApp)
        }
    }

    suspend fun applyToJob(job: JobListing, notes: String = "", nextStep: String = "") {
        val existing = jobDao.getApplicationById(job.id).firstOrNull()
        val app = JobApplicationEntity(
            id = job.id,
            jobId = job.id,
            title = job.title,
            company = job.company,
            category = job.category.name,
            subCategory = job.subCategory,
            location = job.location,
            salary = job.salaryRange,
            status = ApplicationStatus.APPLIED.name,
            appliedDateMillis = System.currentTimeMillis(),
            notes = notes.ifEmpty { existing?.notes ?: "Application submitted via EnggJobs." },
            nextStep = nextStep.ifEmpty { existing?.nextStep ?: "Prepare for technical round 1." },
            isBookmarked = existing?.isBookmarked ?: true,
            updatedAt = System.currentTimeMillis()
        )
        jobDao.insertOrUpdateApplication(app)
    }

    suspend fun updateStatus(
        applicationId: String,
        newStatus: ApplicationStatus,
        interviewDateMillis: Long? = null,
        notes: String? = null,
        nextStep: String? = null
    ) {
        val existing = jobDao.getApplicationById(applicationId).firstOrNull() ?: return
        val updated = existing.copy(
            status = newStatus.name,
            interviewDateMillis = interviewDateMillis ?: existing.interviewDateMillis,
            notes = notes ?: existing.notes,
            nextStep = nextStep ?: existing.nextStep,
            updatedAt = System.currentTimeMillis()
        )
        jobDao.insertOrUpdateApplication(updated)
    }

    suspend fun addCustomJob(
        title: String,
        company: String,
        category: String,
        subCategory: String,
        location: String,
        salary: String,
        status: ApplicationStatus,
        notes: String,
        nextStep: String
    ) {
        val customId = "custom-" + UUID.randomUUID().toString().take(8)
        val app = JobApplicationEntity(
            id = customId,
            jobId = customId,
            title = title,
            company = company,
            category = category,
            subCategory = subCategory,
            location = location,
            salary = salary,
            status = status.name,
            appliedDateMillis = System.currentTimeMillis(),
            notes = notes,
            nextStep = nextStep,
            isBookmarked = true,
            updatedAt = System.currentTimeMillis()
        )
        jobDao.insertOrUpdateApplication(app)
    }

    suspend fun deleteApplication(id: String) {
        jobDao.deleteApplicationById(id)
    }

    suspend fun updateUserProfile(profile: UserProfileEntity) {
        jobDao.insertOrUpdateUserProfile(profile)
    }
}
