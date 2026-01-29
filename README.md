# Employee Management System (社員管理システム)

Spring Boot と Docker を使用して構築した、社員情報を管理するWebアプリケーションです。
基本的なCRUD操作（登録、参照、更新、削除）を実装しており、データベース環境の構築に Docker Compose を採用しています。

## 機能一覧 (Features)

* **社員一覧表示:** 登録されている全社員をリスト表示します。
* **新規登録:** 名前、部署、Emailを入力して社員をデータベースに保存します。
* **情報編集:** 既存の社員情報を更新します。
* **削除機能:** 社員情報を削除します（確認ダイアログ付き）。
* **Docker連携:** `docker-compose` コマンド一つでDB環境を構築可能です。

## 学んだこと (Learning Outcomes)

このプロジェクトを通じて、以下の技術概念と開発フローを習得しました。

* **Dockerによる環境構築のメリット**
    * PCに直接MySQLをインストールせず、`docker-compose.yml` だけでDB環境を立ち上げる手法を学びました。
    * アプリケーション（Java）とインフラ（Docker）が疎結合になっており、接続先（ポート設定）さえ合えば連携できる仕組みを理解しました。
* **Gitによるブランチ運用 (Git Flow)**
    * `main` ブランチに直接コミットせず、機能ごとに `feature` ブランチを切って開発し、完成後にマージするという実務的な開発フローを実践しました。
* **Spring Data JPAの生産性**
    * JavaのEntityクラスを作成するだけで、DB上にテーブルが自動生成される仕組み（ORM）を活用し、SQLを書かずにデータ操作を行う効率的な開発を体験しました。
* **トラブルシューティング能力**
    * ポート競合（8080番問題）が発生した際に、ログを確認して原因を特定し、ポート番号を変更して解決するプロセスを経験しました。

## 使用技術 (Tech Stack)

* **言語:** Java 21
* **フレームワーク:** Spring Boot 4.0.2
    * Spring Web (MVC)
    * Spring Data JPA (Hibernate)
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
├── repository/   データベース操作 (EmployeeRepository)
└── entity/       データベースのテーブル定義 (Employee)

src/main/resources
├── templates/    HTMLファイル (Thymeleaf)
└── application.properties 設定ファイル
```
