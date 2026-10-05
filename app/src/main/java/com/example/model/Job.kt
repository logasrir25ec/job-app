package com.example.model

enum class JobCategory(val displayName: String) {
    ALL("All Domains"),
    CORE("Core Engineering"),
    SOFTWARE("Software & Tech"),
    HYBRID("Robotics / Embedded Tech")
}

enum class WorkMode(val displayName: String) {
    ALL("All Modes"),
    REMOTE("Remote"),
    HYBRID("Hybrid"),
    ON_SITE("On-site")
}

enum class ExperienceLevel(val displayName: String) {
    ALL("All Levels"),
    INTERN("Intern / Campus"),
    ENTRY("Entry Level (0-2 yrs)"),
    MID("Mid-Level (3-5 yrs)"),
    SENIOR("Senior (5+ yrs)"),
    LEAD("Lead / Architect")
}

enum class ApplicationStatus(val displayName: String, val badgeColorHex: Long) {
    SAVED("Saved", 0xFF64748B),
    APPLIED("Applied", 0xFF0284C7),
    INTERVIEWING("Interviewing", 0xFFD97706),
    OFFER("Offer Received", 0xFF059669),
    REJECTED("Not Selected", 0xFFDC2626),
    ARCHIVED("Archived", 0xFF475569)
}

data class JobListing(
    val id: String,
    val title: String,
    val company: String,
    val category: JobCategory,
    val subCategory: String, // e.g. "VLSI & Chips", "Embedded Systems", "Robotics", "AI/ML Systems", "Distributed Backend"
    val location: String,
    val workMode: WorkMode,
    val experienceLevel: ExperienceLevel,
    val salaryRange: String,
    val tags: List<String>,
    val description: String,
    val responsibilities: List<String>,
    val requirements: List<String>,
    val preferredSkills: List<String>,
    val benefits: List<String>,
    val deadline: String,
    val postedAgo: String,
    val isFeatured: Boolean = false,
    val companyLogoInitial: String = company.take(2).uppercase(),
    val matchScore: Int = 0,
    val matchingSkills: List<String> = emptyList(),
    val missingSkills: List<String> = emptyList()
) {
    fun withMatchScore(userSkills: List<String>): JobListing {
        if (userSkills.isEmpty()) {
            return this.copy(matchScore = 0, matchingSkills = emptyList(), missingSkills = requirements)
        }
        val normalizedUserSkills = userSkills.map { it.trim().lowercase() }
        val matched = mutableListOf<String>()
        val missing = mutableListOf<String>()

        val allReqs = (requirements + tags).distinct()
        for (req in allReqs) {
            val reqLower = req.lowercase()
            val hasMatch = normalizedUserSkills.any { userSkill ->
                reqLower.contains(userSkill) || userSkill.contains(reqLower)
            }
            if (hasMatch) {
                matched.add(req)
            } else {
                missing.add(req)
            }
        }

        val total = allReqs.size.coerceAtLeast(1)
        val score = ((matched.size.toFloat() / total.toFloat()) * 100).toInt().coerceIn(15, 98)

        return this.copy(
            matchScore = score,
            matchingSkills = matched,
            missingSkills = missing
        )
    }
}
