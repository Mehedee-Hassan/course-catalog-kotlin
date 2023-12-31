package com.kotlinspring.controller

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/courses")
class CourseController(val courseService: CourseService)  {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody courseDTO: CourseDTO) : CourseDTO
    {
        return courseService.createCourse(courseDTO)
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCourses(): List<CourseDTO>{
        return courseService.getAllCourses()
    }
}