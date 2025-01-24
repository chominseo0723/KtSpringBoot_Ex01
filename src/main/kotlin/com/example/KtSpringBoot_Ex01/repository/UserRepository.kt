package com.example.KtSpringBoot_Ex01.repository
import com.example.KtSpringBoot_Ex01.model.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
@Repository
class UserRepository(private val jdbcTemplate: JdbcTemplate) {
    private val rowMapper = RowMapper<User> { rs, _ ->
        User(
            id = rs.getLong("id"),
            name = rs.getString("name"),
            email = rs.getString("email")
        )
    }
    fun findAll(): List<User> {
        return jdbcTemplate.query("SELECT * FROM users", rowMapper)
    }
    fun findById(id: Long): User? {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", rowMapper, id)
    }
    fun save(user: User): User {
        jdbcTemplate.update("INSERT INTO users (name, email) VALUES (?, ?)", user.name, user.email)
// Retrieve the last inserted ID for the new user (database-generated ID)
        val id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long::class.java) ?: 0
        return user.copy(id = id)
    }
    fun update(id: Long, user: User): Int {
        return jdbcTemplate.update("UPDATE users SET name = ?, email = ? WHERE id = ?", user.name, user.email, id)
    }
    fun deleteById(id: Long): Int {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", id)
    }
}