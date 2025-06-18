# 🎨 mutlukodcu_j17_chart_color_microservice

Modern, modüler ve Kafka destekli bir mikroservis uygulamasıdır. Kullanıcılar:
- Renk bazlı CRUD işlemleri gerçekleştirebilir
- Kendi kişisel galerilerine fotoğraf ekleyebilir
- Seçilen renge göre eşleşen fotoğrafları görebilir
- Bu fotoğrafların içerdiği tüm renkleri analiz edip istatistiksel grafiklerle görüntüleyebilir
- Grafiklerini Excel formatında renkli olarak indirebilir

## 🚀 Teknolojiler

- Java 17 + Spring Boot
- ReactJS + Bootstrap 5
- PostgreSQL + Hibernate (JPA)
- Kafka (Producer-Consumer)
- Docker + Docker Compose
- Spring Security + JWT + Role Management
- Apache POI (Excel Export)
- Swagger (API Dokümantasyonu)

## 🧩 Mikroservisler

| Servis | Açıklama |
|-------|----------|
| `auth-service` | Giriş, çıkış, kayıt, JWT, kullanıcı rolleri |
| `color-service` | Renk CRUD, Kafka producer |
| `photo-gallery-service` | Fotoğraf yükleme, kategori yönetimi, renk eşleşme |
| `color-analysis-consumer` | Kafka consumer, renk analiz servisi |
| `shared-lib` | Ortak DTO, exception, response modelleri |
| `frontend` | React arayüz: grafikler, yükleme, istatistik |

## 🖼 Özellikler

- 🔐 JWT tabanlı güvenli giriş/çıkış
- 🖌 Renk CRUD + grafiksel analiz
- 📷 Fotoğraf yükle/görüntüle/indir/güncelle
- 🎯 Renk eşleşmesiyle ilgili fotoğrafları listeleme
- 📊 PieChart analizleri (fotoğraf içi renk yoğunlukları)
- 📁 Renkli `.xlsx` çıktısı indir
- 👥 Yetki yönetimi (USER / ADMIN)
- 🐳 Docker + Kafka + PostgreSQL tümleşik yapı
- 🔍 Swagger ile test edilebilir REST API

## 🛠 Kurulum

1. Docker ile Kafka ve veritabanlarını başlat:

```bash
docker-compose -f infra/docker/docker-compose.yml up
```

2. IntelliJ üzerinden servisleri başlat:
   - `auth-service`
   - `color-service`
   - `photo-gallery-service`
   - `color-analysis-consumer`

3. Frontend klasörüne girip başlat:
```bash
cd frontend
npm install
npm start
```

4. Swagger arayüzüne gidin:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## 🧪 Test Kullanıcısı
```
email: test@mutlukodcu.com
şifre: 123456
rol: USER
```

## 📂 Çıktılar
- React tabanlı renkli grafikler
- Excel dosyası içinde PieChart'lı renk yoğunluk raporları
- Kategorilere göre galeriler (doğa, hayvanlar, bitkiler, mimari)

## 📌 Geliştirilebilir Alanlar
- 📩 E-posta bildirimleri
- ☁️ Bulut tabanlı fotoğraf saklama (S3/MinIO)
- 📈 Renk trend analitiği
