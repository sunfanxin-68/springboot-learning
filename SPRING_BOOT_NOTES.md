# Spring Boot 学習記録

## 2026-09-10（Day 1）

### 目的
Java / Spring Boot を使った REST API 開発の基礎を習得する。

## 1. 今日学んだこと（概念）

### 1.1 API とは？

- **概要：** ソフトウェア同士がデータをやり取りするための窓口・契約。
- **メリット：** 画面（フロントエンド）と処理（バックエンド）を分離できる。

### 1.2 REST API とは？

- **概要：** Web上でデータをやり取りするための世界共通の「設計ルール・マナー」。
- **HTTPメソッド（操作の指定）**
    - `GET`：データの取得（検索）
    - `POST`：データの新規作成
    - `PUT` / `PATCH`：データの更新
    - `DELETE`：データの削除
- **JSON（データフォーマット）：** REST API では、データの受送信にテキスト形式の `JSON`（JavaScript Object Notation）を使用するのが標準的です。

### 1.3 REST API と RESTful API の違い

- **REST API：** 名詞（具体的なAPIそのもの）。
- **RESTful：** 形容詞（RESTのルールにしっかり沿って設計されている状態）。

### 1.4 Spring Boot とは？

- **概要：** JavaでWebアプリやAPIを素早く開発するための「フレームワーク（開発ツール）」。
- **特徴：** Tomcat（Webサーバー）が内蔵されており、面倒な設定なしですぐ動かせる。

### 1.5 REST API と Spring Boot の関係性

- **役割分担：** REST API は「通信ルールの設計図」、Spring Boot は「そのルール通りに動かすJavaコードのエンジン」。

## 2. Spring Boot の基本構造とアノテーション

### 2.1 アノテーション（Annotation）とは？

- **概要：** `@` から始まる、プログラムの役割や動作を Spring に伝える「目印・指示書」。

### 2.2 コントローラー（APIの窓口）関連

- **`@RestController` と `@Controller` の違い**
    - `@Controller`：HTML（画面）を返す古い方式。
    - `@RestController`：JSON（純粋なデータ）を返す現代の標準方式。
- **リクエストの受け取り（HTTPメソッド）**
    - `@GetMapping` / `@PostMapping` / `@PutMapping` / `@DeleteMapping`
- **値の取得方法**
    - `@PathVariable`（URLパスから取得：`/users/1`）
    - `@RequestParam`（クエリ文字列から取得：`/users?id=1`）
    - `@RequestBody`（JSONデータをJavaオブジェクトに変換）

### 2.3 アーキテクチャと DI（依存性注入）関連

- **3層アーキテクチャ（役割分担の原則）**
    1. **Controller（窓口）：** リクエスト受取・レスポンス返却
    2. **Service（ビジネスロジック）：** メインの処理・計算
    3. **Repository（DB操作）：** データベースとのやり取り
- **DI用アノテーション**

| **アノテーション** | **役割・説明** |
| --- | --- |
| `@Service` | ビジネスロジックを担当するクラスに付与します。 |
| `@Repository` | データベース操作を担当するクラスに付与します。 |
| `@Autowired` | Springが管理するオブジェクトを自動で注入（DI）します。 |
| `@Component` | 汎用的なSpring管理対象のクラス（Bean）として登録します。 |

## 3. データベース連携（JPA / Entity）の基本

### 3.1 Entity（DBテーブルとJavaクラスの紐付け）

- **`@Entity` / `@Table`：** クラスとDBテーブルを対応付ける。
- **`@Id` / `@GeneratedValue`：** 主キー（PK）と自動採番（Auto Increment）の設定。
- **`@Column`：** カラム名や制約（NOT NULL など）の設定。

### 3.2 Repository（DB操作の共通化）

- `JpaRepository` を継承することで、SQLを書かずに `findAll()`, `findById()`, `save()` などが使える仕組み。

## 4. REST API 開発の実践フロー

### 4.1 リクエストからレスポンスまでのデータの流れ

1. フロントエンドから HTTP リクエストが届く
2. `Controller` が受け取る
3. `Service` で処理する
4. `Repository` 経由で DB（`Entity`）にアクセス
5. 処理結果を JSON でフロントエンドに返す

### 4.2 DI（Dependency Injection：依存性注入）

- **仕組み：** `new Class()` を自分で書かず、Spring 容器にインスタンスの生成・管理を任せる仕組み。
- **役割分担：**
    - **提供側（作成）：** `@Service`, `@Repository`, `@Component`（Spring に「管理してね」と伝える）。
    - **利用側（注入）：** `@Autowired`（Spring に「管理しているオブジェクトをここにセットして」と伝える）。

### 4.3 ビルドツール（Maven / Gradle）

- **役割：** 外部ライブラリ（Spring Boot 自身や DB 接続用ツールなど）を自動ダウンロード・管理する「注文書」。
- **ファイル名：**
    - Maven の場合 ➔ `pom.xml`（`<dependency>` タグで追加）
    - Gradle の場合 ➔ `build.gradle`（`implementation` で追加）
- **使い方：** 新しい機能（Lombok や DB 接続など）を使いたい時だけ、指定のコードをコピペして保存する。

### 4.4 Spring Data JPA（データベース操作）

- **仕組み：** SQL（`SELECT * FROM ...`）を書かずに、Java のメソッドで DB を操作する仕組み。
- **使い方：** `JpaRepository<Entity名, IDの型>` を継承した Interface を作成するだけ。
- **標準メソッド：**
    - `findAll()` ➔ 全件取得（SELECT）
    - `findById(id)` ➔ 1件取得（SELECT）
    - `save(entity)` ➔ 新規保存・更新（INSERT / UPDATE）
    - `deleteById(id)` ➔ 削除（DELETE）
- **命名ルール（自動SQL生成）：** `findByEmail(String email)` のようにメソッド名を作るだけで、自動で条件検索 SQL が発行される。

### 4.5 DTO（Data Transfer Object）と Entity の使い分け

- **Entity（データベース直結）：**
    - DB のテーブル構造と 1 対 1 で対応するクラス。
    - パスワードや内部フラグなど、外部に見せたくないデータも含まれる。
- **DTO（通信用データ）：**
    - API のリクエスト受取・レスポンス返却専用のクラス。
    - 必要なデータだけを抽出・整形して送受信する。
- **なぜ分けるのか？**
    1. **セキュリティ：** パスワードなどの機密データ漏洩を防ぐため。
    2. **疎結合：** DB の構造が変わっても、API の仕様（画面に渡すデータ格式）を崩さないため。

## 5. 明日以降の学習予定

- 実践編として、Haskell で組んだ体組成計の API を、Java Spring Boot 版で簡単に作ってみる。
