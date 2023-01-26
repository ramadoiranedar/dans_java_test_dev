# dans_java_test_dev

### Setup
- create database MySQL with name `java_test_dev`
- import sql
`mysql -u root -p java_test_dev < ~/Downloads/dump-java_test_dev-202301261923.sql`

- export bash
```
export DB_URL=jdbc:mysql://localhost:3306/java_test_dev
export DB_USERNAME=root
export DB_PASSWORD=root
export JWT_SECRET=s3cr3tLoremIpsumisdummys3cr3tLoremIpsumisdummys3cr3tLoremIpsumisdummys3cr3tLoremIpsumisdummys3cr3tLoremIpsumisdummy
export DANS_API_BASEURL=http://dev3.dansmultipro.co.id/api
```

- Run