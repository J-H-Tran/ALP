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
* ------------------------------------------------------------------------------------------------------------------
* java.net package
* has 2 APIs: Low-level and High-level
* When working with low-level API you'll have to be more aware of networking concepts
*
* common example is client/server and we'll often want a reliable 2-way communication link between the client and server
* This is where TCP protocol comes in.
* TCP (Transmission Control Protocol) - establishes a 2-way connection between hosts and this connection is reliable
* in the sense that the 2 hosts talk to each other. When used with Internet addresses you get TCP/IP, which
* uses the client/server model.
*
* Communication Flow of TCP/IP:
*
* 1. Client opens connection to the server
* 2. Client sends request to the server
* 3. Server sends response to the client
* 4. Client closes connection to the server
*
* 2 & 3 may be repeated multiple times before the connection is closed.
*
* Socket - A single end-point of the 2-way connection
* When using the low-level networking API, we'll be using sockets to establish connections, send requests, and receive responses
*
* When we have multiple clients connecting to the same server they'll use the same port number, but each client will have its own socket.
* Socket class for Client and ServerSocket class for the server.
*
* What's great about Java is that al lwe have to do is provide the IP address and port number when creating the socket.
* We don't have to understand how TCP/IP works. Underneath the covers, specific messages have to be sent to
* establish a connection between the client and th server (process is called handshaking) and the data ha to
* be sent as packets, which must be in a specific format.
*
* We'll write 2 applications to demonstrate how to do network coding. The server and the client.
* */
