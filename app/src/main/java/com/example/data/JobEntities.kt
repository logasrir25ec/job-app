package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_applications")
data class JobApplicationEntity(
    @PrimaryKey
    val id: String, // Job ID or generated UUID for custom added job
    val jobId: String,
    val title: String,
    val company: String,
    val category: String,
    val subCategory: String,
    val location: String,
    val salary: String,
    val status: String, // ApplicationStatus name: SAVED, APPLIED, INTERVIEWING, OFFER, REJECTED, ARCHIVED
    val appliedDateMillis: Long = System.currentTimeMillis(),
    val interviewDateMillis: Long? = null,
    val notes: String = "",
    val nextStep: String = "",
    val isBookmarked: Boolean = false,
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val fullName: String = "Logasri R",
    val discipline: String = "Electronics & Computer Engineering (Dual Core + Tech)",
    val degree: String = "B.E. Electronics & Communication",
    val graduationYear: String = "2026",
    val preferredWorkMode: String = "Hybrid",
    val skillsCommaSeparated: String = "SystemVerilog,Embedded C,C++,Python,RTOS,Verilog,Jetpack Compose,Kotlin,Git",
    val resumeSummary: String = "Engineering student passionate about both Core Silicon / Embedded systems and modern Software platforms. Experienced in RTL simulation, microcontroller firmware, and reactive Android apps.",
    val targetSalary: String = "₹18 - ₹28 LPA ($90k+)"
) {
    fun getSkillsList(): List<String> {
        return skillsCommaSeparated.split(",").map { it.trim() }.filter { it.isNotEmpty() }
    }
}
