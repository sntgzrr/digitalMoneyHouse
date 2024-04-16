package com.sntgzrr.repositories;

import com.sntgzrr.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransferRepository extends JpaRepository<Transfer, Long> {
}
