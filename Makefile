.PHONY: start build install backend-start frontend-start

install:
	cd frontend && npm install

build:
	cd frontend && npm run build
	cd backend && ./mvnw clean package -DskipTests

backend-start:
	cd backend && ./mvnw spring-boot:run

frontend-start:
	cd frontend && npm start

start:
	@echo "Run backend-start and frontend-start in separate terminals"
