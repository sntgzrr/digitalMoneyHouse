package com.sntgzrr.services;

import com.sntgzrr.models.Transfer;
import com.sntgzrr.repositories.IActivityRepository;
import com.sntgzrr.repositories.ITransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl {
    @Autowired
    private final ITransferRepository iTransferRepository;
    public Transfer saveTransfer(Transfer transfer, Long carId){
        transfer.getCard().setId(carId);
        return this.iTransferRepository.save(transfer);
    }
}
