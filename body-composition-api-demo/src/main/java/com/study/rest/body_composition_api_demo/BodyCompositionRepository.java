package com.study.rest.body_composition_api_demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepositoryを継承。save/find等のDB操作が使える
public interface BodyCompositionRepository extends JpaRepository<BodyComposition, Long> {

    // userId一致・id降順の先頭1件。なければOptional.empty()
    Optional<BodyComposition> findFirstByUserIdOrderByIdDesc(String userId);
}