# Retail Manager API (Fully Restful API) ![Build](https://travis-ci.org/aziz781/retail-api.svg?branch=master)
API at `Level 3` of Richardson Maturity Model


## BUILD & RUN
```
mvn package && java -jar target/retail-api.jar

```

## Base URL:
```
http://localhost:8081/retail
```


## Swagger UI URL
 ```
http://localhost:8081/retail/swagger-ui.html#/shops-controller
 ```


## ENDPOINTS
```
GET /api/v1/shops/ HTTP/1.1

http://localhost:8081/retail/api/v1/shops/

```

```
GET /api/v1/shops/nearby?customerLatitude=77.6624264&customerLongitude=12.8383616 HTTP/1.1

http://localhost:8081/retail/api/v1/shops/nearby?customerLatitude=77.6624264&customerLongitude=12.8383616
```


```
GET /api/v1/shops/{shopName} HTTP/1.1

http://localhost:8081/retail/api/v1/shops/{shopName}

```


```

POST /api/v1/shops/ HTTP/1.1

http://localhost:8081/retail/api/v1/shops/

```

POST Request body:
```
       {
			"shopName": "Shop231",
			"shopAddress": {
				"number": 296,
				"postcode": 333
			}
		}
```


# Technologies
##  Spring Boot, Spring MVC , RESTful Services, Swagger2, Mockito
```
Spring Boot 1.3.6.RELEASE
Spring MVC
Google Maps GeoLocation API
swagger2
Junit 4
Mockito
```
