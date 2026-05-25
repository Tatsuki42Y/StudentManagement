# 受講生管理システム（StudentManagement）

受講生情報と受講コース情報を管理するためのWebアプリケーションです。
RaiseTech Javaフルコースの学習課題として作成し、Javaバックエンド開発における主要な技術スタック（Spring Boot / MyBatis / MySQL / REST API / テスト / CI/CD / AWSデプロイ）を一通り学習・実践することを目的としています。

## 💻 使用技術

| カテゴリ | 技術スタック |
| :--- | :--- |
| **言語** | Java 21 |
| **フレームワーク** | Spring Boot, MyBatis |
| **データベース** | MySQL (RDS), H2 Database (テスト用) |
| **ビルドツール** | Gradle |
| **APIドキュメント**| Swagger / OpenAPI |
| **テスト** | JUnit 5, Mockito |
| **CI/CD** | GitHub Actions |
| **インフラ / 運用** | AWS (EC2, RDS, ALB), systemd |

## ⚙️ 主な機能

* **受講生管理 (CRUD + 検索)**
  * 受講生の新規登録 / 詳細・一覧取得 / 情報更新
  * **複数条件による受講生検索**（柔軟なデータ抽出が可能）
  * **論理削除の導入**（実務を意識し、データを物理削除せず削除フラグで制御）
* **受講コース管理**
  * 受講コース情報の登録
* **品質・堅牢性**
  * **バリデーション**による厳格な入力チェック
  * **例外ハンドリングの共通化**（例外発生時に一貫したエラーレスポンスを返却）
  * **JUnit / Mockitoによる自動テスト**（コントローラーやサービスのモックテスト）
* **デプロイ・自動化**
  * **Swagger UI**によるAPI仕様の可視化と動作確認
  * **GitHub Actions**を活用した、テスト・ビルド・AWS（EC2）へのデプロイ自動化

## 📂 ディレクトリ構成

本プロジェクトは、保守性と拡張性を高めるために、役割ごとにレイヤーを明確に分離した**レイヤードアーキテクチャ**を採用しています。

```text
StudentManagement/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── raisetech/
│   │   │       └── studentmanagement/
│   │   │           ├── Controller/       # リクエスト受付・画面制御
│   │   │           │   ├── converter/    # データ変換ロジック（Entity ⇄ DTO 等）
│   │   │           │   └── StudentController.java
│   │   │           ├── data/             # データベースのエンティティ（データモデル）
│   │   │           │   ├── Student.java
│   │   │           │   └── StudentsCourses.java
│   │   │           ├── domain/           # 業務ロジックで扱うコアとなるドメインオブジェクト
│   │   │           │   └── StudentDetail.java
│   │   │           ├── repository/       # データベース操作（MyBatis Mapper等）のインターフェース
│   │   │           │   └── StudentRepository.java
│   │   │           ├── service/          # ビジネスロジックを処理する層
│   │   │           │   └── StudentService.java
│   │   │           ├── ServletInitializer.java
│   │   │           └── StudentManagementApplication.java # アプリケーションの起動クラス
│   │   └── resources/
│   │       └── application.properties    # データベース接続などの環境設定ファイル
│   └── test/                             # JUnitによるテストコード
├── build.gradle                          # 依存関係・ビルド設定
└── settings.gradle


## 💡 こだわったポイント・学んだこと

### 🔧 実装における具体的な工夫
**受講生情報**
  * **実務を見据えた「論理削除」の採用**: 卒業や退会となった受講生の情報について、データベースから物理的に削除するのではなく、将来的なデータ分析や集計（受講生数の推移やコースごとの卒業生割合の算出など）に活用できるよう、削除フラグ（`isDeleted`）を用いた論理削除を採用しました。また、SQLの条件制御により「在校生（アクティブなユーザー）のみ」に絞り込んだ検索ロジックも合わせて実装しています。
  * **アノテーションを活用した厳格な入力バリデーション**: 不正なデータがデータベースに登録されるのを防ぐため、Bean Validationを活用した入力チェックを徹底しました。
    * **受講生ID (`id`)**: 予期せぬ文字列の混入を防ぐため、正規表現（`@Pattern(regexp = "^\\d+$")`）を用いて「数字のみ」の入力を許容する設計にしました。
    * **メールアドレス (`email`)**: `@Email` アノテーションを付与し、Eメールとして有効な形式（`@`の有無など）のみを受け付けるように制限しました。
