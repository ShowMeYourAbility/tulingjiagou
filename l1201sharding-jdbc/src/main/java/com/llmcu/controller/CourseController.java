package com.llmcu.controller;

import com.llmcu.common.dto.ResponseJson;
import com.llmcu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/7/13 12:29
 */
@RestController
@RequestMapping("course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/insert")
    public ResponseJson<Boolean> insertCourse(){
        return ResponseJson.success(courseService.insertCourses());
    }
}
