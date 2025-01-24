package com.example.KtSpringBoot_Ex01.model
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.ToString
import javax.persistence.*


// for JPA annotations such as @Entity, @Id, @GeneratedValue
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
data class User(
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0, @NotBlank(message = "Name is mandatory")
    val name: String, @Email(message = "Email should be valid")
    val email: String
) {
    constructor() : this(0,"","") {

    }
}