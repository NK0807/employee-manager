# Employee Management System (社員管理システム)

Spring Boot と Docker を使用して構築した、社員情報を管理するWebアプリケーションです。
基本的なCRUD操作に加え、実務を意識した**バリデーション（入力チェック）**や**検索機能**、**リレーショナルデータベース設計（テーブル結合）**を取り入れています。

## 機能一覧 (Features)

* **社員一覧表示・検索:**
    * 登録されている社員を一覧表示します。
    * **検索機能:** 社員名による部分一致検索が可能です。
* **新規登録:**
    * 名前、Email、部署を登録します。
    * **部署選択:** 部署マスタ（DB）から取得した選択肢をプルダウンで表示します。
    * **バリデーション:** 必須入力やメール形式のチェックを行い、不正なデータ入力を防ぎます。
* **情報編集:** 既存の社員情報を更新します（バリデーション対応）。
* **削除機能:** 社員情報を削除します（確認ダイアログ付き）。
* **初期データ投入:** アプリ起動時に、部署マスタのデータがない場合は自動で初期データを登録します。
* **Docker連携:** `docker-compose` コマンド一つでMySQL環境を構築可能です。

## 学んだこと (Learning Outcomes)

このプロジェクトを通じて、以下の技術概念と開発フローを習得しました。

### 1. 堅牢なデータベース設計とJPA
* **リレーション（多対1）の実装:**
    * 社員テーブルと部署テーブルを分離し、`@ManyToOne` を使用してオブジェクト指向的にデータを結合する方法を学びました。
    * これにより、部署名の表記ゆれ（営業部、営業など）を防ぐ正規化の重要性を理解しました。
* **検索機能の実装:**
    * Spring Data JPAの命名規則（`findByNameContaining`）を利用し、SQLを直接書かずに柔軟な検索機能を実装しました。

### 2. データの整合性と安全性
* **バリデーション:**
    * `@NotBlank` や `@Email` アノテーションを使用し、サーバーサイドでの入力チェックを実装しました。
    * エラー時にフォームへメッセージを表示するThymeleafの制御方法を習得しました。

### 3. 開発環境とフロー
* **Dockerによる環境構築:**
    * `docker-compose.yml` を記述し、チームメンバーが誰でも同じDB環境を即座に再現できる構成にしました。
* **Git Flowの実践:**
    * 機能追加ごとに `feature` ブランチを作成し、動作確認後に `main` へマージする実務的な開発フローを徹底しました。

## 使用技術 (Tech Stack)

* **言語:** Java 21
* **フレームワーク:** Spring Boot 4.0.2
    * Spring Web (MVC)
    * Spring Data JPA (Hibernate)
    * Spring Validation (入力チェック)
    * Thymeleaf (テンプレートエンジン)
* **データベース:** MySQL 8.0 (Dockerコンテナで動作)
* **フロントエンド:** Bootstrap 5
* **インフラ/環境:** Docker, Docker Compose
* **ツール:** Maven, Git

## 環境構築と起動方法 (How to Run)

このアプリケーションはデータベースに Docker コンテナを使用しています。

### 前提条件
* Java 21 がインストールされていること
* Docker Desktop がインストールされ、起動していること

### 1. リポジトリのクローン
```bash
git clone [https://github.com/NK0807/employee-manager.git](https://github.com/NK0807/employee-manager.git)
cd employee-manager
```

### 2. データベースの起動 (Docker)
プロジェクト直下で以下のコマンドを実行し、MySQLサーバーを立ち上げます。
※ ポート **3307** を使用します。

```bash
docker compose up -d
```

> **Note:** 以前のバージョンのDBデータが残っていると起動に失敗する場合があります。その際は `docker compose down -v` で一度データをリセットしてください。

### 3. アプリケーションの起動
EclipseなどのIDEから `EmployeeManagerApplication.java` を実行するか、以下のコマンドで起動します。

```bash
./mvnw spring-boot:run
```

### 4. ブラウザでアクセス
起動後、以下のURLにアクセスしてください。

* **URL:** http://localhost:9000/employees
* (トップページ http://localhost:9000 にアクセスすると自動でリダイレクトされます)

## ディレクトリ構成

```text
src/main/java/com/example/employee_manager
├── controller/   画面遷移と入力を制御 (EmployeeController)
├── service/      ビジネスロジック (EmployeeService)
├── repository/   データベース操作 (EmployeeRepository, DepartmentRepository)
├── entity/       データベース定義 (Employee, Department)
└── DataLoader.java  初期データ投入用プログラム

src/main/resources
├── templates/    HTMLファイル (Thymeleaf)
└── application.properties 設定ファイル
```
