package com.example.demo_kt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoKtApplication

fun main(args: Array<String>) {
	runApplication<DemoKtApplication>(*args)
}
