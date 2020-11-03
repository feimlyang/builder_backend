# myStore_backend - SpringBoot

### S1:

#### UC1 - Guest user view product list

command: getProductList()


post: 
- return product list with details 

---

#### UC2 - Guest user place order

command: processOrder(Order)

pre: 
- guest user has paid and paypal payment token received
- guest user provides valid email address 

post: 
- update inventory of each product if inventory is not less than required quantity
- save order in db with details with PROCESSING status
- send receipt to guest via provided email.

---

### S2: 

#### UC3 - Merchant Login

command: login(userId, password)

pre: 
- merchant account has been created by admin. 

post: 
- UC4, UC5, UC6, UC7

---

#### UC4 - Merchant product builder

pre: 
- merchant user logged in
- order details are valid

post:
- sku generated for new product
- save product in db

---

#### UC5 - Merchant order view

command: getIncompleteOrders()

pre: 
- merchant user logged in.

post: 
- return all orders with not completed order with status code

---

#### UC6 - Merchant complete order

command: setOrderStatus(orderId, statusCode)

pre: 
- merchant user logged in. 

post: 
- update order status

---

### S3:

#### UC7 - Merchant sales analysis dashboard

养生模块

---



