package com.example.demo_kt

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class DemoKtApplication

fun main(args: Array<String>) {
	runApplication<DemoKtApplication>(*args)
}


//@SpringBootApplication
//object DemoKtApplication {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        SpringApplication.run(DemoKtApplication::class.java, *args)
//    }
//}