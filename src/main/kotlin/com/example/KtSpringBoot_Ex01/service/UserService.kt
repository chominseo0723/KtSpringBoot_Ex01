package com.example.KtSpringBoot_Ex01.service
import com.example.KtSpringBoot_Ex01.model.User
import com.example.KtSpringBoot_Ex01.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> = userRepository.findAll()
    fun getUserById(id: Long): User? = userRepository.findById(id)
    fun createUser(user: User): User = userRepository.save(user)
    fun updateUser(id: Long, updatedUser: User): User? {
        return if (userRepository.update(id, updatedUser) > 0) {
            updatedUser.copy(id = id)
        } else {
            null
        }
    }




    fun deleteUser(id: Long): Boolean {
        return userRepository.deleteById(id) > 0
    }
}