# 🛍️ Inditex Pricing API

Este proyecto es una prueba técnica para Inditex, cuyo objetivo es construir un servicio REST con Java 17 y Spring Boot que devuelva el precio aplicable de un producto según la marca, fecha y hora de consulta.

## 📌 Objetivo del Proyecto

Diseñar una API que permita consultar el **precio final aplicable** para un producto, marca y fecha/hora dados, siguiendo principios de:

- Arquitectura **hexagonal**
- **Domain-Driven Design (DDD)**
- **Clean Code** y principios **SOLID**
- Buenas prácticas REST
- Pruebas unitarias e integración
- Cobertura de tests y control de errores
- Preparado para **CI/CD** (Extra)

## ⚙️ Tecnologías usadas

- Java 17
- Spring Boot 3
- Spring Web / JPA
- Lombok
- H2 Database (en memoria)
- Maven
- JUnit 5
- JaCoCo (para cobertura de tests)
- GitHub Actions (opcional para CI/CD)

## 🧱 Arquitectura (Hexagonal + DDD)

```
com.inditex.api
├── domain             → Modelo de dominio y puertos
├── application        → Casos de uso (servicios de aplicación)
├── infrastructure
│   ├── controller     → Adaptador REST
│   ├── repository     → Adaptador JPA (acceso a datos)
│   └── config         → Manejo global de excepciones
├── mapper             → Conversores entre capas
└── InditexPriceApplication.java
```

## 📦 Modelo de dominio principal

```java
Price {
  Long brandId;
  LocalDateTime startDate;
  LocalDateTime endDate;
  Integer priceList;
  Long productId;
  Integer priority;
  Double price;
  String currency;
}
```

## 🔌 Endpoint principal

### `GET /api/prices`

Consulta el precio aplicable según fecha, marca y producto.

#### 🔸 Parámetros (query):
- `brandId`: ID de la marca (ej. `1` para ZARA)
- `productId`: ID del producto (ej. `35455`)
- `applicationDate`: Fecha y hora en formato ISO (ej. `2020-06-14T10:00:00`)

#### 🔸 Ejemplo de llamada:

```bash
curl -X GET "http://localhost:8080/api/prices?brandId=1&productId=35455&applicationDate=2020-06-14T16:00:00"
```

#### 🔸 Respuesta esperada:

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45,
  "currency": "EUR"
}
```

## ✅ Casos de prueba implementados

| Test # | Fecha y hora de consulta | Producto | Marca | Resultado esperado |
|--------|---------------------------|----------|--------|--------------------|
| Test 1 | 2020-06-14 10:00:00       | 35455    | 1 (ZARA) | Tarifa 1           |
| Test 2 | 2020-06-14 16:00:00       | 35455    | 1        | Tarifa 2           |
| Test 3 | 2020-06-14 21:00:00       | 35455    | 1        | Tarifa 1           |
| Test 4 | 2020-06-15 10:00:00       | 35455    | 1        | Tarifa 3           |
| Test 5 | 2020-06-16 21:00:00       | 35455    | 1        | Tarifa 4           |

## 🧪 Testing

- Tests unitarios para lógica de aplicación
- Test de integración para endpoint completo
- Cobertura de código con **JaCoCo**

Ejecutar tests:

```bash
mvn test
```

Ver cobertura:

```
target/site/jacoco/index.html
```

## 🛑 Manejo de errores

| Código | Motivo                   | Tipo de error                  |
|--------|--------------------------|--------------------------------|
| 200    | OK                       | Respuesta correcta             |
| 400    | Bad Request              | Formato incorrecto o inválido  |
| 404    | Price Not Found          | No se encontró tarifa válida   |
| 500    | Internal Server Error    | Error inesperado del servidor  |

Todas las respuestas de error devuelven un JSON con `timestamp`, `error`, `code` y `message`.

## 📂 Base de datos H2

Se utiliza una base **en memoria** con datos precargados desde `data.sql`.

Consola H2 disponible en:

```
http://localhost:8080/h2-console
```

Parámetros:

- **JDBC URL**: `jdbc:h2:mem:inditexdb`
- **Username**: `sa`
- **Password**: *(vacío)*

## 🚀 (Opcional) CI/CD

Incluye un ejemplo básico de CI con GitHub Actions en `.github/workflows/ci.yml`.

Ejecuta:
- Build del proyecto
- Tests automáticos

## 📄 Licencia

Este proyecto es parte de una prueba técnica y su uso está limitado al ámbito evaluativo.