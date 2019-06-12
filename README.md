# security




```
###
POST http://localhost:8080/oauth/token
Authorization: Basic client pass
Content-Type: application/x-www-form-urlencoded

username=cocobee&password=secret&grant_type=password
```


```
GET http://localhost:8080/plain/index?str=cocobee
Authorization: Bearer 1f21f9c2-7e18-42c5-851e-80f166d84658
```