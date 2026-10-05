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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
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
import com.example.model.MockJobData
import com.example.model.SalaryBenchmark
import com.example.ui.theme.CoreAmberSecondary
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.TechBluePrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CareerInsightsScreen(
    modifier: Modifier = Modifier
) {
    var selectedInsightTab by remember { mutableStateOf(0) } // 0: Salaries & Benchmarks, 1: Core-Tech Bridge Guide

    val benchmarks = MockJobData.benchmarkData

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("career_insights_list"),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 80.dp)
    ) {
        // Header
        item {
            Column {
                Text(
                    text = "Career & Market Insights",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Real industry compensation benchmarks and transition roadmaps between Core Engineering and Software Tech.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(14.dp))

                TabRow(
                    selectedTabIndex = selectedInsightTab,
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.clip(RoundedCornerShape(12.dp))
                ) {
                    Tab(
                        selected = selectedInsightTab == 0,
                        onClick = { selectedInsightTab = 0 },
                        text = { Text("Salary Benchmarks", fontWeight = FontWeight.Bold) }
                    )
                    Tab(
                        selected = selectedInsightTab == 1,
                        onClick = { selectedInsightTab = 1 },
                        text = { Text("Core ↔ Tech Bridge", fontWeight = FontWeight.Bold) }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        if (selectedInsightTab == 0) {
            // Salary Overview Highlights
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(2.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.MonetizationOn, contentDescription = null, tint = SuccessEmerald, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Compensation Summary 2026", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Silicon & VLSI verification now rivals AI software salaries due to the global semiconductor supply chain expansion. Robotics and autonomous firmware engineers command highest hybrid premiums.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
            }

            // Benchmark Cards
            items(benchmarks) { bench ->
                BenchmarkItemCard(benchmark = bench)
                Spacer(modifier = Modifier.height(10.dp))
            }
        } else {
            // Core to Tech Bridge Roadmaps
            item {
                CoreTechBridgeContent()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BenchmarkItemCard(benchmark: SalaryBenchmark) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = benchmark.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = TechBluePrimary.copy(alpha = 0.12f)
                ) {
                    Text(
                        text = benchmark.growthRating,
                        style = MaterialTheme.typography.labelSmall,
                        color = TechBluePrimary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Salary Ladder
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Entry (0-2y)", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = benchmark.entrySalary, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
                Column {
                    Text(text = "Mid-Level (3-5y)", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = benchmark.midSalary, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = CoreAmberSecondary)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Lead / Staff", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = benchmark.leadSalary, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = SuccessEmerald)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Most Valued Skills:", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(4.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                benchmark.topSkills.forEach { skill ->
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = skill,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CoreTechBridgeContent() {
    Column {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Hub, contentDescription = null, tint = TechBluePrimary, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "The Modern Convergence", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Core engineering and software are no longer separate silos. The highest growth engineering positions in 2026 require hybrid fluency.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Bridge Pathway 1
        BridgePathwayCard(
            title = "1. Core Electronics → Hardware-Accelerated AI",
            sourceSkills = "Digital Electronics, Verilog, Computer Architecture",
            targetSkills = "CUDA, Triton, PyTorch Internal Kernels, TensorRT",
            companies = "Nvidia, OpenAI, Google TPU, Qualcomm, Apple ANE",
            description = "Understanding memory pipelines, cache coherency, and systolic matrix arrays allows hardware engineers to write ultra-fast AI inference kernels that pure software engineers struggle to conceptualize."
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Bridge Pathway 2
        BridgePathwayCard(
            title = "2. Mechanical/Electrical → Autonomous Robotics",
            sourceSkills = "Kinematics, Dynamics, CAD, Control Systems, MATLAB",
            targetSkills = "ROS2, Modern C++ (C++20), Extended Kalman Filters, LiDAR SLAM",
            companies = "Boston Dynamics, Waymo, Tesla Bot, Zoox, Skydio",
            description = "Robots must obey physical laws of inertia, friction, and torque. Mechanical & control engineers who learn modern C++ and ROS2 are premier candidates for motion planning."
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Bridge Pathway 3
        BridgePathwayCard(
            title = "3. Embedded Systems → High-Scale IoT Cloud",
            sourceSkills = "C, Microcontrollers, SPI/I2C, FreeRTOS, BLE",
            targetSkills = "Go, MQTT, Kafka, Distributed Edge Compute, Docker",
            companies = "Tesla Energy, Schneider Electric, Samsara, Verkada",
            description = "Connecting millions of physical industrial sensors to cloud backends requires engineers who understand byte packing, low power hardware limits, and distributed message queues."
        )
    }
}

@Composable
private fun BridgePathwayCard(
    title: String,
    sourceSkills: String,
    targetSkills: String,
    companies: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "Foundation Core Skills: $sourceSkills", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = CoreAmberSecondary)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Bridge Tech Skills: $targetSkills", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = SuccessEmerald)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Top Employers: $companies", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}
