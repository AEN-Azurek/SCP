# SCP


<h3>Create Postgres DB</h3>
- Server name 'calc_plus'
- Create Login role 'scp' with password 'Welcome1!'
- Create DB with name 'scp' with user 'scp' as owner
- Create Schema with name 'dbo' and owned by 'scp'

<h3>Set Up Run Configs</h3>
- Go to Run/Debug Configurations in your IDE
- Create a new One under "Application"
- Name it something you'll remember.
- Set SDK to Java 17 and select `com.scp.CalculatorPlus.CalculatorPlusApplication`

This configuration is used to run the application. When running, Flyway is launched and a check is done on the DB. If the DB is not up-to-date, then Flyway will run any migration SQL file that has not been run on the DB yet according to the table flyway_schema_history.

- Create another configuration under "JUnit"
- Name it "All Tests"
- Configure it to be a package/directory rather than just a file
- Select `src/test/java/com/scp/CalculatorPlus`

This configuration can be used to run all tests in all test classes. Run this whenever making changes, and ensure all tests pass before pushing any changes to branches. Any failing tests on the main branches will be treated with high priority.
