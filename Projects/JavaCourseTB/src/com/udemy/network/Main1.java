package com.udemy.network;

public class Main1 {
    public static void main(String[] args) {

    }
}
/*Networking Overview:
*
* Network - A system of computer connected together so they can share resources and communicate with each other
* Networking - refers to how the connected computer communicate
*
* java.net package contains classes we'll ust to establish connections between computers and then send messages between them.
* When writing networking code, we'll need familiarity with threads and IO streams.
*
* Computers can communicate across a private network called intranet. Office workers don't usually have a printer at
* their desk they share a printer. When they print a file it's sent to the printer over the business' intranet.
*
* You can also use networking when you want applications running on the same machine to communicate with each other.
* So networking can be involved even when only one machine is involved, usually referred to as a Host.
* Common configuration is client/server.
*
* Computers on a network, and internet, communicate with each other using a transport protocol TCP and UDP
*
* When data arrives at the one physical connection to the network, how does it get routed to the target application?
* When running multiple applications on your computer ie. web browsing, listening to music, streaming video, etc.
* That's where pots come in. Each application that needs data from the network is assigned a port.
* When data arrives, the port number is used to route the data to the application that's waiting for it.
*
* IPv4 or PIv6 (Internet Version Protocols #)
* IPv4 32-bit -> 4 billion unique address for devices. All need it's own unique IP address so 4billion wasn't enough
* - was 4 ints separated by dots
* IPv6 128-bit address scheme which allows for many more IP addresses.
* - hexadecimal and separated by colons
*
* TCP/IP - refers to using TCP protocol with IP addresses, which doesn't necessarily mean the host is
* connected to the internet. 2 applications running on the same host can use TCP/IP to communicate with each other.
* When the client and server are on the same host, usually IP address 127.0.0.1 which is referred to as
* localhost is used to identify the host.
* */
