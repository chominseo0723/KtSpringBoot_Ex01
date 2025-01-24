package com.example.KtSpringBoot_Ex01
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.slf4j.Logger
import org.slf4j.LoggerFactory
@SpringBootApplication
@ComponentScan(basePackages = ["com.example"])
class KtSpringBoot101Application
fun main(args: Array<String>) {
	val logger = LoggerFactory.getLogger("\n>>>> Main() Function ")
	logger.info(">>>> KtSpringBoot101Application::main() starts Web application . . . ")
	runApplication<KtSpringBoot101Application>(*args)
}
