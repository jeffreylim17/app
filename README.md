mvn clean install spring-boot:run </br>
Create user </br>
/** id's are auto generated **/ </br>
{
"customerType": "STAFF",
"duration": 5,
"id": 0,
"name": "Jeff"
}

/** id's are auto generated **/ </br>
Create product </br>
{
"category": "ELECTRONICS",
"id": 0,
"name": "battery",
"price": 10
}

Add to Cart Endpoint Using userId and productId </br>
It will create a record on the cart </br>

Go to the cart controller and input the userId you used for adding to cart </br>
All the products should be there and the computed discount and net total