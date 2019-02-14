#PIZZAN API

PIZZAN API is a mini-project realized, in pairs by [Alexis](https://github.com/ixelaz) and [Theophile](https://github.com/Gudsfile), as part of the SOAP Service Oriented Architecture and Service Exposure course.

##Context
Realize a service-oriented architecture in the form of extended web services (in SOAP) in a pizzeria.  
This pizzaria has three services:

* [**UserService**](#UserService)  
User web service  
This service is used to add customer users or admin user to the app.  
Users can login using their name and password.  
Loging in return a unique string token that is needed to execute most of the api requests.  
Admin can add new admins, delete users and get the list of all users.  Access point : [http://localhost:8090/userservice](http://localhost:8090/userservice)  
Docker access point : [http://localhost:8090/ws/userservice](http://localhost:8090/ws/userservice)

* [**OrderService**](#OrderService)  
Order web service  
This service is used to add orders and pizzas.  
As an admin you can add new pizza and delete them, get all customer orders.  
As a customer you can order a pizza, get your order list.  
Access point : [http://localhost:8090/orderservice](http://localhost:8090/orderservice)  
Docker access point : [http://localhost:8090/ws/orderservice](http://localhost:8090/ws/orderservice)

* [**PaymentService**](#PaymentService)    
Payment web service  
This service is used to pay orders.  
As a customer you can get the total debt you have and pay your orders.  
Access point : [http://localhost:8090/paymentservice](http://localhost:8090/paymentservice)  
Docker access point : [http://localhost:8090/ws/paymentservice](http://localhost:8090/ws/paymentservice)

##Usage

###Requirements
* Java [install OpenJDK 11](https://jdk.java.net/11/)
* Maven [install Maven](https://maven.apache.org)

###Deployment

* Local

		java PizzanWebServicePublisher
		
* Docker

		sudo docker run --rm --name PizzanService-tomcat -v ws.war:/usr/local/tomcat/webapps/ws.war -it -p 8080:8080 tomcat:9.0.12-jre10-slim

##Documentation API

###UserService

####addUser
Add a customer

	boolean addUser(String name, String pass);

######Parameters
Name | Description
---- | -------------
name | **String** \| *customer's name*
pass | **String** \| *customer's password*

######Responses
Type     | Description
-------- | -------------
Boolean  | true if the customer has been added

--

####addAdmin
Allows an admin to add another admin

	boolean addAdmin(String token, String name, String pass);

######Parameters
Name  | Description
----- | -------------
token | **String** \| *admin authentification token*
name  | **String** \| *customer's name*
pass  | **String** \| *customer's password*

######Responses
Type    | Description
------- | -------------
String  | pass admin's password

--

####deleteUser
Allows an admin to delete a user using this user's id

	boolean deleteUser(String token, int id);

######Parameters
Name  | Description
----- | -------------
token | **String** \| *admin authentification token token*
id    | **Integer** \| *user's id to be deleted*

######Responses
Type     | Description
-------- | -------------
Boolean  | true if the user has been deleted

--

####login
Login a user and return a unique authentification token that is to be used for every request on the api

	String login(String login, String pass);

######Parameters
Name  | Description
----- | -------------
login | **String** \| *user login*
pass  | **String** \| *user password*

######Responses
Type   | Description
------ | -------------
String | a unique 64 char random string;

--

####logout
Logout a user and remove his token from the list of current users token

	boolean logout(String token);

######Parameters
Name  | Description
----- | -------------
token | **String** \| *user token*

######Responses
Type     | Description
-------- | -------------
Boolean  | true if the user has been loged out

--

####getUsers
Allows an admin to get the list of users

	List<User> getUsers(String token);

######Parameters
Name  | Description
----- | -------------
token | **String** \| *admin authentification token*

######Responses
Type        | Description
----------- | -------------
List<User>  | the list of users

--

####getUser
Allows an admin to get a user information

	User getUser(String token, int id);

######Parameters
Name  | Description
----- | -------------
token | **String** \| *admin authentification token*
id    | **Integer** \| *user id*

######Responses
Type  | Description
----- | -------------
User  | a user

---

###OrderService

####addPizzas
Allows an admin to add a pizza to the list of available pizzas

	boolean addPizza(String token, String name, double price);

######Parameters
Name  | Description
----- | -------------
token | **String** \| *admin authentification token*
name  | **String** \| *name of the new pizza*
price | **Integer** \| *price of the new pizza*

######Responses
Type    | Description
------- | -------------
Boolean | true if the new pizza has been added

--

####deletePizza
Allows an admin to delete a pizza from the list of available pizzas

	boolean deletePizza(String token, int id);
	
######Parameters
Name  | Description
----- | -------------
token | **String** \| *admin authentification token*
id    | **Integer** \| *id of the pizza to delete*

######Responses
Type    | Description
------- | -------------
Boolean | true if the pizza has been deleted

--

####getPizzas
Get the list of available pizzas

	List<Pizza> getPizzas(String token);
		
######Parameters
Name  | Description
----- | -------------
token | **String** \| *user authentification token*

######Responses
Type        | Description
----------- | -------------
List<Pizza> | the list of pizzas

--

####getPizza
Get a pizza information

	Pizza getPizza(String token, int id);
		
######Parameters
Name  | Description
----- | -------------
token | **String** \| *user authentification token*
id    | **Integer** \| *the pizza's id*

######Responses
Type  | Description
----- | -------------
Pizza | a pizza

--

####addOrder
Allows a customer to order a pizza

	boolean addOrder(String token, int pizzaId);
		
######Parameters
Name    | Description
------- | -------------
token   | **String** \| *customer authentification token*
pizzaId | **Integer** \| *the pizza's to order id*

######Responses
Type    | Description
------- | -------------
boolean | true if the pizza has been ordered

--

####getUnpaidOrders
Allows a customer to get current orders (i.e. orders which are not paid yet)

	List<Order> getUnpaidOrders(String token);
		
######Parameters
Name  | Description
----- | -------------
token | **String** \| *user authentification token*

######Responses
Type        | Description
----------- | -------------
List<Order> | current orders

--

####getUserOrders
Allows a customer to get all his orders

	List<Order> getUserOrders(String token);
		
######Parameters
Name  | Description
----- | -------------
token | **String** \| *user authentification token*

######Responses
Type        | Description
----------- | -------------
List<Order> | user orders

--

####getOrders
Allows an admin to get the list of all orders

	List<Order> getOrders(String token);
		
######Parameters
Name  | Description
----- | -------------
token | **String** \| *admin authentification token*

######Responses
Type        | Description
----------- | -------------
List<Order> | the list of all orders

--

####getOrder
Allows an admin to get an order informations

	Order getOrder(String token, int id);
		
######Parameters
Name  | Description
----- | -------------
token | **String** \| *admin authentification token*
id    | **Integer** \| *order id*

######Responses
Type  | Description
----- | -------------
Order | an order

---

###PaymentService

####getTotalDebt
Allows a user to get the total price he needs to pay

	double getTotalDebt(String token);
		
######Parameters
Name  | Description
----- | -------------
token | **String** \| *user authentification token*

######Responses
Type   | Description
------ | -------------
double | the total price

--

####getTotalDebt
Allows a user to pay the total price and set his orders to paid

	double getTotalDebt(String token);
		
######Parameters
Name  | Description
----- | -------------
token | **String** \| *user authentification token*

######Responses
Type    | Description
------- | -------------
boolean | true if the user has paid

--


