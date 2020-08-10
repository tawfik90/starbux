# Starbux stories

## Story 1
As admin user of the application, I would like to be able to Add/Update/Delete Drinks/Toppings.

### sub-tasks of story 1
Task 1: Create the business logic of drinks(add/update/delete). (Done)

Task 2: Store, update, delete drinks data in H2 database using Spring Data, by creating data layer(repository, data). (Done)

Task 3: Create the API end-point to access the service of drinks CURD operations. (Done)

Task 4: Create the business logic of toppings(add/update/delete).(Done)

Task 5: Store all data in H2 database using Spring Data.(Done)

Task 6: Creat the API end-point to access the service of toppings CRUD operation.(Done)


## Story 2
As user of the application, I would like to be able to see all available drinks and topping.

### sub-tasks of story 2
Task 1: Update the business logic to retrieve all available drinks.(Done)

Task 2: Create the API end-point to retrieve all available drinks.(Done)

Task 3: Update business logic to retrieve all available toppings.(Done)

Task 4: Create the API end-point to retrieve all available toppings.(Done)


## Story 3
As user of the application, I should be able to create an order contains any number of drinks with any of topping
combinations.

### sub-tasks of story 3
Task 1: Create the business logic of order and add drinks/toppings to the order.

Task 2: Expose a new REST end-point to add drinks/toppings to order.


## Story 4
As User of the application I would like to finalise my order and get my discount :)

