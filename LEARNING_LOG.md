# Spring Boot 学習記録

## 2026-09-10（Day 1）

### 目的
Java / Spring Boot を使った REST API 開発の基礎を習得する。

### 今日学んだこと（概念）

- **API とは**: ソフトウェア同士がデータをやり取りするための窓口・契約。
- **REST API とは**: HTTP のメソッド（GET / POST / PUT / DELETE）と URL を使ってリソースを操作する設計スタイル。データ形式は主に JSON。
- **Spring Boot とは**: Java で REST API を素早く構築できる Web フレームワーク。面倒な設定（ルーティングや DB 接続など）を自動化してくれる。

### 環境構築

- Java: OpenJDK 25 (インストール済みを確認)
- ビルドツール: Maven（Maven Wrapper `mvnw` を利用、ローカルに Maven 本体をインストールせずに実行可能）
- Spring Initializr (`start.spring.io`) を使ってプロジェクト雛形を作成
  - Spring Boot バージョン: 4.1.1
  - 依存関係: `spring-web`（REST API 構築用）

### 動作確認：Hello World API

`HelloController` を作成し、`/hello` エンドポイントを実装。

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
```

アプリを起動し、以下のリクエストで正常にレスポンスが返ることを確認済み。

```
$ curl http://localhost:8080/hello
Hello, Spring Boot!
```

→ **Spring Boot アプリの起動から REST API の疎通確認まで完了。**

### 今後の学習ロードマップ（本日の残り時間 / 明日以降）

| 項目 | 内容 |
|---|---|
| アノテーションの基本 | `@RestController` `@GetMapping` `@PostMapping` `@PathVariable` `@RequestBody` の役割を理解する |
| CRUD API の実装 | メモリ上のリスト or H2 インメモリDB を使い、一覧取得・作成・更新・削除の4本のAPIを実装する |
| リクエスト/レスポンスの型 | DTO クラス（POJO）を作り、JSON との自動変換を体験する |
| 動作確認方法 | curl または Postman でAPIを叩いて確認する習慣をつける |
| （余裕があれば）DB接続 | Spring Data JPA + H2 で簡単な永続化を試す |

### 参考

- Spring Initializr: https://start.spring.io
- Spring Boot 公式リファレンス: https://docs.spring.io/spring-boot/
