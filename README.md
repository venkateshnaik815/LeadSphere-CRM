# LeadSphere-CRM

LeadSphere CRM is a comprehensive Enterprise Customer Relationship Management system built with Java, Spring Boot, React, and PostgreSQL. It allows sales teams to manage their pipeline, leads, contacts, and companies seamlessly.

## Dependencies
- **Backend**: Java 21, Spring Boot 3.2.3, Spring Security, Spring Data JPA, H2 Database (in-memory mode for development), PostgreSQL, Flyway, Maven.
- **Frontend**: React 18, TypeScript, Vite, Tailwind CSS, Redux Toolkit, Headless UI, Heroicons.

## Installation
1. Clone the repository to your local machine.
2. Ensure you have Java 21 and Node.js v18+ installed.
3. Install frontend dependencies:
   ```bash
   cd frontend
   npm install
   ```
4. Backend dependencies are managed by Maven and will be downloaded automatically when running or building.

## Build
To build the frontend for production:
```bash
cd frontend
npm run build
```

To build the backend executable JAR:
```bash
cd backend
./mvnw clean package -DskipTests
```

## Run
To run the full stack locally for development:

**Start the Backend:**
```bash
cd backend
./mvnw spring-boot:run
```
The API will start on `http://localhost:8080`.

**Start the Frontend:**
```bash
cd frontend
npm start
```
The frontend UI will start on `http://localhost:5173`.

## Usage
1. Once both servers are running, open your browser and navigate to `http://localhost:5173`.
2. **Login Credentials**: The database automatically seeds an admin user.
   - **Email**: `admin@leadsphere.com`
   - **Password**: `password123`
3. Navigate to the **Sales Pipeline** to drag and drop deals across stages.
4. Use the **Leads**, **Contacts**, and **Companies** tabs to manage your CRM data.
5. Click **Add Opportunity** to create new deals in your pipeline.
