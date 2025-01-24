package com.example.KtSpringBoot_Ex01.controller
import com.example.KtSpringBoot_Ex01.model.User
import com.example.KtSpringBoot_Ex01.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.slf4j.LoggerFactory
@RestController @RequestMapping("/api/users")
class UserController(private val userService: UserService) {
    private val logger = LoggerFactory.getLogger(UserController::class.java)

    init {
        logger.info("***** UserController initialized")
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): User? {
        logger.info("Fetching user with ID: $id")
        return userService.getUserById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User): User {
        logger.info("Creating user: $user")
        return userService.createUser(user)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): User? {
        logger.info("Updating user with ID: $id")
        return userService.updateUser(id, user)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long) {
        logger.info("Deleting user with ID: $id")
        userService.deleteUser(id)
    }

    @GetMapping
    fun getAllUsers(model: Model): String {
        val users = userService.getAllUsers()
        val tableRows = users.joinToString("") {
            "<tr><td>${it.id}</td><td>${it.name}</td><td>${it.email}</td></tr>"
        }
        return """ 
<!DOCTYPE html>
<html lang="en">
<head> <meta charset="UTF-8"> 
<title>User List</title>
<style>
table { width: 100%; border-collapse: collapse; margin: 20px 0; }
table, th, td { border: 1px solid black; }
th, td { padding: 8px; text-align: left; }
th { background-color: #f2f2f2; } </style>
</head>
<body>
<h1>User List</h1>
<table> <thead> <tr><th>ID</th><th>Name</th><th>Email</th></tr>
</thead>
 <tbody>
  $tableRows
</tbody>
</table>
</body>
</html>
"""
    }
}