package com.phocas.asset.rest.repository;

import com.phocas.asset.rest.model.AssetEvent;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface AssetRepository extends
        CrudRepository<AssetEvent, String> {

    Optional<AssetEvent> findById(String id);
}