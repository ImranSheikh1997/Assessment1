package com.assement.task.dao;

import com.assement.task.dto.UserRequest;
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
    @Modifying
    @Query("UPDATE User c" +
            " SET c.name = ?4, c.address = ?2, c.mobileNumber = ?3 where c.email = ?1")
    void updateUser(String email, String address, String mobileNumber, String name);

    @Transactional
    @Modifying
    @Query("DELETE from User c"+
            " WHERE c.email=?1"

    )
    void deleteByEmail(String email);
}
