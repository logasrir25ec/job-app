package com.example.model

object MockJobData {
    val sampleJobs: List<JobListing> = listOf(
        // CORE ENGINEERING: VLSI & SEMICONDUCTOR
        JobListing(
            id = "core-vlsi-01",
            title = "ASIC Digital Verification Engineer",
            company = "Qualcomm",
            category = JobCategory.CORE,
            subCategory = "VLSI & Semiconductor",
            location = "Bangalore, India",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.ENTRY,
            salaryRange = "₹18 - ₹26 LPA ($85k - $120k)",
            tags = listOf("SystemVerilog", "UVM", "RTL Verification", "Synopsys VCS", "Python"),
            description = "Join Qualcomm's Snapdragon silicon verification team developing next-generation heterogeneous SOCs and 5G/6G processors.",
            responsibilities = listOf(
                "Develop comprehensive UVM testbenches, test plans, and functional coverage metrics for high-speed IP blocks.",
                "Execute RTL simulations, debug gate-level regressions, and close functional coverage holes.",
                "Automate regression scripts and coverage dashboards using Python and Bash.",
                "Collaborate closely with RTL design and physical design teams across global silicon design centers."
            ),
            requirements = listOf("SystemVerilog", "UVM", "Digital Electronics", "Verilog", "Python"),
            preferredSkills = listOf("Synopsys VCS", "Cadence Xcelium", "PCIe / AXI Protocols", "Tcl Scripting"),
            benefits = listOf("Silicon design tool access", "Annual stock grant", "Comprehensive health coverage", "Flexible hybrid schedule"),
            deadline = "15 Oct 2026",
            postedAgo = "2 days ago",
            isFeatured = true
        ),
        JobListing(
            id = "core-vlsi-02",
            title = "Analog & Mixed-Signal IC Design Engineer",
            company = "Texas Instruments",
            category = JobCategory.CORE,
            subCategory = "VLSI & Semiconductor",
            location = "Dallas, TX (or Bangalore)",
            workMode = WorkMode.ON_SITE,
            experienceLevel = ExperienceLevel.MID,
            salaryRange = "$135,000 - $165,000 / yr",
            tags = listOf("Cadence Virtuoso", "SPICE", "PLL", "ADC/DAC", "CMOS"),
            description = "Design cutting-edge precision analog signal chains, high-speed data converters, and power management ICs for automotive and industrial IoT.",
            responsibilities = listOf(
                "Architect transistor-level analog schematics for low-noise amplifiers, bandgaps, and pipelined ADCs.",
                "Perform Monte Carlo mismatch simulations, corners analysis, and parasitic extraction.",
                "Oversee analog physical layout matching and electromigration verification.",
                "Silicon bring-up in the hardware lab with oscilloscopes and spectrum analyzers."
            ),
            requirements = listOf("Analog IC Design", "SPICE", "Cadence Virtuoso", "CMOS", "Hardware Lab Debugging"),
            preferredSkills = listOf("SAR ADC", "PLL Design", "Layout Parasitic Extraction", "MATLAB"),
            benefits = listOf("Patent filing incentives", "Relocation assistance", "401(k) 6% match", "On-site lab & fitness center"),
            deadline = "20 Oct 2026",
            postedAgo = "3 days ago"
        ),
        JobListing(
            id = "core-vlsi-03",
            title = "GPU RTL Architecture Engineer",
            company = "Nvidia",
            category = JobCategory.CORE,
            subCategory = "VLSI & Semiconductor",
            location = "Santa Clara, CA",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.SENIOR,
            salaryRange = "$180,000 - $240,000 + RSU",
            tags = listOf("Verilog", "GPU Architecture", "RTL", "Timing Closure", "Computer Architecture"),
            description = "Shape the future of accelerated computing by designing tensor core execution pipelines and cache coherency fabrics for next-gen AI Superchips.",
            responsibilities = listOf(
                "Write clean, synthesizable Verilog/SystemVerilog RTL for streaming multiprocessor logic.",
                "Optimize critical timing paths and power-performance-area (PPA) trade-offs at 2nm node.",
                "Work with micro-architecture modelers to validate cycle-accurate performance simulators.",
                "Debug post-silicon anomalies on prototype Blackwell and successor GPU boards."
            ),
            requirements = listOf("Verilog", "RTL Design", "Computer Architecture", "Static Timing Analysis", "C++"),
            preferredSkills = listOf("CUDA", "Tensor Cores", "Formal Verification", "Synthesis"),
            benefits = listOf("Generous NVIDIA ESPP", "Executive coaching", "Unlimited PTO", "Leading-edge hardware labs"),
            deadline = "30 Oct 2026",
            postedAgo = "Just now",
            isFeatured = true
        ),

        // CORE ENGINEERING: EMBEDDED SYSTEMS & HARDWARE
        JobListing(
            id = "core-emb-01",
            title = "Embedded Firmware & BMS Engineer",
            company = "Tesla",
            category = JobCategory.CORE,
            subCategory = "Embedded Systems",
            location = "Austin, TX",
            workMode = WorkMode.ON_SITE,
            experienceLevel = ExperienceLevel.ENTRY,
            salaryRange = "$105,000 - $140,000 + Equity",
            tags = listOf("C/C++", "RTOS", "CAN Bus", "Battery Management", "Microcontrollers"),
            description = "Write safety-critical firmware controlling high-voltage lithium battery packs, state-of-charge algorithms, and thermal balancing systems.",
            responsibilities = listOf(
                "Develop bare-metal and FreeRTOS drivers for ARM Cortex-M microcontrollers.",
                "Implement ISO 26262 functional safety requirements for high-voltage energy storage systems.",
                "Interface hardware sensors using SPI, I2C, and CAN-FD communication protocols.",
                "Validate battery protection limits using hardware-in-the-loop (HIL) simulators."
            ),
            requirements = listOf("C/C++", "Embedded C", "RTOS", "Microcontrollers", "CAN Bus"),
            preferredSkills = listOf("FreeRTOS", "Battery Management Systems", "HIL Testing", "Git"),
            benefits = listOf("Tesla stock purchase discount", "Comprehensive health", "Electric vehicle car pool perks"),
            deadline = "18 Oct 2026",
            postedAgo = "1 day ago",
            isFeatured = true
        ),
        JobListing(
            id = "core-emb-02",
            title = "Automotive Embedded & AUTOSAR Specialist",
            company = "Bosch",
            category = JobCategory.CORE,
            subCategory = "Embedded Systems",
            location = "Stuttgart, Germany / Pune, India",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.MID,
            salaryRange = "€75,000 - €95,000 / ₹22 - ₹32 LPA",
            tags = listOf("Embedded C", "AUTOSAR", "CAN", "Vector DaVinci", "Diagnostics UDS"),
            description = "Build intelligent electronic control units (ECU) for autonomous braking and driver assistance radar systems in commercial vehicles.",
            responsibilities = listOf(
                "Configure AUTOSAR Classic BSW layers (OS, COM, CanIf, Dem/Dcm, NvM) using DaVinci Configurator.",
                "Develop complex device drivers (CDD) for specialized peripheral ICs.",
                "Participate in ASPICE audits and automated unit test suite execution with Vector CANoe.",
                "Coordinate with global OEM engineering teams (BMW, Mercedes, Porsche)."
            ),
            requirements = listOf("Embedded C", "AUTOSAR", "CAN", "Vector Tools", "Git"),
            preferredSkills = listOf("Vector CANoe", "CAPL Scripting", "UDS Diagnostics", "MISRA C"),
            benefits = listOf("International mobility", "Flexible working hours", "Pension scheme", "Education subsidy"),
            deadline = "25 Oct 2026",
            postedAgo = "4 days ago"
        ),

        // CORE ENGINEERING: MECHANICAL, MECHATRONICS & ROBOTICS
        JobListing(
            id = "core-mech-01",
            title = "Mechatronics & Precision Motion Systems Engineer",
            company = "ASML",
            category = JobCategory.CORE,
            subCategory = "Mechatronics & Robotics",
            location = "Veldhoven, Netherlands",
            workMode = WorkMode.ON_SITE,
            experienceLevel = ExperienceLevel.MID,
            salaryRange = "€80,000 - €105,000",
            tags = listOf("MATLAB", "Control Systems", "Mechatronics", "Dynamics", "Precision Mechanics"),
            description = "Develop sub-nanometer wafer stage positioning mechanisms for state-of-the-art Extreme Ultraviolet (EUV) photolithography machines.",
            responsibilities = listOf(
                "Design multivariable feedback control algorithms (MIMO) for magnetically levitated wafer stages.",
                "Model mechanical resonances, vibrations, and thermal drift using MATLAB/Simulink.",
                "Validate experimental transfer functions on prototype EUV lithography platforms.",
                "Collaborate with optics physicists and vacuum engineers."
            ),
            requirements = listOf("Control Systems", "MATLAB", "Mechatronics", "Vibration Analysis", "Simulink"),
            preferredSkills = listOf("Nanopositioning", "State-Space Controls", "Optomechatronics", "LabVIEW"),
            benefits = listOf("Dutch 30% tax ruling eligibility", "Relocation package", "Top tier semiconductor engineering lab"),
            deadline = "05 Nov 2026",
            postedAgo = "2 days ago",
            isFeatured = true
        ),
        JobListing(
            id = "core-mech-02",
            title = "Mechanical Design & FEA Engineer (CAD)",
            company = "Boeing",
            category = JobCategory.CORE,
            subCategory = "Mechanical & Aerospace",
            location = "Seattle, WA",
            workMode = WorkMode.ON_SITE,
            experienceLevel = ExperienceLevel.ENTRY,
            salaryRange = "$92,000 - $125,000",
            tags = listOf("SolidWorks", "CATIA", "FEA Analysis", "Aerospace Structures", "ANSYS"),
            description = "Design commercial airframe structural components, composite wing fairings, and fuselage load bearing brackets.",
            responsibilities = listOf(
                "Create parametric 3D models and manufacturing blueprints in CATIA V5/3DEXPERIENCE.",
                "Conduct structural stress, fatigue, and buckling simulations using ANSYS and NASTRAN.",
                "Review GD&T tolerances and coordinate with aircraft assembly line technicians.",
                "Document compliance for FAA structural airworthiness certification."
            ),
            requirements = listOf("SolidWorks", "CAD", "FEA Analysis", "Mechanical Design", "GD&T"),
            preferredSkills = listOf("CATIA V5", "ANSYS", "Composites", "Aerospace Materials"),
            benefits = listOf("Aviation employee discount", "Tuition reimbursement", "401k match", "Pension plan"),
            deadline = "12 Nov 2026",
            postedAgo = "5 days ago"
        ),

        // CORE ENGINEERING: ELECTRICAL & POWER SYSTEMS
        JobListing(
            id = "core-elec-01",
            title = "Power Systems & Renewable Grid Integration Engineer",
            company = "Schneider Electric",
            category = JobCategory.CORE,
            subCategory = "Electrical & Power",
            location = "Boston, MA / Bangalore, India",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.ENTRY,
            salaryRange = "$95,000 - $130,000 / ₹16 - ₹24 LPA",
            tags = listOf("Power Systems", "ETAP", "SCADA", "Renewable Energy", "Inverters"),
            description = "Architect microgrid control systems, solar photovoltaic grid interconnects, and utility-scale battery energy storage systems (BESS).",
            responsibilities = listOf(
                "Perform load flow analysis, short circuit calculations, and relay coordination using ETAP and PSCAD.",
                "Design protective relay schemes for medium voltage switchgear and grid inverters.",
                "Configure Modbus, DNP3, and IEC 61850 substation automation communication protocols.",
                "Commission on-site microgrid testbeds and industrial facilities."
            ),
            requirements = listOf("Power Systems", "Electrical Engineering", "ETAP", "Circuit Design", "Power Electronics"),
            preferredSkills = listOf("IEC 61850", "SCADA", "PSCAD", "Battery Energy Storage"),
            benefits = listOf("Green energy sustainability awards", "Hybrid flexibility", "Professional PE license sponsorship"),
            deadline = "28 Oct 2026",
            postedAgo = "3 days ago"
        ),

        // CORE ENGINEERING: BIOTECH & MEDICAL DEVICES
        JobListing(
            id = "core-bio-01",
            title = "Biomedical Hardware Systems Engineer",
            company = "Medtronic",
            category = JobCategory.CORE,
            subCategory = "Biomedical Engineering",
            location = "Minneapolis, MN",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.MID,
            salaryRange = "$115,000 - $145,000",
            tags = listOf("Biomedical", "PCB Design", "Sensor Interface", "FDA Regulations", "Altium"),
            description = "Innovate implantable cardiac pacemakers and wearable neural stimulators delivering life-saving therapies.",
            responsibilities = listOf(
                "Design ultra-low-power biosignal acquisition analog front ends (AFE) for ECG/EEG telemetry.",
                "Lead PCB layout in Altium Designer complying with IEC 60601 medical electrical safety standards.",
                "Prepare Design History Files (DHF) and hazard risk analysis for FDA 510(k) submissions.",
                "Conduct benchtop electrical test protocols under physiological environmental conditions."
            ),
            requirements = listOf("PCB Design", "Circuit Design", "Altium", "Hardware Debugging", "Sensors"),
            preferredSkills = listOf("Altium Designer", "IEC 60601", "Biomedical Instrumentation", "ISO 13485"),
            benefits = listOf("Impact on patient lives", "Patent royalty bonuses", "Health wellness stipend"),
            deadline = "14 Nov 2026",
            postedAgo = "6 days ago"
        ),

        // HYBRID: ROBOTICS, AUTONOMOUS SYSTEMS & FIRMWARE
        JobListing(
            id = "hyb-rob-01",
            title = "Robotics Software & Motion Planning Engineer",
            company = "Boston Dynamics",
            category = JobCategory.HYBRID,
            subCategory = "Robotics & Automation",
            location = "Waltham, MA",
            workMode = WorkMode.ON_SITE,
            experienceLevel = ExperienceLevel.MID,
            salaryRange = "$140,000 - $185,000",
            tags = listOf("C++", "ROS2", "Motion Planning", "Kinematics", "Trajectory Optimization"),
            description = "Program dynamic locomotion and manipulation behaviors for quadruped and humanoid robots navigating unstructured environments.",
            responsibilities = listOf(
                "Implement whole-body kinematics and quadratic programming (QP) trajectory optimization in modern C++.",
                "Build ROS2 nodes communicating over real-time EtherCAT bus with brushless motor actuators.",
                "Integrate 3D LiDAR point clouds and stereo depth cameras for obstacle collision avoidance.",
                "Test agile maneuvers and balance recovery on physical hardware in our robot obstacle courses."
            ),
            requirements = listOf("C++", "ROS2", "Robotics", "Control Systems", "Kinematics"),
            preferredSkills = listOf("Trajectory Optimization", "EtherCAT", "MoveIt", "Python"),
            benefits = listOf("Work directly with world-famous robots", "Generous equity", "Catered lunches", "Gym on-site"),
            deadline = "01 Nov 2026",
            postedAgo = "Just now",
            isFeatured = true
        ),
        JobListing(
            id = "hyb-auto-02",
            title = "Autonomous Vehicle Sensor Fusion & Perception Engineer",
            company = "Waymo",
            category = JobCategory.HYBRID,
            subCategory = "Autonomous Vehicles",
            location = "Mountain View, CA",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.SENIOR,
            salaryRange = "$190,000 - $260,000 + RSU",
            tags = listOf("C++", "Computer Vision", "LiDAR", "Kalman Filter", "CUDA"),
            description = "Build real-time perception and tracking algorithms processing multi-camera and high-resolution LiDAR streams on autonomous robotaxis.",
            responsibilities = listOf(
                "Design Extended Kalman Filters (EKF) and deep neural network heads for 3D bounding box detection.",
                "Optimize real-time inference latency using TensorRT and CUDA kernels on automotive compute units.",
                "Collaborate with vehicle safety operators to analyze edge-case sensor dropouts in dense rain and fog.",
                "Profile memory bandwidth and multi-threaded synchronization in C++20."
            ),
            requirements = listOf("C++", "Computer Vision", "Machine Learning", "CUDA", "Sensor Fusion"),
            preferredSkills = listOf("LiDAR Processing", "TensorRT", "Kalman Filtering", "ROS"),
            benefits = listOf("Alphabet equity refreshers", "401k 50% match", "Free autonomous taxi rides", "World-class team"),
            deadline = "10 Nov 2026",
            postedAgo = "1 day ago",
            isFeatured = true
        ),

        // SOFTWARE ENGINEERING: AI / MACHINE LEARNING
        JobListing(
            id = "sw-ai-01",
            title = "Machine Learning Infrastructure & LLM Systems Engineer",
            company = "Anthropic",
            category = JobCategory.SOFTWARE,
            subCategory = "AI & Machine Learning",
            location = "San Francisco, CA (or Remote)",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.SENIOR,
            salaryRange = "$220,000 - $310,000 + Equity",
            tags = listOf("PyTorch", "Python", "Distributed Systems", "Triton", "Kubernetes"),
            description = "Architect high-throughput inference engines and distributed training infrastructure powering state-of-the-art Claude models.",
            responsibilities = listOf(
                "Optimize custom GPU kernels in Triton and CUDA for attention mechanisms and KV cache compression.",
                "Scale distributed training pipelines across thousands of H100/B200 GPUs using Megatron-LM and Ray.",
                "Design low-latency serving stacks with continuous batching and speculative decoding.",
                "Monitor cluster health, network fabric congestion (RoCE / InfiniBand), and automated checkpoint recovery."
            ),
            requirements = listOf("Python", "PyTorch", "Distributed Systems", "Machine Learning", "C++"),
            preferredSkills = listOf("Triton", "CUDA", "Ray", "Kubernetes", "vLLM"),
            benefits = listOf("Top tier equity package", "High impact AI safety mission", "Full health/dental/vision 100% covered"),
            deadline = "15 Nov 2026",
            postedAgo = "Just now",
            isFeatured = true
        ),
        JobListing(
            id = "sw-ai-02",
            title = "Computer Vision & Edge AI Engineer",
            company = "Apple",
            category = JobCategory.SOFTWARE,
            subCategory = "AI & Machine Learning",
            location = "Cupertino, CA",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.MID,
            salaryRange = "$165,000 - $215,000",
            tags = listOf("PyTorch", "CoreML", "Computer Vision", "Python", "C++"),
            description = "Develop on-device neural network models for spatial computing, object tracking, and camera computational photography.",
            responsibilities = listOf(
                "Train lightweight convolutional and transformer backbones for real-time mobile segmentation.",
                "Quantize FP32 models to INT8/INT4 using CoreML and Apple Neural Engine (ANE) toolchains.",
                "Collaborate with low-level iOS and visionOS framework teams to integrate model pipelines.",
                "Benchmark power consumption and thermal dissipation during continuous video inference."
            ),
            requirements = listOf("Python", "PyTorch", "Computer Vision", "Machine Learning", "C++"),
            preferredSkills = listOf("CoreML", "Model Quantization", "Swift", "Metal Shading"),
            benefits = listOf("Apple product hardware discounts", "Subsidized commute", "On-site wellness centers"),
            deadline = "22 Oct 2026",
            postedAgo = "3 days ago"
        ),

        // SOFTWARE ENGINEERING: DISTRIBUTED SYSTEMS & BACKEND
        JobListing(
            id = "sw-back-01",
            title = "Distributed Systems & Cloud Backend Engineer",
            company = "Google",
            category = JobCategory.SOFTWARE,
            subCategory = "Backend & Cloud",
            location = "Sunnyvale, CA / Bangalore, India",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.ENTRY,
            salaryRange = "$140,000 - $185,000 / ₹28 - ₹42 LPA",
            tags = listOf("Go", "gRPC", "Distributed Systems", "Kubernetes", "C++"),
            description = "Build planet-scale storage and indexing microservices handling billions of queries per second across global data centers.",
            responsibilities = listOf(
                "Design fault-tolerant APIs in Go and C++ utilizing gRPC and Protocol Buffers.",
                "Implement distributed consensus protocols (Raft, Paxos) and consistent hashing mechanisms.",
                "Optimize garbage collection pauses and network socket throughput under high p99 latency constraints.",
                "Conduct automated chaos engineering drills and disaster recovery rehearsals."
            ),
            requirements = listOf("Go", "Distributed Systems", "gRPC", "Algorithms", "Git"),
            preferredSkills = listOf("Kubernetes", "C++", "Spanner", "System Design"),
            benefits = listOf("Gourmet meals daily", "Generous 401k match", "Peer learning university", "Comprehensive healthcare"),
            deadline = "30 Oct 2026",
            postedAgo = "2 days ago",
            isFeatured = true
        ),
        JobListing(
            id = "sw-back-02",
            title = "Financial Core Ledger & API Engineer",
            company = "Stripe",
            category = JobCategory.SOFTWARE,
            subCategory = "Backend & Systems",
            location = "Remote (US / Europe / India)",
            workMode = WorkMode.REMOTE,
            experienceLevel = ExperienceLevel.MID,
            salaryRange = "$170,000 - $225,000",
            tags = listOf("Java", "Go", "Distributed Systems", "SQL", "Kafka"),
            description = "Build double-entry ledger engines and real-time fraud mitigation microservices processing trillions of dollars in global commerce.",
            responsibilities = listOf(
                "Architect immutable double-entry accounting ledgers ensuring zero balance discrepancies.",
                "Build real-time event streaming consumers using Apache Kafka and PostgreSQL.",
                "Design idempotent RESTful and webhook architectures with 99.999% uptime SLA.",
                "Participate in operational on-call rotations and write clear post-incident blameless postmortems."
            ),
            requirements = listOf("Java", "Go", "SQL", "Distributed Systems", "Kafka"),
            preferredSkills = listOf("Microservices", "Event-Driven Architecture", "API Design", "PostgreSQL"),
            benefits = listOf("100% remote anywhere stipend", "$2k home office setup", "Annual company retreats"),
            deadline = "16 Nov 2026",
            postedAgo = "4 days ago"
        ),
        JobListing(
            id = "sw-back-03",
            title = "Database Engine & Storage Kernel Engineer",
            company = "Databricks",
            category = JobCategory.SOFTWARE,
            subCategory = "Systems & Databases",
            location = "San Francisco, CA / Seattle, WA",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.SENIOR,
            salaryRange = "$210,000 - $285,000",
            tags = listOf("C++", "Rust", "Database Internals", "SIMD", "Query Optimization"),
            description = "Develop photon vectorized execution engines and parquet format storage layers powering the Databricks Lakehouse.",
            responsibilities = listOf(
                "Write high-performance modern C++ utilizing AVX-512 SIMD vector instructions for columnar scans.",
                "Develop cost-based query optimizers, push-down predicates, and memory-mapped file buffers.",
                "Troubleshoot low-level cache misses and branch mispredictions with Linux `perf` and VTune.",
                "Collaborate with open-source Apache Spark and Delta Lake communities."
            ),
            requirements = listOf("C++", "Rust", "Database Internals", "Algorithms", "Linux"),
            preferredSkills = listOf("SIMD", "Apache Spark", "Compiler Design", "Memory Management"),
            benefits = listOf("High-growth pre-IPO equity", "Flexible time off", "Wellness reimbursements"),
            deadline = "19 Nov 2026",
            postedAgo = "1 day ago"
        ),

        // SOFTWARE ENGINEERING: MOBILE (ANDROID & JETPACK COMPOSE)
        JobListing(
            id = "sw-mob-01",
            title = "Staff Android Platform & App Architect",
            company = "Spotify",
            category = JobCategory.SOFTWARE,
            subCategory = "Mobile & Frontend",
            location = "Stockholm / New York (Remote OK)",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.SENIOR,
            salaryRange = "$175,000 - $230,000",
            tags = listOf("Android", "Kotlin", "Jetpack Compose", "Coroutines", "Audio DSP"),
            description = "Lead client architecture for Spotify's flagship Android application used by over 600 million music and podcast listeners worldwide.",
            responsibilities = listOf(
                "Design fluid, accessible Jetpack Compose UI component systems and buttery 120Hz animations.",
                "Implement offline audio caching, ExoPlayer streaming buffers, and Bluetooth LE audio handoffs.",
                "Drive modularization, build-time reduction, and automated UI test pipelines.",
                "Mentor junior and mid-level Android engineers in modern reactive Kotlin programming."
            ),
            requirements = listOf("Android", "Kotlin", "Jetpack Compose", "Coroutines", "Room"),
            preferredSkills = listOf("ExoPlayer", "MVI Architecture", "Gradle Optimization", "Accessibility"),
            benefits = listOf("Free Spotify for life + family", "Work from anywhere model", "Parental leave 6 months"),
            deadline = "08 Nov 2026",
            postedAgo = "3 days ago",
            isFeatured = true
        ),
        JobListing(
            id = "sw-mob-02",
            title = "Android Mobile Engineer - Consumer Apps",
            company = "Meta",
            category = JobCategory.SOFTWARE,
            subCategory = "Mobile & Frontend",
            location = "Menlo Park, CA / London, UK",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.ENTRY,
            salaryRange = "$130,000 - $175,000",
            tags = listOf("Android", "Kotlin", "Jetpack Compose", "GraphQL", "Performance"),
            description = "Build high-speed camera, messaging, and feed experiences in Instagram and Threads on Android.",
            responsibilities = listOf(
                "Implement feature screens using Jetpack Compose and Material 3 design systems.",
                "Optimize app startup time, frame drop rates (jank), and APK download footprint.",
                "Integrate GraphQL client mutations and offline Room database persistence.",
                "Coordinate AB experiments with product managers and UX research."
            ),
            requirements = listOf("Android", "Kotlin", "Jetpack Compose", "Git", "REST APIs"),
            preferredSkills = listOf("GraphQL", "Room", "Jetpack Navigation", "Profiling"),
            benefits = listOf("Meta stock bonus", "Free meals and transit", "Top-tier wellness perks"),
            deadline = "27 Oct 2026",
            postedAgo = "5 days ago"
        ),

        // SOFTWARE ENGINEERING: CLOUD, DEVOPS & SRE
        JobListing(
            id = "sw-dev-01",
            title = "Cloud Infrastructure & SRE Architect",
            company = "Netflix",
            category = JobCategory.SOFTWARE,
            subCategory = "Cloud & DevOps",
            location = "Los Gatos, CA",
            workMode = WorkMode.HYBRID,
            experienceLevel = ExperienceLevel.SENIOR,
            salaryRange = "$220,000 - $350,000 all-cash",
            tags = listOf("AWS", "Kubernetes", "Terraform", "Docker", "Python"),
            description = "Ensure flawless streaming reliability across global AWS regions with automated multi-zone failovers and chaos testing.",
            responsibilities = listOf(
                "Provision scalable cloud architecture with Terraform, Titus, and Kubernetes.",
                "Automate continuous deployment canary pipelines using Spinnaker.",
                "Conduct game days and fault injection experiments (Chaos Monkey) on live production clusters.",
                "Optimize multi-petabyte cloud ingress/egress transit costs and compute utilization."
            ),
            requirements = listOf("AWS", "Kubernetes", "Terraform", "Docker", "Linux"),
            preferredSkills = listOf("Spinnaker", "Chaos Engineering", "Python", "Prometheus"),
            benefits = listOf("Top of market compensation in cash", "Unlimited vacation", "High freedom & responsibility"),
            deadline = "12 Nov 2026",
            postedAgo = "1 day ago"
        ),

        // SOFTWARE ENGINEERING: CYBERSECURITY
        JobListing(
            id = "sw-sec-01",
            title = "Kernel Security & Threat Detection Engineer",
            company = "CrowdStrike",
            category = JobCategory.SOFTWARE,
            subCategory = "Cybersecurity & Systems",
            location = "Remote",
            workMode = WorkMode.REMOTE,
            experienceLevel = ExperienceLevel.MID,
            salaryRange = "$150,000 - $195,000",
            tags = listOf("C/C++", "eBPF", "Linux Kernel", "Reverse Engineering", "Go"),
            description = "Build low-overhead Falcon sensor agent components monitoring kernel events and thwarting sophisticated cyber attacks.",
            responsibilities = listOf(
                "Write secure eBPF probe programs and kernel modules intercepting suspicious syscalls.",
                "Reverse engineer zero-day exploits, rootkits, and memory corruption payloads.",
                "Implement tamper-proof telemetry transmission channels using TLS and cryptographic hashing.",
                "Audit sensor code for zero-overhead performance impact on host servers."
            ),
            requirements = listOf("C/C++", "Linux Kernel", "Cybersecurity", "Operating Systems", "Git"),
            preferredSkills = listOf("eBPF", "Reverse Engineering", "Go", "GDB"),
            benefits = listOf("100% remote globally", "Continuing security certifications paid", "Home office stipend"),
            deadline = "24 Oct 2026",
            postedAgo = "4 days ago"
        )
    )

    val commonSkillsSuggestions = listOf(
        // Core Hardware & Engineering
        "SystemVerilog", "Verilog", "UVM", "RTL Design", "Cadence Virtuoso", "SPICE", "FPGA",
        "Embedded C", "C/C++", "RTOS", "FreeRTOS", "Microcontrollers", "CAN Bus", "AUTOSAR",
        "PCB Design", "Altium", "Circuit Design", "Hardware Lab Debugging", "Control Systems",
        "MATLAB", "Simulink", "SolidWorks", "CATIA", "FEA Analysis", "ANSYS", "Mechatronics",
        "Robotics", "ROS2", "Kinematics", "Power Systems", "ETAP",
        // Software & Tech
        "Python", "Java", "Go", "Rust", "Kotlin", "Android", "Jetpack Compose", "Coroutines",
        "PyTorch", "TensorFlow", "Computer Vision", "Machine Learning", "CUDA",
        "Distributed Systems", "gRPC", "SQL", "PostgreSQL", "Kafka", "Redis",
        "Docker", "Kubernetes", "AWS", "Terraform", "Linux", "Git", "Cybersecurity", "eBPF"
    )

    val benchmarkData = listOf(
        SalaryBenchmark(
            title = "VLSI / Silicon Design (Core)",
            entrySalary = "₹18 - ₹26 LPA / $115k",
            midSalary = "₹30 - ₹48 LPA / $155k",
            leadSalary = "₹60+ LPA / $240k+",
            topSkills = listOf("SystemVerilog", "UVM", "Synopsys VCS", "Static Timing Analysis"),
            growthRating = "Very High (Global Chip Act Boom)"
        ),
        SalaryBenchmark(
            title = "Embedded & Firmware (Core/Hybrid)",
            entrySalary = "₹12 - ₹20 LPA / $95k",
            midSalary = "₹22 - ₹35 LPA / $135k",
            leadSalary = "₹45+ LPA / $190k+",
            topSkills = listOf("Embedded C", "RTOS", "AUTOSAR", "CAN/SPI/I2C"),
            growthRating = "High (EV & IoT surge)"
        ),
        SalaryBenchmark(
            title = "Robotics & Autonomous Systems (Hybrid)",
            entrySalary = "₹16 - ₹25 LPA / $125k",
            midSalary = "₹32 - ₹50 LPA / $175k",
            leadSalary = "₹65+ LPA / $260k+",
            topSkills = listOf("ROS2", "C++20", "Kinematics", "LiDAR / Sensor Fusion"),
            growthRating = "Explosive (Humanoids & Drones)"
        ),
        SalaryBenchmark(
            title = "AI & Machine Learning Systems (Software)",
            entrySalary = "₹22 - ₹36 LPA / $145k",
            midSalary = "₹45 - ₹75 LPA / $220k",
            leadSalary = "₹90+ LPA / $350k+",
            topSkills = listOf("PyTorch", "Triton / CUDA", "Distributed Training", "vLLM"),
            growthRating = "Massive (Generative AI & Inference)"
        ),
        SalaryBenchmark(
            title = "Distributed Backend & Systems (Software)",
            entrySalary = "₹18 - ₹30 LPA / $130k",
            midSalary = "₹35 - ₹60 LPA / $185k",
            leadSalary = "₹70+ LPA / $280k+",
            topSkills = listOf("Go / Rust", "gRPC", "Kafka", "High p99 Optimization"),
            growthRating = "Very High (Cloud Scale)"
        ),
        SalaryBenchmark(
            title = "Mobile & Client Platform (Software)",
            entrySalary = "₹14 - ₹24 LPA / $115k",
            midSalary = "₹28 - ₹45 LPA / $160k",
            leadSalary = "₹55+ LPA / $225k+",
            topSkills = listOf("Jetpack Compose", "Kotlin Coroutines", "Audio/Video Pipeline", "M3"),
            growthRating = "Steady High (Core App Experience)"
        )
    )
}

data class SalaryBenchmark(
    val title: String,
    val entrySalary: String,
    val midSalary: String,
    val leadSalary: String,
    val topSkills: List<String>,
    val growthRating: String
)
