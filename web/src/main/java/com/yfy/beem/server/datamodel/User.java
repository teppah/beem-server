package com.yfy.beem.server.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * The base user representation for the server module of the Beem chat client.
 * TODO: Write javadoc for server:com.yfy.beem.server.datamodel.User
 */
@Entity()
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String ipAddress;
    private String publicKey;

}
