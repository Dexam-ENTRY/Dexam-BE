# Dexam

학원 수행평가·시험 일정을 한눈에 관리하는 웹 서비스 백엔드입니다.

## Tech Stack

| 구분 | 기술 |
| --- | --- |
| Language | Java 21 |
| Framework | Spring Boot 4.1.0 |
| Security | Spring Security 7.1, JWT (jjwt 0.12.6), OAuth 2.0 (Google) |
| Persistence | Spring Data JPA, MySQL |
| Cache / Session | Spring Data Redis (Lettuce) |
| API Docs | Swagger (springdoc-openapi 2.8.9) |
| Build | Gradle |

## Architecture

```
com.entry.dexam
├── domain
└── global
    ├── annotations  # @CurrentUserId 등 커스텀 어노테이션
    ├── dto          # 공통 응답 (ApiResponse)
    ├── entity       # 공통 엔티티
    ├── exception    # 전역 예외 처리
    ├── redis        # Redis 설정
    └── security     # 인증/인가 · JWT · OAuth
```

## Roles

| Role | 설명 |
| --- | --- |
| `USER` | 일반 사용자 |
| `CLASS_ADMIN` | 반 관리자 |
| `ADMIN` | 최고 관리자 |

## Features

### 회원 · 인증

| 기능 | 설명 |
| --- | --- |
| 회원가입 | Google OAuth 2.0 소셜 로그인으로 자동 가입 |
| 로그인 | OAuth 2.0 인증 후 Access / Refresh Token 발급 |
| 로그아웃 | Redis에 저장된 Refresh Token 삭제 |
| 프로필 조회 | 현재 로그인한 사용자 정보 조회 |
| 프로필 수정 | 반(클래스) 정보 변경 |
| 회원 탈퇴 | 계정 삭제 |

### 권한 · 관리

| 기능 | 설명 |
| --- | --- |
| 사용자 목록 조회 | 사용자 목록 확인 |
| 권한 변경 | 사용자 Role 변경 |
| 권한별 접근 제어 | Role 기반 엔드포인트 접근 제어 |
| 공지 작성 / 조회 / 수정 / 삭제 | 공지사항 CRUD |

### 평가

| 기능 | 설명 |
| --- | --- |
| 등록 내역 조회 | 기간별 등록 내역 조회 |
| 등록 내역 삭제 | 등록 내역 삭제 |
| 수행평가 목록 조회 | 수행평가 유형별 목록 조회 |
| 수행 상세 조회 | 수행평가 상세 확인 |
| 시험 목록 조회 | 시험 목록 확인 |
| 시험 상세 조회 | 시험 상세 확인 |

## Getting Started

### Requirements

- Java 21
- MySQL
- Redis

### Configuration

`.env` 파일을 프로젝트 루트에 생성하고 환경 변수를 설정합니다.

```properties
DB_HOST=localhost
DB_PORT=3306
DB_NAME=dexam
DB_USERNAME=root
DB_PASSWORD=your_password

REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=your_password

JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION=3600000

GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
FRONTEND_CALLBACK_URL=http://localhost:5173
```

### Run

```bash
./gradlew bootRun
```

### API Docs

서버 실행 후 Swagger UI 접속

```
http://localhost:8080/swagger-ui/index.html
```
