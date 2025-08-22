package com.ruoyi.net.repository;

import com.ruoyi.net.domain.NetDns;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INetDnsRepository extends MongoRepository<NetDns, String> {
}
