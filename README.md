# Dead Queue Pattern
* Dead queue pattern - once message send to First queue(Outgoing Queue) with time to live, after time expire it's move message to second queue using defined Exhange and Routing Key and server listen to Second queue(Incoming Queue)

![Video](dead_queue.gif)
