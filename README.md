# Employee Management System (社員管理システム)

Spring Boot と Docker を使用して構築した、社員情報を管理するWebアプリケーションです。
実務を意識し、**CRUD操作**、**DBリレーション**に加え、**Spring Securityによる本格的なログイン認証・権限管理**まで実装しています。

## 機能一覧 (Features)

* **ユーザー認証・権限管理**
    * **ログイン機能:** Spring Security を使用したフォーム認証を実装。
    * **権限の使い分け:**
        * **管理者 (ADMIN):** データの登録・編集・削除が可能。
        * **一般 (USER):** データの削除が不可。
    * **安全なパスワード管理:** BCryptアルゴリズムによるパスワードのハッシュ化（暗号化）保存。
* **社員一覧表示・検索:**
    * 登録されている社員を一覧表示します。
    * **検索機能:** 社員名による部分一致検索が可能です。
* **新規登録・編集:**
    * **部署選択:** 部署マスタ（DB）から取得した選択肢をプルダウンで表示します。
    * **バリデーション:** 必須入力やメール形式のチェックを行い、不正なデータ入力を防ぎます。
* **削除機能:** 管理者権限を持つユーザーのみ削除が可能です。
* **初期データ投入:** アプリ起動時に、必須の部署マスタや初期ユーザー（Admin/User）を自動生成します。
* **Docker連携:** `docker-compose` コマンド一つでMySQL環境を構築可能です。

## 学んだこと (Learning Outcomes)

このプロジェクトを通じて、以下の技術概念と開発フローを習得しました。

### 1. セキュリティとアクセス制御 (Spring Security)
* **認証 (Authentication) と 認可 (Authorization):**
    * 「誰かを確認する（ログイン）」と「何をしていいか決める（権限）」の違いを理解し、実装しました。
* **動的な画面制御:**
    * Thymeleaf Security Extras (`sec:authorize`) を使用し、ログインユーザーの権限に応じて「削除ボタンを表示/非表示」にするなどの制御を行いました。
* **セキュリティ設定:**
    * `SecurityFilterChain` を用いて、特定のリソース（CSSなど）へのアクセス許可や、ログイン必須ページのルールをコードで記述しました。

### 2. 堅牢なデータベース設計とJPA
* **リレーション（多対1）の実装:**
    * 社員テーブルと部署テーブルを分離し、`@ManyToOne` を使用してオブジェクト指向的にデータを結合する方法を学びました。
* **検索機能の実装:**
    * Spring Data JPAの命名規則（`findByNameContaining`）を利用し、SQLを直接書かずに柔軟な検索機能を実装しました。

### 3. 開発環境とフロー
* **Dockerによる環境構築:**
    * `docker-compose.yml` を記述し、開発チーム全体で統一されたDB環境を即座に再現できる構成にしました。
* **Git Flowの実践:**
    * 機能追加ごとに `feature` ブランチを作成し、動作確認後に `main` へマージする実務的な開発フローを徹底しました。

## 使用技術 (Tech Stack)

* **言語:** Java 21
* **フレームワーク:** Spring Boot 4.0.2
    * Spring Security (認証・認可)
    * Spring Web (MVC)
    * Spring Data JPA (Hibernate)
    * Spring Validation (入力チェック)
    * Thymeleaf (テンプレートエンジン) + Thymeleaf Extras Spring Security
* **データベース:** MySQL 8.0 (Dockerコンテナで動作)
* **フロントエンド:** Bootstrap 5
* **インフラ/環境:** Docker, Docker Compose
* **ツール:** Maven, Git

## 環境構築と起動方法 (How to Run)

### 前提条件
* Java 21 がインストールされていること
* Docker Desktop がインストールされ、起動していること

### 1. リポジトリのクローン
```bash
git clone https://github.com/NK0807/employee-manager.git
cd employee-manager
```

### 2. データベースの起動 (Docker)
プロジェクト直下で以下のコマンドを実行し、MySQLサーバーを立ち上げます。
※ ポート **3307** を使用します。

```bash
docker compose up -d
```
> **Note:** 初期データ（管理者ユーザーなど）を反映させるため、初回は必ずコンテナを立ち上げてください。

### 3. アプリケーションの起動
EclipseなどのIDEから `EmployeeManagerApplication.java` を実行するか、以下のコマンドで起動します。

```bash
./mvnw spring-boot:run
```

### 4. ブラウザでアクセス & ログイン
起動後、以下のURLにアクセスしてください。

* **URL:** http://localhost:9000/employees

**【ログイン情報 (テスト用)】**
アプリ起動時に以下のユーザーが自動作成されます。

| ユーザー名 | パスワード | 権限 (Role) | 可能な操作 |
| :--- | :--- | :--- | :--- |
| **admin** | `password` | **ADMIN** | 登録・閲覧・編集・**削除** |
| **user** | `password` | **USER** | 登録・閲覧・編集 (削除不可) |

## ディレクトリ構成

```text
src/main/java/com/example/employee_manager
├── config/       セキュリティ設定 (SecurityConfig)
├── controller/   画面遷移と入力を制御 (EmployeeController)
├── service/      ビジネスロジック (EmployeeService, UserDetailsServiceImpl)
├── repository/   データベース操作 (EmployeeRepository, UserRepository...)
├── entity/       データベース定義 (Employee, Department, User)
└── DataLoader.java  初期データ投入用プログラム

src/main/resources
├── templates/    HTMLファイル (Thymeleaf)
└── application.properties 設定ファイル
```
