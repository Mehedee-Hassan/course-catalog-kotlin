package com.kotlinspring.service

import com.kotlinspring.dto.CourseDTO
import com.kotlinspring.entity.Course
import com.kotlinspring.repository.CourseRepository
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class CourseService(val courseRepository: CourseRepository) {

    companion object : KLogging()

    fun createCourse(courseDTO: CourseDTO): CourseDTO{

        val course = courseDTO.let {
            Course(id=null, it.name, it.category)
        }
        courseRepository.save(course)


        logger.info ("Course created: $course")

        return course.let{
            CourseDTO(it.id,it.name,it.category)
        }

    }
}
