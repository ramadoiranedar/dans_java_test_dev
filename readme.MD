# dans_java_test_dev

### Setup
- create database MySQL with name `java_test_dev`
- import sql
  - `cd root project`
  - `mysql -u YOUR_USERNAME_DB -p java_test_dev < ./dump-java_test_dev-202301261923.sql`

- export bash
```
export DB_URL=jdbc:mysql://localhost:3306/java_test_dev
export DB_USERNAME=root
export DB_PASSWORD=root
export JWT_SECRET=s3cr3tLoremIpsumisdummys3cr3tLoremIpsumisdummys3cr3tLoremIpsumisdummys3cr3tLoremIpsumisdummys3cr3tLoremIpsumisdummy
export DANS_API_BASEURL=http://dev3.dansmultipro.co.id/api
```

- Run

### Postman Collections
- https://api.postman.com/collections/7368471-15aad484-aa07-4313-85c7-fc4f73f73f0a?access_key=PMAT-01GQQ43H782R3CNAA0FNFN9GVQ