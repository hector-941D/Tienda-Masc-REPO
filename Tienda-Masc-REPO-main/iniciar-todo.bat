@echo off

echo Iniciando Servidor de Descubrimiento Eureka (Puerto 8761)...
start "Eureka" /D "./eureka" cmd /k "mvnw.cmd spring-boot:run"

echo Esperando 12 segundos a que Eureka se estabilice...
timeout /t 12 /nobreak > nul

echo Iniciando API Gateway...
start "Gateway" /D "./gateway" cmd /k "mvnw.cmd spring-boot:run"

echo Iniciando Microservicio Boletas...
start "Boletas" /D "./Boleta" cmd /k "mvnw.cmd spring-boot:run"

echo Iniciando Microservicio Clientes...
start "Clientes" /D "./Cliente" cmd /k "mvnw.cmd spring-boot:run"

echo Iniciando Microservicio Productos...
start "Productos" /D "./Producto" cmd /k "mvnw.cmd spring-boot:run"

echo Ecosistema lanzado. Dashboard disponible en http://localhost:8761