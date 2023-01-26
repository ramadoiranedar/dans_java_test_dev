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
- make sure you have internet connection
- ru

### Postman Collections
- https://api.postman.com/collections/7368471-15aad484-aa07-4313-85c7-fc4f73f73f0a?access_key=PMAT-01GQQ43H782R3CNAA0FNFN9GVQ

### Screenshot

- register
![Screen Shot 2023-01-26 at 8 29 42 PM](https://user-images.githubusercontent.com/58647636/214848074-9231f2aa-1802-42d4-8f15-ef5ede0eb1ed.png)

- login
![Screen Shot 2023-01-26 at 8 29 51 PM](https://user-images.githubusercontent.com/58647636/214848084-bbd429db-dfa6-4abc-bf7a-82e917a360cf.png)

- list job
![Screen Shot 2023-01-26 at 8 30 02 PM](https://user-images.githubusercontent.com/58647636/214848096-3fe3cd37-4ae1-4133-af30-1071c959e2d6.png)

- detail job
![Screen Shot 2023-01-26 at 8 30 09 PM](https://user-images.githubusercontent.com/58647636/214848105-ae10bd32-c705-4899-9a07-a5049cd106c7.png)
