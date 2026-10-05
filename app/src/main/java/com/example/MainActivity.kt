package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.AssignmentTurnedIn
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.ApplicationStatus
import com.example.model.JobCategory
import com.example.ui.AppNavTab
import com.example.ui.JobFinderViewModel
import com.example.ui.components.JobDetailSheet
import com.example.ui.screens.ApplicationTrackerScreen
import com.example.ui.screens.CareerInsightsScreen
import com.example.ui.screens.DiscoverScreen
import com.example.ui.screens.ProfileSkillsScreen
import com.example.ui.theme.CoreAmberSecondary
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.TechBluePrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent(viewModel: JobFinderViewModel = viewModel()) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val scoredJobs by viewModel.scoredJobs.collectAsStateWithLifecycle()
    val stats by viewModel.stats.collectAsStateWithLifecycle()
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()
    val applications by viewModel.applications.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val selectedJobForDetail by viewModel.selectedJobForDetail.collectAsStateWithLifecycle()

    // Handle Back Press gracefully
    BackHandler(enabled = selectedJobForDetail != null || currentTab != AppNavTab.DISCOVER) {
        if (selectedJobForDetail != null) {
            viewModel.closeJobDetail()
        } else if (currentTab != AppNavTab.DISCOVER) {
            viewModel.setTab(AppNavTab.DISCOVER)
        }
    }

    if (selectedJobForDetail != null) {
        val currentJob = selectedJobForDetail!!
        val app = applications.find { it.jobId == currentJob.id }
        JobDetailSheet(
            job = currentJob,
            application = app,
            onDismiss = { viewModel.closeJobDetail() },
            onBookmarkToggle = { viewModel.toggleBookmark(currentJob, app?.isBookmarked == true) },
            onApply = { notes, nextStep -> viewModel.applyToJob(currentJob, notes, nextStep) },
            onStatusChange = { newStatus, notes, interviewDate ->
                viewModel.updateApplicationStatus(currentJob.id, newStatus, interviewDate, notes)
            }
        )
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "EnggJobs",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 6.dp,
                    modifier = Modifier.testTag("main_navigation_bar")
                ) {
                    AppNavTab.values().forEach { tab ->
                        val isSelected = currentTab == tab
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { viewModel.setTab(tab) },
                            icon = {
                                when (tab) {
                                    AppNavTab.DISCOVER -> Icon(
                                        imageVector = if (isSelected) Icons.Filled.Explore else Icons.Outlined.Explore,
                                        contentDescription = tab.title
                                    )
                                    AppNavTab.APPLICATIONS -> {
                                        val activeCount = applications.count {
                                            it.status == ApplicationStatus.APPLIED.name ||
                                            it.status == ApplicationStatus.INTERVIEWING.name ||
                                            it.status == ApplicationStatus.OFFER.name
                                        }
                                        if (activeCount > 0) {
                                            BadgedBox(
                                                badge = {
                                                    Badge { Text(activeCount.toString()) }
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = if (isSelected) Icons.Filled.AssignmentTurnedIn else Icons.Outlined.AssignmentTurnedIn,
                                                    contentDescription = tab.title
                                                )
                                            }
                                        } else {
                                            Icon(
                                                imageVector = if (isSelected) Icons.Filled.AssignmentTurnedIn else Icons.Outlined.AssignmentTurnedIn,
                                                contentDescription = tab.title
                                            )
                                        }
                                    }
                                    AppNavTab.INSIGHTS -> Icon(
                                        imageVector = if (isSelected) Icons.Filled.TrendingUp else Icons.Outlined.TrendingUp,
                                        contentDescription = tab.title
                                    )
                                    AppNavTab.PROFILE -> Icon(
                                        imageVector = if (isSelected) Icons.Filled.Person else Icons.Outlined.Person,
                                        contentDescription = tab.title
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = tab.title,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Crossfade(targetState = currentTab, label = "tab_transition") { tab ->
                    when (tab) {
                        AppNavTab.DISCOVER -> DiscoverScreen(
                            jobs = scoredJobs,
                            stats = stats,
                            filterState = filterState,
                            applications = applications,
                            onQueryChange = { viewModel.setQuery(it) },
                            onCategoryChange = { viewModel.setCategory(it) },
                            onSubCategoryChange = { viewModel.setSubCategory(it) },
                            onWorkModeChange = { viewModel.setWorkMode(it) },
                            onExperienceChange = { viewModel.setExperience(it) },
                            onResetFilters = { viewModel.resetFilters() },
                            onJobClick = { viewModel.openJobDetail(it) },
                            onBookmarkToggle = { job, isBookmarked -> viewModel.toggleBookmark(job, isBookmarked) }
                        )

                        AppNavTab.APPLICATIONS -> ApplicationTrackerScreen(
                            applications = applications,
                            onStatusChange = { id, status, date, notes, nextStep ->
                                viewModel.updateApplicationStatus(id, status, date, notes, nextStep)
                            },
                            onDeleteApplication = { viewModel.deleteApplication(it) },
                            onAddCustomJob = { title, company, category, subCategory, loc, salary, status, notes, nextStep ->
                                viewModel.addCustomJob(title, company, category, subCategory, loc, salary, status, notes, nextStep)
                            },
                            onExploreJobs = { viewModel.setTab(AppNavTab.DISCOVER) }
                        )

                        AppNavTab.INSIGHTS -> CareerInsightsScreen()

                        AppNavTab.PROFILE -> ProfileSkillsScreen(
                            profile = userProfile,
                            onAddSkill = { viewModel.addProfileSkill(it) },
                            onRemoveSkill = { viewModel.removeProfileSkill(it) },
                            onUpdateProfile = { name, disc, deg, yr, mode, sal, sum ->
                                viewModel.updateProfile(name, disc, deg, yr, mode, sal, sum)
                            }
                        )
                    }
                }
            }
        }
    }
}
