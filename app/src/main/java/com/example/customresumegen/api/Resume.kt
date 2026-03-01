package com.example.customresumegen.api


data class Results(
        val results:ArrayList<Resume>
        )


data class Resume(
        val address:String,
        val email:String,
        val name:String,
        val phone:String,
        val projects:ArrayList<ProjectDetails>,
    val skills:List<String>,
    val summery:String,
    val twitter:String,
        )

data class ProjectDetails(
    val description:String,
    val endDate:String,
    val startDate:String,
    val title:String,
        )