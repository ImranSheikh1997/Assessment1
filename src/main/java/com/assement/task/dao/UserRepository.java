package com.assement.task.dao;

import com.assement.task.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a" +
            " SET a.addressLine = ?2, a.country = ?3," +
            " a.firstName = ?4, " +
            " a.lastName = ?6, " +
            "a.gender = ?5," +
            " a.zipCode = ?8, a.state= ?7 where a.email = ?1"
    )
    void updateUserByEmail(String email, String addressLine, String country, String firstName, String gender, String lastName, String state, int zipCode);

    @Transactional
    @Modifying
    @Query("UPDATE User a" +
            " SET a.password = ?2 where a.email=?1"
    )
    void updatePasswordByEmail(String email, String newPassword);
}
