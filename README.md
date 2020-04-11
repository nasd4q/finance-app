## SO FAR

#### We have :

##### in portfolioWatcher/src/main/java - com.nasd4q.portfolioWatcher

* service.Cac40Lister class able to return a List<Stock> given a LocalDate 
* model.Stock which modelizes a stock (action), based on the info 
provided by the website www.bnains.org

##### in portfolioWatcher/src/test/java - com.nasd4q.portfolioWatcher
* service.Cac40ListerTest class that tests corresponding class

##### besides this, in financeApp/
* git-ignored "data from www-bnains-org" folder containing 1993-2016 historical daily
quotes (open high low close values) for (all of?) euronext stocks in text files

##### other
* PortfolioWatcherApplication and PortfolioWatcherApplicationTests class
as generated by Spring initializr. Both of them doing nothing.
* An empty application.properties file

* pom.xml containing (besides Spring initializr default dependencies -
spring-boot-starter and spring-boot-starter-test) : Jsoup in order to 
parse the data returned by www.bnains.org (in Cac40Lister)

#### Questions : 
* Why use LocalDate rather than java.util.Date ?
* Why start off from a Spring initializr generated archetype ?

#### Current Goals :
* set up a postgres database in order to store stocks, as well as stock membership 
in the cac 40 index at a given date
* populate the db with historical quotes for any stock appearing in cac 40 since 1993, 
using the data in "data from bnains" folder