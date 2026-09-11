package com.study.rest.body_composition_api_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication：組み込みサーバー・自動設定を有効化
@SpringBootApplication
public class BodyCompositionApiDemoApplication {

	// エントリポイント。ここからアプリが起動する
	public static void main(String[] args) {
		SpringApplication.run(BodyCompositionApiDemoApplication.class, args);
	}

}
