:wq# REVOLUTE

A small wallet web app: deposit, withdraw, send money between accounts, and see
transaction history. Java 17, Servlet + JSP, packaged as a `.war`. Data is kept
in memory, so it resets when the server restarts.

## Structure

```
revolute-app/
├── pom.xml
├── .gitignore
└── src/
    ├── main/
    │   ├── java/com/revolute/
    │   │   ├── Wallet.java              # balance, deposit, withdraw, transfer
    │   │   ├── Transaction.java         # one history entry
    │   │   ├── InsufficientFundsException.java
    │   │   ├── WalletService.java       # in-memory "bank" (demo accounts Rems, Ada)
    │   │   ├── WalletServlet.java       # GET/POST /wallet
    │   │   └── Html.java                # escapes text shown on the page
    │   └── webapp/
    │       ├── WEB-INF/web.xml
    │       └── index.jsp
    └── test/java/com/revolute/
        └── WalletTest.java
```

## Build and test

```bash
mvn clean package      # runs the unit tests, then builds target/revolute-app.war
mvn test               # tests only
```

## Run

Needs Tomcat 10+ (Jakarta namespace):

```bash
cp target/revolute-app.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/catalina.sh run
```

Open http://localhost:8080/revolute-app/wallet

## Push to GitHub

```bash
git init
git branch -M main
git add .
git commit -m "REVOLUTE first commit"
git remote add origin git@github.com:Cyfrin-rems/REVOLUTE_app.git
git pull origin main --allow-unrelated-histories --no-rebase   # only if the repo already has a README
git push -u origin main
```

