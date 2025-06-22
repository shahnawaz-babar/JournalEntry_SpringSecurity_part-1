📘 Project Development Steps
Step 1: Add Spring Security Dependency
First, add the Spring Security dependency in your pom.xml file:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>


Step 2: Understand Default Security Behavior
-> When you add the Spring Security dependency, all endpoints are secured by default.
-> A random password is generated at application startup and displayed in the console.
-> By default, Spring Security uses HTTP Basic Authentication.

You can authenticate by using:
Username: user  
Password: (console-generated password)

The client sends an Authorization header:
Authorization: Basic <encodedString>
The server decodes this string to extract the username and password.

Step 3: Create Security Configuration
-> Create a config package.
-> Inside this package, create a SpringSecurityConfig class.
-> Annotate the class with:

@Configuration  
@EnableWebSecurity

-> Create a method that returns SecurityFilterChain, e.g.:

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
    // configure security
}

Step 4: Session Management
Even though Spring Security is stateless by default (especially in REST APIs), it manages authentication across requests using:
-> Session creation
-> Session cookies
-> Session context
-> Session timeout
-> Session end
-> Remember-me functionality (if enabled)

Step 5: Set Up Authentication
To enable custom authentication, follow these steps:
✅ Create a User entity to represent user data.
✅ Create a UserRepository to interact with your database (e.g., MongoDB).
✅ Implement UserDetailsService to load user details:

UserDetailsService is an interface with:
UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
This method fetches the user by username.

Step 6: Complete Security Configuration
-> Configure password encoding using a PasswordEncoder bean (e.g., BCryptPasswordEncoder).
-> Disable CSRF if you're building a stateless REST API:
http.csrf().disable();


Step 7: Protect Endpoints
-> Add authentication to sensitive endpoints like @DeleteMapping and @PutMapping.
-> Ensure that only authenticated users can perform delete or update operations.

Step 8: Create a Registration Controller
Create a separate controller to allow new users to register.

Example:

@PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody User user) {
    // save user logic
}
