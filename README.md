🔐 SECURESPHERE Mail AuthAuthentication & Email Verification System

A secure user authentication system built using Spring Boot that integrates email verification, JWT-based authentication, and Redis caching for temporary OTP storage.

🛠️ Tech Stack
Component	Technology
Backend Framework	Java, Spring Boot
Authentication	JWT (JSON Web Token)
Database	MySQL (Spring Data JPA)
Caching	Redis (for OTP & session caching)
Security	Spring Security, BCrypt Password Encryption
API Style	REST (Spring MVC)
Mail Service	Spring Mail (SMTP)
📌 Features

✔ User Registration & Log in with Secure Password Hashing
✔ Email Verification using OTP
✔ JWT-based Login & Authorization
✔ Role-based Access Control
✔ Redis for Fast OTP Storage & Expiry Handling
✔ MySQL for Persistent User Data
✔ Resend OTP & Expiry Validation
✔ Secure API Endpoints with Spring Security

🧩 System Flow
User Sign-Up → OTP sent via Email → User Verifies OTP
 → JWT Token Generated → Access to Secure APIs

📑 API Endpoints
Method	Endpoint	Description
POST	/auth/register	Register User & Send OTP
POST	/auth/verify-otp	Verify Email via OTP
POST	/auth/login	Login using Email & Password (JWT returned)
GET	/user/profile	Get User Profile (Requires JWT)
POST	/auth/resend-otp	Resend Verification OTP
🗃️ Database Structure
User Table
Field	Type	Description
id	BIGINT	Primary Key
email	VARCHAR	Unique Email
password	VARCHAR	Encrypted Hash
verified	BOOL	Email Verified?
role	ENUM	USER / ADMIN
Redis Storage
Key	Value	Expiry
OTP:<email>	6-digit OTP	2–5 Minutes
🚀 Installation & Setup
1️⃣ Clone the Repository
git clone https://github.com/your-username/securesphere-mailauth.git
cd securesphere-mailauth

2️⃣ Configure MySQL & Redis in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/users
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-mail-app-password

spring.redis.host=localhost
spring.redis.port=8080

3️⃣ Install Redis (if not installed)
sudo apt install redis-server
redis-server

4️⃣ Run the Application
mvn spring-boot:run

🔒 Security Highlights

Passwords stored with BCrypt encryption

JWT tokens used for session-less authentication

OTP stored in Redis with expiry

Secured endpoints with Spring Security

📂 Folder Structure
src/main/java/com/securesphere
│── controller
│── service
│── repository
│── security
│── model
│── dto
└── exception
🤝 Contribution

Contributions are welcome! Feel free to submit issues or pull requests.
