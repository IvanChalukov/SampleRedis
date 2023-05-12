# Sample Redis project

## Setup local Redis server with enabled ssl
1. Pull docker image
    ```sh
    docker pull redis
    ```
2. Create certificate for redis
    ```sh
    openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365 -nodes
    ```
3. Build own docker image from `redis`
    ```shell
    docker build -t redis-ssl .
    ```
4. Start docker container
    ```shell
   docker run -p 6380:6380 --name redis-ssl -v $(pwd)/redis.conf:/usr/local/etc/redis/redis.conf -v $(pwd)/cert.pem:/etc/ssl/cert.pem -v $(pwd)/key.pem:/etc/ssl/key.pem -e REDIS_PASSWORD=Abcd@1234 -d redis-ssl redis-server /usr/local/etc/redis/redis.conf --tls-port 6380 --tls-cert-file /etc/ssl/cert.pem --tls-key-file /etc/ssl/key.pem --tls-auth-clients no --requirepass Abcd@1234    
   ```
## Test with redis-cli
1. Install redis-cli
2. Import create certificate into cacerts
    ```shell
    keytool -import -alias sample -file cert.pem -keystore /path/to/java/lib/security/cacerts
    ```
3. Test with `redis-cli`
    ```shell
    redis-cli --tls --cacert cert.pem -h localhost -p 6380 -a Abcd@1234
    ```
   
## Test with java application
1. Build with maven
    ```shell
    mvn clean install
    ```
2. Make sure all properties are set correctly in `application.properties`
3. Run java application
```shell

```