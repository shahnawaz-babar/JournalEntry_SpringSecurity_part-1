📘 Project Development Steps

Step 1 :- First we add Spring Security Depenedency 
<dependency>
<groupId>or.springframework.boot</groupId>
<artifactId>spring-boot-starter-security<artifactId/>
</dependecy</>

Step 2 :- when we apply this dependdency all end points will be secure by deafault
-> random passsowrd is generate on console 
-> By default Spring security uses Http Basic authenticatin
-> when we add this password on authentiaction and in name user than we can see response of endpoints 
-> the client send an authorisaiton header , Authorisation:Basic<encodedString> , the server decode the string , and extract username and password

Step 3 :- 
