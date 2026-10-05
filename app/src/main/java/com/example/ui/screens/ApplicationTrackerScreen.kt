package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.JobApplicationEntity
import com.example.model.ApplicationStatus
import com.example.ui.theme.CoreAmberSecondary
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.TechBluePrimary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ApplicationTrackerScreen(
    applications: List<JobApplicationEntity>,
    onStatusChange: (String, ApplicationStatus, Long?, String?, String?) -> Unit,
    onDeleteApplication: (String) -> Unit,
    onAddCustomJob: (title: String, company: String, category: String, subCategory: String, location: String, salary: String, status: ApplicationStatus, notes: String, nextStep: String) -> Unit,
    onExploreJobs: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedStatusTab by remember { mutableStateOf<ApplicationStatus?>(null) } // null = All
    var showAddCustomDialog by remember { mutableStateOf(false) }
    var applicationToEditNotes by remember { mutableStateOf<JobApplicationEntity?>(null) }
    var applicationToDelete by remember { mutableStateOf<JobApplicationEntity?>(null) }

    val filteredApps = if (selectedStatusTab == null) {
        applications
    } else {
        applications.filter { it.status == selectedStatusTab?.name }
    }

    val appliedCount = applications.count { it.status == ApplicationStatus.APPLIED.name }
    val interviewingCount = applications.count { it.status == ApplicationStatus.INTERVIEWING.name }
    val offerCount = applications.count { it.status == ApplicationStatus.OFFER.name }
    val savedCount = applications.count { it.status == ApplicationStatus.SAVED.name }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddCustomDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(bottom = 60.dp)
                    .testTag("add_custom_job_fab")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Off-Campus Job")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Add Application", fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("application_tracker_list"),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            // Pipeline Metrics Header
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Engineering Career Pipeline",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Track your on-campus, off-campus, and referral applications in one place.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        PipelineStatCard(
                            label = "Applied",
                            count = appliedCount,
                            color = TechBluePrimary,
                            modifier = Modifier.weight(1f)
                        )
                        PipelineStatCard(
                            label = "Interviewing",
                            count = interviewingCount,
                            color = CoreAmberSecondary,
                            modifier = Modifier.weight(1f)
                        )
                        PipelineStatCard(
                            label = "Offers",
                            count = offerCount,
                            color = SuccessEmerald,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Status Filter Tabs
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ElevatedFilterChip(
                        selected = selectedStatusTab == null,
                        onClick = { selectedStatusTab = null },
                        label = { Text("All (${applications.size})") }
                    )
                    listOf(
                        ApplicationStatus.SAVED to savedCount,
                        ApplicationStatus.APPLIED to appliedCount,
                        ApplicationStatus.INTERVIEWING to interviewingCount,
                        ApplicationStatus.OFFER to offerCount,
                        ApplicationStatus.REJECTED to applications.count { it.status == ApplicationStatus.REJECTED.name }
                    ).forEach { (status, count) ->
                        FilterChip(
                            selected = selectedStatusTab == status,
                            onClick = { selectedStatusTab = status },
                            label = { Text("${status.displayName} ($count)") }
                        )
                    }
                }
            }

            // Applications List
            if (filteredApps.isEmpty()) {
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
                            Image(
                                painter = painterResource(id = R.drawable.engg_interview_prep_1791194946726),
                                contentDescription = "Interview Preparation Desk",
                                modifier = Modifier
                                    .size(130.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = if (selectedStatusTab != null) "No roles in ${selectedStatusTab?.displayName}" else "No tracked applications yet",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Explore Core & Software jobs to bookmark and apply, or add off-campus referrals manually!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(onClick = onExploreJobs) {
                                    Text("Explore Jobs")
                                }
                                OutlinedButton(onClick = { showAddCustomDialog = true }) {
                                    Text("+ Add Application")
                                }
                            }
                        }
                    }
                }
            } else {
                items(filteredApps, key = { it.id }) { app ->
                    TrackedJobCard(
                        app = app,
                        onStatusUpdate = { nextStatus ->
                            onStatusChange(app.id, nextStatus, app.interviewDateMillis, app.notes, app.nextStep)
                        },
                        onEditNotes = { applicationToEditNotes = app },
                        onDelete = { applicationToDelete = app },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }

    // Dialog for Adding Off-Campus / Custom Opportunity
    if (showAddCustomDialog) {
        AddCustomJobDialog(
            onDismiss = { showAddCustomDialog = false },
            onConfirm = { title, company, category, subCategory, location, salary, status, notes, nextStep ->
                onAddCustomJob(title, company, category, subCategory, location, salary, status, notes, nextStep)
                showAddCustomDialog = false
            }
        )
    }

    // Modal Sheet for Editing Notes & Next Steps
    applicationToEditNotes?.let { app ->
        EditNotesDialog(
            application = app,
            onDismiss = { applicationToEditNotes = null },
            onSave = { updatedNotes, updatedNextStep ->
                val currentStatus = try {
                    ApplicationStatus.valueOf(app.status)
                } catch (e: Exception) {
                    ApplicationStatus.APPLIED
                }
                onStatusChange(app.id, currentStatus, app.interviewDateMillis, updatedNotes, updatedNextStep)
                applicationToEditNotes = null
            }
        )
    }

    // Delete Confirmation Dialog
    applicationToDelete?.let { app ->
        AlertDialog(
            onDismissRequest = { applicationToDelete = null },
            title = { Text("Delete Application") },
            text = { Text("Are you sure you want to remove '${app.title} at ${app.company}' from your tracking pipeline?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteApplication(app.id)
                        applicationToDelete = null
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { applicationToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun PipelineStatCard(
    label: String,
    count: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = color
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun TrackedJobCard(
    app: JobApplicationEntity,
    onStatusUpdate: (ApplicationStatus) -> Unit,
    onEditNotes: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showStatusMenu by remember { mutableStateOf(false) }

    val statusColor = when (app.status) {
        ApplicationStatus.SAVED.name -> Color(0xFF64748B)
        ApplicationStatus.APPLIED.name -> TechBluePrimary
        ApplicationStatus.INTERVIEWING.name -> CoreAmberSecondary
        ApplicationStatus.OFFER.name -> SuccessEmerald
        ApplicationStatus.REJECTED.name -> Color(0xFFDC2626)
        else -> MaterialTheme.colorScheme.primary
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Status badge & Menu / Delete
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = statusColor.copy(alpha = 0.15f),
                        modifier = Modifier.clickable { showStatusMenu = true }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(statusColor)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = app.status,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = statusColor
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "▼", fontSize = 9.sp, color = statusColor)
                        }
                    }

                    DropdownMenu(
                        expanded = showStatusMenu,
                        onDismissRequest = { showStatusMenu = false }
                    ) {
                        listOf(
                            ApplicationStatus.SAVED,
                            ApplicationStatus.APPLIED,
                            ApplicationStatus.INTERVIEWING,
                            ApplicationStatus.OFFER,
                            ApplicationStatus.REJECTED
                        ).forEach { status ->
                            DropdownMenuItem(
                                text = { Text(status.displayName) },
                                onClick = {
                                    onStatusUpdate(status)
                                    showStatusMenu = false
                                }
                            )
                        }
                    }
                }

                Row {
                    IconButton(onClick = onEditNotes, modifier = Modifier.size(36.dp)) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Notes", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    IconButton(onClick = onDelete, modifier = Modifier.size(36.dp)) {
                        Icon(imageVector = Icons.Default.DeleteOutline, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Job Title and Company
            Text(
                text = app.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = app.company,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "• ${app.subCategory}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Location & Salary
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = app.location, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                if (app.salary.isNotEmpty()) {
                    Text(text = app.salary, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
            }

            // Next step or Notes block
            if (app.nextStep.isNotEmpty() || app.notes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        if (app.nextStep.isNotEmpty()) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.FactCheck, contentDescription = null, tint = CoreAmberSecondary, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Next: ${app.nextStep}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                        if (app.notes.isNotEmpty()) {
                            if (app.nextStep.isNotEmpty()) Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = app.notes,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 2
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Quick advance actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (app.status == ApplicationStatus.SAVED.name) {
                    Button(
                        onClick = { onStatusUpdate(ApplicationStatus.APPLIED) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Mark Applied")
                    }
                } else if (app.status == ApplicationStatus.APPLIED.name) {
                    Button(
                        onClick = { onStatusUpdate(ApplicationStatus.INTERVIEWING) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = CoreAmberSecondary),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Advance to Interview")
                    }
                } else if (app.status == ApplicationStatus.INTERVIEWING.name) {
                    Button(
                        onClick = { onStatusUpdate(ApplicationStatus.OFFER) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = SuccessEmerald),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Celebrate Offer! 🎉")
                    }
                }
            }
        }
    }
}

@Composable
private fun AddCustomJobDialog(
    onDismiss: () -> Unit,
    onConfirm: (title: String, company: String, category: String, subCategory: String, location: String, salary: String, status: ApplicationStatus, notes: String, nextStep: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("CORE") }
    var subCategory by remember { mutableStateOf("VLSI / Embedded") }
    var location by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(ApplicationStatus.APPLIED) }
    var notes by remember { mutableStateOf("") }
    var nextStep by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add External / Referral Job") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Job Title *") },
                    placeholder = { Text("e.g. Firmware Engineer") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = company,
                    onValueChange = { company = it },
                    label = { Text("Company Name *") },
                    placeholder = { Text("e.g. Intel, TI, Cisco...") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("Domain Category", style = MaterialTheme.typography.labelMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    FilterChip(
                        selected = category == "CORE",
                        onClick = { category = "CORE"; subCategory = "VLSI / Embedded" },
                        label = { Text("Core") }
                    )
                    FilterChip(
                        selected = category == "SOFTWARE",
                        onClick = { category = "SOFTWARE"; subCategory = "Backend / AI" },
                        label = { Text("Software") }
                    )
                    FilterChip(
                        selected = category == "HYBRID",
                        onClick = { category = "HYBRID"; subCategory = "Robotics / Firmware" },
                        label = { Text("Hybrid") }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location") },
                    placeholder = { Text("e.g. Bangalore, Remote") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = salary,
                    onValueChange = { salary = it },
                    label = { Text("Salary / Package") },
                    placeholder = { Text("e.g. ₹16 - ₹22 LPA") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = nextStep,
                    onValueChange = { nextStep = it },
                    label = { Text("Next Action / Round") },
                    placeholder = { Text("e.g. Aptitude test link, SystemVerilog round") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes / Referral Contact") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank() && company.isNotBlank()) {
                        onConfirm(title, company, category, subCategory, location.ifBlank { "Hybrid" }, salary, status, notes, nextStep)
                    }
                },
                enabled = title.isNotBlank() && company.isNotBlank()
            ) {
                Text("Add to Pipeline")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun EditNotesDialog(
    application: JobApplicationEntity,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var notes by remember { mutableStateOf(application.notes) }
    var nextStep by remember { mutableStateOf(application.nextStep) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Update Preparation Notes") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${application.title} • ${application.company}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = nextStep,
                    onValueChange = { nextStep = it },
                    label = { Text("Upcoming Round / Milestone") },
                    placeholder = { Text("e.g. Technical Round 2 on Verilog") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Private Interview Notes") },
                    placeholder = { Text("Topics to revise, interview feedback...") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4
                )
            }
        },
        confirmButton = {
            Button(onClick = { onSave(notes, nextStep) }) {
                Text("Save Notes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
