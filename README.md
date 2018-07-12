# Rock-Paper-Scissors console game

This is java based console game "Rock-Paper-Scissors".

## Requirements (if not using docker)
1. java v.8.*
2. maven v.3.*

### Simple game instructions:
1. Run the application
2. After invitation and rules had been printed you should choose your move. To make move - input 'R', 'P' or 'S' and press 'Enter'.
3. Program will print your and computer's moves and inform you about game result.
4. To continue playing - input any character(except 'Q' or 'q')
5. Finally, when you input 'Q', you would see game statistic such as Wins, Losses, Draws and Wins percentage. 

#### Build 
* ```cd ${PROJECT DIR}```
* ```mvn clean package```

#### Run the application:
* Build the project
* Run next commands

    ```cd ${PROJECT DIR}```
    
    ```java -jar target/*.jar```

#### How to run tests:
    ```mvn clean test```


#### How to run using docker
1. Clone project from repository to your local machine.
2. ```cd ${project dir}```
3. ```docker build -t rock-paper-scissors .```
4. ```docker run -i rock-paper-scissors```