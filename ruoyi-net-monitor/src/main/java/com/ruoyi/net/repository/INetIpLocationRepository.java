package com.ruoyi.net.repository;

import com.ruoyi.net.domain.NetIpLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INetIpLocationRepository extends MongoRepository<NetIpLocation, String> {
}
