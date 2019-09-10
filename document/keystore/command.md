- 生成JKS文件
```
keytool -genkeypair -alias mytest -keyalg RSA -keypass mypass -keystore mytest.jks -storepass mypass
```
- 导出公钥
```
keytool -list -rfc --keystore mytest.jks | openssl x509 -inform pem -pubkey
```

- 认证请求地址
```
localhost:8090/oauth/token?grant_type=password&username=xing&password=123456
Basic Auth:
    username: client
    password: secret
```
- 资源允许配置
```
http://127.0.0.1:8091/test
Headers: 
    authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ4aW5nIiwic2NvcGUiOlsiYXBwIl0sImV4cCI6MTU2ODE2OTM2NSwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiNzYzZGE5NTEtNWYzYy00MzFhLTg5ZTgtNmQ3YzZmYzJmYjViIiwiY2xpZW50X2lkIjoiY2xpZW50IiwidXNlcm5hbWUiOiJ4aW5nIn0.DN9pbXBEmjVuo7NnzmltrA6teFDxtuRE5Igqxcyr8lzYtuJJjcMeUSMpZS0bnNxs-Lo3tmKLDcvS5Mz_OwOCf1kz8fZGRdB77vN6HC-cvKe9kimuq6ZjU79oS8Uj_lk15UkGIXTtax5nSDbmM9IYckH5Acsms5nxFEdVLMGdTlk2y-2ICNRXBxwXFZtjVsEQjbqqpv5u9mraic1umLJn8OC7YZKRa4t_diKZRawobLYo0fBWbF2lvlv4TrZ19C95Bd-HLTueqK1_rFysFyNdOPvZXyMja6Wzx3FShJDPVhGjemTHVOm8S-wxk6G7NbdNNnkK2m1btPBnsFExfQ4fZw
相当于： authorization： Bearer token_access
```
