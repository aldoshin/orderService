# orderService
microservice that uses productService 

# Chnage the url for local ip address while running

```
@FeignClient(name = "product-service", url = "10.0.0.221:8001")
public interface ProductServiceProxy {
```

### Docker postgeSQL container 

```docker run -d -p 5432:5432 --name my-postgres -e POSTGRES_PASSWORD=mysecretpassword postgres```

# For Order service https://www.javacodegeeks.com/2018/11/spring-microservices-docker-kubernetes-2.html


```
docker exec -it my-postgres bash
CREATE DATABASE orders_db;
GRANT ALL PRIVILEGES ON DATABASE orders_db TO postgres;
```
