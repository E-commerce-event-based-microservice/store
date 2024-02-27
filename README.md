# Template 
microservices, event-driven Ecommerce

## Guidelines:
* Don't work on the main/master branch. Create you own branches :warning:
  * Format of branch name: <ITEM-ID>_<DEV-NAME>_<ITEM NAME>
  * Since branch protection can not be applied in private repo:
    * ALWAYS create a PR (PULL-REQUEST) to the main branch
    * Which means, NEVER push in main
    * Merge atleast once a week in main before the Sprint Review (Tuesday)
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



