package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SettingsSuggest
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.JobApplicationEntity
import com.example.model.ExperienceLevel
import com.example.model.JobCategory
import com.example.model.JobListing
import com.example.model.WorkMode
import com.example.ui.FilterState
import com.example.ui.JobStats
import com.example.ui.components.JobCard
import com.example.ui.theme.CoreAmberSecondary
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.TechBluePrimary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DiscoverScreen(
    jobs: List<JobListing>,
    stats: JobStats,
    filterState: FilterState,
    applications: List<JobApplicationEntity>,
    onQueryChange: (String) -> Unit,
    onCategoryChange: (JobCategory) -> Unit,
    onSubCategoryChange: (String?) -> Unit,
    onWorkModeChange: (WorkMode) -> Unit,
    onExperienceChange: (ExperienceLevel) -> Unit,
    onResetFilters: () -> Unit,
    onJobClick: (JobListing) -> Unit,
    onBookmarkToggle: (JobListing, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var showFilterSheet by remember { mutableStateOf(false) }

    val coreSubCategories = listOf(
        "VLSI & Semiconductor",
        "Embedded Systems",
        "Mechatronics & Robotics",
        "Mechanical & Aerospace",
        "Electrical & Power",
        "Biomedical Engineering"
    )

    val softwareSubCategories = listOf(
        "AI & Machine Learning",
        "Backend & Systems",
        "Mobile & Frontend",
        "Cloud & DevOps",
        "Cybersecurity & Systems"
    )

    val availableSubCategories = when (filterState.category) {
        JobCategory.CORE -> coreSubCategories
        JobCategory.SOFTWARE -> softwareSubCategories
        JobCategory.HYBRID -> listOf("Robotics & Automation", "Autonomous Vehicles")
        JobCategory.ALL -> coreSubCategories + softwareSubCategories
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("discover_screen_list"),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Hero Visual Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.engg_career_hero_1791194933015),
                        contentDescription = "Core & Software Engineering Careers",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Gradient overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color(0xDD0B1120), Color(0xFA0B1120))
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = TechBluePrimary.copy(alpha = 0.25f)
                        ) {
                            Text(
                                text = "CORE & SOFTWARE JOB MATRIX",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Find High-Impact Engineering Roles",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Stats pills
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.White.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "⚙ ${stats.coreCount} Core Roles",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = CoreAmberSecondary,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.White.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "💻 ${stats.softwareCount} Tech Roles",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TechBluePrimary,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.White.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "⚡ ${stats.averageMatchPercent}% Avg Match",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = SuccessEmerald,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Search Bar & Filter Button
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = filterState.query,
                    onValueChange = onQueryChange,
                    placeholder = { Text("Search title, company, or skills (Verilog, C++, AI)...") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    },
                    trailingIcon = {
                        if (filterState.query.isNotEmpty()) {
                            IconButton(onClick = { onQueryChange("") }) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("search_jobs_input")
                )

                Spacer(modifier = Modifier.width(8.dp))

                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = if (filterState.workMode != WorkMode.ALL || filterState.experience != ExperienceLevel.ALL) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.size(52.dp)
                ) {
                    IconButton(
                        onClick = { showFilterSheet = true },
                        modifier = Modifier.testTag("filter_options_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = "Filters",
                            tint = if (filterState.workMode != WorkMode.ALL || filterState.experience != ExperienceLevel.ALL) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Domain Categories Row (All Domains, Core, Software, Hybrid)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                JobCategory.values().forEach { category ->
                    val isSelected = filterState.category == category
                    ElevatedFilterChip(
                        selected = isSelected,
                        onClick = { onCategoryChange(category) },
                        label = {
                            Text(
                                text = when (category) {
                                    JobCategory.ALL -> "🌐 All Domains (${stats.totalActiveJobs})"
                                    JobCategory.CORE -> "⚙ Core Engg (${stats.coreCount})"
                                    JobCategory.SOFTWARE -> "💻 Software & Tech (${stats.softwareCount})"
                                    JobCategory.HYBRID -> "🤖 Robotics & Firmware (${stats.hybridCount})"
                                }
                            )
                        },
                        colors = FilterChipDefaults.elevatedFilterChipColors(
                            selectedContainerColor = when (category) {
                                JobCategory.CORE -> CoreAmberSecondary
                                JobCategory.SOFTWARE -> TechBluePrimary
                                JobCategory.HYBRID -> Color(0xFF10B981)
                                JobCategory.ALL -> MaterialTheme.colorScheme.primary
                            },
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.testTag("category_chip_${category.name}")
                    )
                }
            }
        }

        // Subcategory horizontal chips
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FilterChip(
                    selected = filterState.subCategory == null,
                    onClick = { onSubCategoryChange(null) },
                    label = { Text("All Disciplines") }
                )
                availableSubCategories.distinct().forEach { sub ->
                    FilterChip(
                        selected = filterState.subCategory == sub,
                        onClick = { onSubCategoryChange(if (filterState.subCategory == sub) null else sub) },
                        label = { Text(sub) }
                    )
                }
            }
        }

        // Results summary line
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${jobs.size} Opportunities Found",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (filterState.query.isNotBlank() || filterState.subCategory != null || filterState.workMode != WorkMode.ALL || filterState.experience != ExperienceLevel.ALL) {
                    Text(
                        text = "Reset Filters",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.testTag("reset_filters_button")
                    )
                }
            }
        }

        // Job Cards or Empty State
        if (jobs.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "🔍", fontSize = 42.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No engineering roles match your criteria",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Try adjusting your domain filters, keywords, or experience level to explore more jobs.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onResetFilters) {
                            Text("Reset All Filters")
                        }
                    }
                }
            }
        } else {
            items(jobs, key = { it.id }) { job ->
                val app = applications.find { it.jobId == job.id }
                val isBookmarked = app?.isBookmarked == true

                JobCard(
                    job = job,
                    isBookmarked = isBookmarked,
                    onJobClick = { onJobClick(job) },
                    onBookmarkToggle = { onBookmarkToggle(job, isBookmarked) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }
    }

    // Filter Bottom Sheet for Work Mode & Experience Level
    if (showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = { showFilterSheet = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Refine Engineering Search",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Work Arrangement",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WorkMode.values().forEach { mode ->
                        FilterChip(
                            selected = filterState.workMode == mode,
                            onClick = { onWorkModeChange(mode) },
                            label = { Text(mode.displayName) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Experience Level",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ExperienceLevel.values().forEach { exp ->
                        FilterChip(
                            selected = filterState.experience == exp,
                            onClick = { onExperienceChange(exp) },
                            label = { Text(exp.displayName) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            onResetFilters()
                            showFilterSheet = false
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors()
                    ) {
                        Text("Reset")
                    }

                    Button(
                        onClick = { showFilterSheet = false },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Apply Filters")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
