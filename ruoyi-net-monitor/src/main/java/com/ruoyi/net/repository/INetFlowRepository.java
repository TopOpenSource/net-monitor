package com.ruoyi.net.repository;

import com.ruoyi.net.domain.NetFlow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INetFlowRepository extends MongoRepository<NetFlow, String> {
}
