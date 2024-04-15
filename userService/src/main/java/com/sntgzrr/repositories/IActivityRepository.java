package com.sntgzrr.repositories;

import com.sntgzrr.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findActivitiesByAccountUserId(Long userId);
    Optional<Activity> findActivityByAccountUserIdAndTransferId(Long userId, Long transferId);
}
