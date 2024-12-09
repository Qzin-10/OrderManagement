package com.Qzin.repository;

import com.Qzin.entity.Item;
import com.Qzin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.user_uuid=:userUUID")
    Optional<User> getUserByUUID(@Param("userUUID") String userUUID);
}
