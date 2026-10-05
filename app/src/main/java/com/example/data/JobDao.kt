package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Query("SELECT * FROM job_applications ORDER BY updatedAt DESC")
    fun getAllApplications(): Flow<List<JobApplicationEntity>>

    @Query("SELECT * FROM job_applications WHERE id = :id LIMIT 1")
    fun getApplicationById(id: String): Flow<JobApplicationEntity?>

    @Query("SELECT * FROM job_applications WHERE isBookmarked = 1 ORDER BY updatedAt DESC")
    fun getBookmarkedApplications(): Flow<List<JobApplicationEntity>>

    @Query("SELECT * FROM job_applications WHERE status = :status ORDER BY updatedAt DESC")
    fun getApplicationsByStatus(status: String): Flow<List<JobApplicationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateApplication(application: JobApplicationEntity)

    @Delete
    suspend fun deleteApplication(application: JobApplicationEntity)

    @Query("DELETE FROM job_applications WHERE id = :id")
    suspend fun deleteApplicationById(id: String)

    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUserProfile(profile: UserProfileEntity)
}
