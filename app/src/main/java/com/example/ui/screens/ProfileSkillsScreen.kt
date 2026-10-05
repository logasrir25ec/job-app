package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.UserProfileEntity
import com.example.model.MockJobData
import com.example.ui.theme.CoreAmberSecondary
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.TechBluePrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileSkillsScreen(
    profile: UserProfileEntity,
    onAddSkill: (String) -> Unit,
    onRemoveSkill: (String) -> Unit,
    onUpdateProfile: (name: String, discipline: String, degree: String, gradYear: String, workMode: String, targetSalary: String, summary: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showEditProfileDialog by remember { mutableStateOf(false) }
    var newSkillInput by remember { mutableStateOf("") }

    val userSkills = profile.getSkillsList()

    val coreHardwareSuggestions = listOf(
        "SystemVerilog", "Verilog", "UVM", "Cadence Virtuoso", "SPICE", "FPGA",
        "Embedded C", "RTOS", "AUTOSAR", "CAN Bus", "PCB Design", "Altium"
    )

    val mechanicalRoboticsSuggestions = listOf(
        "SolidWorks", "CATIA", "FEA Analysis", "ANSYS", "MATLAB", "Simulink",
        "ROS2", "Robotics", "Control Systems", "Kinematics"
    )

    val softwareCloudSuggestions = listOf(
        "Python", "C++", "Java", "Go", "Rust", "Kotlin", "Jetpack Compose",
        "PyTorch", "CUDA", "Distributed Systems", "Docker", "Kubernetes", "AWS", "Linux"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("profile_skills_list"),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 80.dp)
    ) {
        // Profile Info Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.linearGradient(
                                            listOf(TechBluePrimary, CoreAmberSecondary)
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = profile.fullName.take(2).uppercase(),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = profile.fullName,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = profile.discipline,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        IconButton(
                            onClick = { showEditProfileDialog = true },
                            modifier = Modifier.testTag("edit_profile_button")
                        ) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Profile", tint = MaterialTheme.colorScheme.primary)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Degree: ${profile.degree}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
                                Text(text = "Grad: ${profile.graduationYear}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Mode: ${profile.preferredWorkMode}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(text = "Target: ${profile.targetSalary}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = SuccessEmerald)
                            }
                        }
                    }

                    if (profile.resumeSummary.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Career Elevator Pitch", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = profile.resumeSummary,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(18.dp)) }

        // Skills Management Header
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Your Engineering Skillset",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${userSkills.size} skills configured • Powers job match %",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = SuccessEmerald.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = "Live Match Engine",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = SuccessEmerald,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Active Skills Chips
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        userSkills.forEach { skill ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                                modifier = Modifier.clip(RoundedCornerShape(8.dp))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = skill,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Remove $skill",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .clip(CircleShape)
                                            .clickable { onRemoveSkill(skill) }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Add Custom Skill Input
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = newSkillInput,
                            onValueChange = { newSkillInput = it },
                            placeholder = { Text("Add skill (e.g. FreeRTOS, PyTorch, C++)...") },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("custom_skill_input")
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (newSkillInput.isNotBlank()) {
                                    onAddSkill(newSkillInput)
                                    newSkillInput = ""
                                }
                            },
                            enabled = newSkillInput.isNotBlank(),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("add_custom_skill_button")
                        ) {
                            Text("Add")
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Quick Add Suggested Skills by Domains
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "1-Tap Skill Suggestions",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Tap to add skills demanded by top employers to boost your compatibility score.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Group 1: Core Silicon & Embedded
                    Text(text = "⚙ Core Silicon & Embedded Systems", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = CoreAmberSecondary)
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        coreHardwareSuggestions.forEach { skill ->
                            val alreadyHas = userSkills.any { it.equals(skill, ignoreCase = true) }
                            FilterChip(
                                selected = alreadyHas,
                                onClick = {
                                    if (alreadyHas) onRemoveSkill(skill) else onAddSkill(skill)
                                },
                                label = { Text(if (alreadyHas) "✓ $skill" else "+ $skill") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = CoreAmberSecondary.copy(alpha = 0.2f),
                                    selectedLabelColor = CoreAmberSecondary
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Group 2: Mechanical, Mechatronics & Robotics
                    Text(text = "🤖 Robotics, Dynamics & Mechanical", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = SuccessEmerald)
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        mechanicalRoboticsSuggestions.forEach { skill ->
                            val alreadyHas = userSkills.any { it.equals(skill, ignoreCase = true) }
                            FilterChip(
                                selected = alreadyHas,
                                onClick = {
                                    if (alreadyHas) onRemoveSkill(skill) else onAddSkill(skill)
                                },
                                label = { Text(if (alreadyHas) "✓ $skill" else "+ $skill") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = SuccessEmerald.copy(alpha = 0.2f),
                                    selectedLabelColor = SuccessEmerald
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Group 3: Software & Cloud
                    Text(text = "💻 Software, AI & Cloud Platforms", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = TechBluePrimary)
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        softwareCloudSuggestions.forEach { skill ->
                            val alreadyHas = userSkills.any { it.equals(skill, ignoreCase = true) }
                            FilterChip(
                                selected = alreadyHas,
                                onClick = {
                                    if (alreadyHas) onRemoveSkill(skill) else onAddSkill(skill)
                                },
                                label = { Text(if (alreadyHas) "✓ $skill" else "+ $skill") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = TechBluePrimary.copy(alpha = 0.2f),
                                    selectedLabelColor = TechBluePrimary
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    // Edit Profile Dialog
    if (showEditProfileDialog) {
        var name by remember { mutableStateOf(profile.fullName) }
        var discipline by remember { mutableStateOf(profile.discipline) }
        var degree by remember { mutableStateOf(profile.degree) }
        var gradYear by remember { mutableStateOf(profile.graduationYear) }
        var workMode by remember { mutableStateOf(profile.preferredWorkMode) }
        var targetSalary by remember { mutableStateOf(profile.targetSalary) }
        var summary by remember { mutableStateOf(profile.resumeSummary) }

        AlertDialog(
            onDismissRequest = { showEditProfileDialog = false },
            title = { Text("Edit Engineer Profile") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Full Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = discipline,
                        onValueChange = { discipline = it },
                        label = { Text("Discipline / Track") },
                        placeholder = { Text("e.g. Core Electronics + Embedded Tech") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = degree,
                        onValueChange = { degree = it },
                        label = { Text("Degree & Major") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = gradYear,
                            onValueChange = { gradYear = it },
                            label = { Text("Grad Year") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = workMode,
                            onValueChange = { workMode = it },
                            label = { Text("Work Mode") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = targetSalary,
                        onValueChange = { targetSalary = it },
                        label = { Text("Target Salary / CTC") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = summary,
                        onValueChange = { summary = it },
                        label = { Text("Career Summary / Pitch") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onUpdateProfile(name, discipline, degree, gradYear, workMode, targetSalary, summary)
                        showEditProfileDialog = false
                    }
                ) {
                    Text("Save Changes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditProfileDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
