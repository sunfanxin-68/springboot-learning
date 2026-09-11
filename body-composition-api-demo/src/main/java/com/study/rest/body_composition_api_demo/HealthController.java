package com.study.rest.body_composition_api_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ヘルスチェックのJSON形式 {"status":"..."}
record HealthResponse(String status) {}

// @RestController：戻り値をJSONに変換して返す
@RestController
// このクラスのAPIは /api/v1 から始まる
@RequestMapping("/api/v1")
public class HealthController {

    // GET /healthz。サーバー稼働確認用
    @GetMapping("/healthz")
    public HealthResponse healthCheck() {
        // "OK" → {"status":"OK"} に自動変換
        return new HealthResponse("OK");
    }
}

