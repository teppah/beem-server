package com.yfy.beem.server.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.InetAddress;
import java.security.PublicKey;

/**
 * The base user representation for the server module of the Beem chat client.
 * TODO: Write javadoc for server:com.yfy.beem.server.datamodel.User
 * */
@Entity(name ="user")
@Data
@AllArgsConstructor
@ToString
public class    User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;
    private String name;
    private final InetAddress ipAddress;
    private final PublicKey publicKey;

}
