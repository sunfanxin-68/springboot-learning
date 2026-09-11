package com.study.rest.body_composition_api_demo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController：戻り値をJSONで返す。共通パス /api/v1/body-compositions
@RestController
@RequestMapping("/api/v1/body-compositions")
public class BodyCompositionController {

    private final BodyCompositionRepository repository;  // Springが自動注入


    public BodyCompositionController(BodyCompositionRepository repository) {
        this.repository = repository;
    }

    // POST /me。X-User-Idで本人を識別し、リクエストをDB保存
    @PostMapping("/me")
    public ResponseEntity<BodyComposition> createRecord(
            @RequestHeader("X-User-Id") String userId,  // ログインユーザーID
            @Valid @RequestBody BodyCompositionRequest request  // JSONボディ
    ) {
        BodyComposition record = new BodyComposition(userId, request.weight(), request.bodyFat());
        BodyComposition saved = repository.save(record);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /me。X-User-Idの最新1件を返す
    public ResponseEntity<BodyComposition> getLatestRecord(
            @RequestHeader("X-User-Id") String userId
    ) {
        return repository.findFirstByUserIdOrderByIdDesc(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}