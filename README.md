# WebTest

## Test Execution

After you have pulled the git repository, you can then run <b>mvn clean install -DskipTests</b>

Once the dependencies are installed you can then run the following commands to execute the test scripts:

1. Execute the following commands to run the docker containers for selenium images on to your local machine:
> docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:4.1.1-20211217

>docker run -d -p 4445:4444 --shm-size="2g" selenium/standalone-firefox:4.1.1-20211217

2. Once the docker containers are running you can then execute the test script via the following maven command:
> mvn clean verify -Dbrowser=\<browser>
  
  <browser> value can either be chrome or firefox. If this argument is not passed, the browser will default to chrome. 

## Test Execution Report

This framework has been built using serenity reports. After execution the reports can be viewed at the following location:
/target/site/serenity/index.html
