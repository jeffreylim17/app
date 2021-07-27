<h1>Ecommerce App</h1>

<h3><b>Create user</b></h3>
<b>Parameter Definition </b>

* <b>Customer type values</b> - type of customer that specifies the discount to be applied </b>
    * AFFILIATE
    * STAFF

* <b>Duration</b> - duration in years
* <b>Id</b> - User Identifier
* <b>name</b> - User name. Auto generated

<i>User Creation Sample payload: </i>

```
{
"customerType": "STAFF",
"duration": 5,
"id": 0,
"name": "Jeff"
}
`````

<h3><b>Create product</b></h3>
<b>Parameter Definition </b>
* <b>Category</b> - defines what type or what is the category of the product
  * ELECTRONICS
  * GROCERY
  
* <b>Id</b> - product identifier
* <b>Name</b> - product name
* <b>Price</b> - product price

```
{
"category": "ELECTRONICS",
"id": 0,
"name": "battery",
"price": 10 
}
```

<h1>How to Use </h1>

1. Run <i>mvn clean install spring-boot:run</i> </br>
2. Go to http://localhost:8000/swagger-ui.html </br>
<i>Note: you can trigger the endpoints manually or for better testing utilize swagger UI</i>
   
3. Go to User Controller and Create a User <i><b> POST /user </b></i>, then take note of the id of the created user
4. Go to Product Controller and Create a Product <i><b> POST /product </b></i>, then take note of the id of the created product
5. Go to Cart Controller
6. Create an order or Add product to cart using this endpoint <i><b> POST /cart/{userId}/{productId} </b></i> </br>
use the id's from the created user and product.
   
7. If you wish to see the summary of orders, discount and net total amount </br>
   Kindly trigger this endpoint and provide the userId <i><b> GET /cart/{userId} </b></i>
   