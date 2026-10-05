package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.JobApplicationEntity
import com.example.data.JobRepository
import com.example.data.UserProfileEntity
import com.example.model.ApplicationStatus
import com.example.model.ExperienceLevel
import com.example.model.JobCategory
import com.example.model.JobListing
import com.example.model.MockJobData
import com.example.model.WorkMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppNavTab(val title: String) {
    DISCOVER("Discover"),
    APPLICATIONS("Tracker"),
    INSIGHTS("Salaries"),
    PROFILE("My Profile")
}

data class FilterState(
    val query: String = "",
    val category: JobCategory = JobCategory.ALL,
    val subCategory: String? = null,
    val workMode: WorkMode = WorkMode.ALL,
    val experience: ExperienceLevel = ExperienceLevel.ALL,
    val minMatch: Int = 0
)

data class JobStats(
    val totalActiveJobs: Int = 0,
    val coreCount: Int = 0,
    val softwareCount: Int = 0,
    val hybridCount: Int = 0,
    val appliedCount: Int = 0,
    val interviewingCount: Int = 0,
    val offersCount: Int = 0,
    val averageMatchPercent: Int = 0
)

class JobFinderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: JobRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = JobRepository(db.jobDao())
        viewModelScope.launch {
            repository.ensureInitialData()
        }
    }

    val currentTab = MutableStateFlow(AppNavTab.DISCOVER)
    val selectedJobForDetail = MutableStateFlow<JobListing?>(null)

    val filterState = MutableStateFlow(FilterState())

    val userProfile: StateFlow<UserProfileEntity> = repository.userProfile
        .combine(MutableStateFlow(UserProfileEntity())) { profile, default ->
            profile ?: default
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserProfileEntity()
        )

    val applications: StateFlow<List<JobApplicationEntity>> = repository.allApplications
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Compute jobs with dynamic match score based on current user skills
    val scoredJobs: StateFlow<List<JobListing>> = combine(
        filterState,
        userProfile
    ) { filter, profile ->
        val skills = profile.getSkillsList()
        val allScored = MockJobData.sampleJobs.map { job ->
            job.withMatchScore(skills)
        }

        allScored.filter { job ->
            val matchesQuery = filter.query.isBlank() ||
                job.title.contains(filter.query, ignoreCase = true) ||
                job.company.contains(filter.query, ignoreCase = true) ||
                job.subCategory.contains(filter.query, ignoreCase = true) ||
                job.tags.any { it.contains(filter.query, ignoreCase = true) }

            val matchesCategory = when (filter.category) {
                JobCategory.ALL -> true
                JobCategory.CORE -> job.category == JobCategory.CORE
                JobCategory.SOFTWARE -> job.category == JobCategory.SOFTWARE
                JobCategory.HYBRID -> job.category == JobCategory.HYBRID
            }

            val matchesSubCategory = filter.subCategory == null ||
                job.subCategory.equals(filter.subCategory, ignoreCase = true)

            val matchesWorkMode = filter.workMode == WorkMode.ALL ||
                job.workMode == filter.workMode

            val matchesExp = filter.experience == ExperienceLevel.ALL ||
                job.experienceLevel == filter.experience

            val matchesScore = job.matchScore >= filter.minMatch

            matchesQuery && matchesCategory && matchesSubCategory && matchesWorkMode && matchesExp && matchesScore
        }.sortedByDescending { it.matchScore }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MockJobData.sampleJobs
    )

    val stats: StateFlow<JobStats> = combine(
        scoredJobs,
        applications
    ) { jobs, apps ->
        val allJobs = MockJobData.sampleJobs
        val core = allJobs.count { it.category == JobCategory.CORE }
        val sw = allJobs.count { it.category == JobCategory.SOFTWARE }
        val hyb = allJobs.count { it.category == JobCategory.HYBRID }
        val applied = apps.count { it.status == ApplicationStatus.APPLIED.name }
        val interviewing = apps.count { it.status == ApplicationStatus.INTERVIEWING.name }
        val offers = apps.count { it.status == ApplicationStatus.OFFER.name }
        val avgMatch = if (jobs.isNotEmpty()) jobs.map { it.matchScore }.average().toInt() else 0

        JobStats(
            totalActiveJobs = allJobs.size,
            coreCount = core,
            softwareCount = sw,
            hybridCount = hyb,
            appliedCount = applied,
            interviewingCount = interviewing,
            offersCount = offers,
            averageMatchPercent = avgMatch
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = JobStats()
    )

    fun setTab(tab: AppNavTab) {
        currentTab.value = tab
    }

    fun setQuery(query: String) {
        filterState.value = filterState.value.copy(query = query)
    }

    fun setCategory(category: JobCategory) {
        filterState.value = filterState.value.copy(category = category, subCategory = null)
    }

    fun setSubCategory(sub: String?) {
        filterState.value = filterState.value.copy(subCategory = sub)
    }

    fun setWorkMode(mode: WorkMode) {
        filterState.value = filterState.value.copy(workMode = mode)
    }

    fun setExperience(exp: ExperienceLevel) {
        filterState.value = filterState.value.copy(experience = exp)
    }

    fun resetFilters() {
        filterState.value = FilterState()
    }

    fun openJobDetail(job: JobListing) {
        // Refresh with current profile skills score
        val currentSkills = userProfile.value.getSkillsList()
        selectedJobForDetail.value = job.withMatchScore(currentSkills)
    }

    fun closeJobDetail() {
        selectedJobForDetail.value = null
    }

    fun toggleBookmark(job: JobListing, isCurrentlyBookmarked: Boolean) {
        viewModelScope.launch {
            repository.toggleBookmark(job, isCurrentlyBookmarked)
        }
    }

    fun applyToJob(job: JobListing, notes: String = "", nextStep: String = "") {
        viewModelScope.launch {
            repository.applyToJob(job, notes, nextStep)
            // also update currently opened detail if matches
            if (selectedJobForDetail.value?.id == job.id) {
                // detail remains open so user can see updated status
            }
        }
    }

    fun updateApplicationStatus(
        applicationId: String,
        newStatus: ApplicationStatus,
        interviewDateMillis: Long? = null,
        notes: String? = null,
        nextStep: String? = null
    ) {
        viewModelScope.launch {
            repository.updateStatus(applicationId, newStatus, interviewDateMillis, notes, nextStep)
        }
    }

    fun addCustomJob(
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
        viewModelScope.launch {
            repository.addCustomJob(
                title = title,
                company = company,
                category = category,
                subCategory = subCategory,
                location = location,
                salary = salary,
                status = status,
                notes = notes,
                nextStep = nextStep
            )
        }
    }

    fun deleteApplication(id: String) {
        viewModelScope.launch {
            repository.deleteApplication(id)
        }
    }

    fun addProfileSkill(newSkill: String) {
        val trimmed = newSkill.trim()
        if (trimmed.isEmpty()) return
        val currentSkills = userProfile.value.getSkillsList().toMutableList()
        if (!currentSkills.any { it.equals(trimmed, ignoreCase = true) }) {
            currentSkills.add(trimmed)
            val updatedProfile = userProfile.value.copy(
                skillsCommaSeparated = currentSkills.joinToString(",")
            )
            viewModelScope.launch {
                repository.updateUserProfile(updatedProfile)
            }
        }
    }

    fun removeProfileSkill(skillToRemove: String) {
        val currentSkills = userProfile.value.getSkillsList().filterNot { it.equals(skillToRemove, ignoreCase = true) }
        val updatedProfile = userProfile.value.copy(
            skillsCommaSeparated = currentSkills.joinToString(",")
        )
        viewModelScope.launch {
            repository.updateUserProfile(updatedProfile)
        }
    }

    fun updateProfile(
        fullName: String,
        discipline: String,
        degree: String,
        gradYear: String,
        workMode: String,
        targetSalary: String,
        summary: String
    ) {
        val updated = userProfile.value.copy(
            fullName = fullName,
            discipline = discipline,
            degree = degree,
            graduationYear = gradYear,
            preferredWorkMode = workMode,
            targetSalary = targetSalary,
            resumeSummary = summary
        )
        viewModelScope.launch {
            repository.updateUserProfile(updated)
        }
    }
}
