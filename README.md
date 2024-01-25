# RMI mini chat
---
## Desciption
Implementation of a simple mini-chat web application using Java RMI. The distributed application consists of two parts:
- server
- client
---
## Functionality
### Server:
1. registers clients under selected names
2. receives text messages from each of them and displays them on the server screen
3. receive requests to read data collected by the server:
    - number and list of registered customers
    - number and list of registered messages
    - a subset of messages meeting the selected criterion (sender, date), with information regarding
sender and date
### Client:
1. sends a message to register with the server under a certain name
2. sends a message to unregister
3. sends a utility message (message)
4. sends a request to share data, in accordance with point 3
---
