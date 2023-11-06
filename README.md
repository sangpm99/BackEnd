# How to use This Back End Project

## Clone project
Open terminal in IDE and run:

### `git clone https://github.com/sangpm99/BackEnd.git`

## Config DataBase
Open MySQL and create a new database with the name you want, here the default in project:
<br>
<b>MySQL</b>
<br>
database: <code>polyfood</code>
<br>
usename: <code>root</code>
<br>
password: <code>admin</code>
<br>
<i>To more detail see in: <code>src/main/resources/application.properties</code> </i>

## Config Email (Send Email Function)
This function use for: login, logout, payment, sendmail, ...
<br>
How to create send mail application see more: [Link to create email appplication](https://www.youtube.com/watch?v=ugIUObNHZdo)
<br>
<br>
<b>Config in <code>src/main/resources/application.properties</code></b>
<br>
spring.mail.username=<code>Your mail</code>
<br>
spring.mail.password=<code>Your password application</code>
<br>
<br>
<b>Config in <code>src/main/java/com/example/polyfood/Config/SendMailConfig.java</code></b>
<br>
mimeMessageHelper.setFrom("<code>Your email</code>");

## VNPay
Use this account for test VNPay:
<br>
Bank:
```NCB```
<br>
Card number:
```9704198526191432198```
<br>
Account name:
```NGUYEN VAN A```
<br>
Create Date:
```07/15```
<br>
OTP password:
```123456```
<br>
