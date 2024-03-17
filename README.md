# Template 
microservices, event-driven Ecommerce

## Guidelines:
* Don't work on the main/master branch. Create you own branches :warning:
  * Format of branch name: <ITEM-ID>_<DEV-NAME>_<ITEM NAME>
  * Since branch protection can not be applied in private repo:
    * ALWAYS create a PR (PULL-REQUEST) to the main branch
    * Which means, NEVER push in main
    * Merge atleast once a week in main before the Sprint Review (Tuesday)
    * Keep the commit comments (GIT) or Summary (Github Desktop) clean and understandable.
* Document your code as much as possible :white_check_mark:
* keep an eye on the readme, other guideliens could be written later :grin:

# parent POM dependencies
if you feel there is a need to a system-wide dependency.
Please let me know (abdullah), and I'll add to the parent POM dependencies. we are enforcing that to keep things clean organized
which will help us also in deployment

# services
 *for each service you gonna needs at least two container, one for the API itself and one for the database. 
 *let's agree on using MySQL database for consistency

# playaround
after finishing from developing your service, you are encouraged to
fork the repo and create other services and try to use get familiar with apache kafka.

# message broker
we probably gonna switch from rabbitMQ to Apache Kafka, given
the abundance of tutorils and resources to accelerate implementation.
Use the cloud stream apache kafka binder

# data consistency (distriubted transactions)
Also, read and think about how we going to implement the saga architectural pattern to enable distributed transactions
example: createOrder request requires decreasing inventory
first then create the order itself. if a substage fails the transaction as a whole should fail.

# Database schema
```sql
create table category
(
    categoryId  bigint auto_increment
        primary key,
    name        varchar(255) null,
    description text         null
);

create table product
(
    productId   bigint auto_increment
        primary key,
    name        varchar(255)   null,
    price       decimal(10, 2) null,
    description text           null,
    stockNumber int            null,
    categoryId  bigint         null,
    constraint product_ibfk_1
        foreign key (categoryId) references category (categoryId)
);

create index categoryId
    on product (categoryId);

create table user
(
    userID    bigint auto_increment
        primary key,
    role      varchar(255) null,
    firstName varchar(255) null,
    lastName  varchar(255) null,
    phone     varchar(255) null,
    password  varchar(255) null,
    DOB       date         null
);

create table billingAddress
(
    billingAddressId bigint auto_increment
        primary key,
    fullName         varchar(255) null,
    phone            varchar(255) null,
    streetName       varchar(255) null,
    houseNumber      varchar(255) null,
    city             varchar(255) null,
    country          varchar(255) null,
    userId           bigint       null,
    constraint billingaddress_ibfk_1
        foreign key (userId) references user (userID)
);

create index userId
    on billingAddress (userId);

create table shippingAddress
(
    shippingAddressId bigint auto_increment
        primary key,
    fullName          varchar(255) null,
    phone             varchar(255) null,
    streetName        varchar(255) null,
    houseNumber       varchar(255) null,
    city              varchar(255) null,
    country           varchar(255) null,
    userId            bigint       null,
    constraint shippingaddress_ibfk_1
        foreign key (userId) references user (userID)
);

create table `order`
(
    orderId           bigint auto_increment
        primary key,
    status            varchar(255)   null,
    date              date           null,
    price             decimal(10, 2) null,
    userId            bigint         null,
    shippingAddressId bigint         null,
    billingAddressId  bigint         null,
    constraint order_ibfk_1
        foreign key (userId) references user (userID),
    constraint order_ibfk_2
        foreign key (shippingAddressId) references shippingAddress (shippingAddressId),
    constraint order_ibfk_3
        foreign key (billingAddressId) references billingAddress (billingAddressId)
);

create index billingAddressId
    on `order` (billingAddressId);

create index shippingAddressId
    on `order` (shippingAddressId);

create index userId
    on `order` (userId);

create table orderItem
(
    orderItemId bigint auto_increment
        primary key,
    quantity    int            null,
    price       decimal(10, 2) null,
    orderId     bigint         null,
    productId   bigint         null,
    constraint orderitem_ibfk_1
        foreign key (orderId) references `order` (orderId),
    constraint orderitem_ibfk_2
        foreign key (productId) references product (productId)
);

create index orderId
    on orderItem (orderId);

create index productId
    on orderItem (productId);

create index userId
    on shippingAddress (userId);




