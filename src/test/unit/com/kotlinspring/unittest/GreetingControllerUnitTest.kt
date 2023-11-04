package com.kotlinspring.unittest


import com.ninjasquad.springmockk.MockkBean
import com.kotlinspring.controller.GreetingController
import com.kotlinspring.service.GreetingService
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebMvcTest(controllers = [GreetingController::class])
@AutoConfigureWebTestClient
class GreetingControllerUnitTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var greetingService: GreetingService

    @Test
    fun retrieveGreeting(){
        val name = "mehedee"

        every {
            greetingService.retrieveGreeting(any())

        } returns
            "Hello $name, Hello from default profile"


        val result = webTestClient.get()
            .uri("/v1/greetings/{name}",name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        val expectedResult = "Hello $name, Hello from default profile"
        Assertions.assertEquals(expectedResult, result.responseBody)
    }



}