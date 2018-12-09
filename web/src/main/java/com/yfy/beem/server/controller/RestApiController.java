package com.yfy.beem.server.controller;

import com.yfy.beem.server.datamodel.User;
import com.yfy.beem.server.respository.UserRepository;
import com.yfy.beem.server.util.RequestMappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@RestController
@Slf4j
public class RestApiController {
    private UserRepository userRepository;

    @Autowired
    public RestApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @PostMapping(RequestMappings.POST_TEST)
    public String registerUser (@RequestParam long id,
                                @RequestParam String name,
                                @RequestParam String publicKey,
                                HttpServletRequest request) {
        // remove all superfluous spaces, beginning tag and end tag of RSA public key
        publicKey = publicKey.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll(" ","");
        log.info("publicKey = {}", publicKey);

        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64
                    .getDecoder()
                    .decode(publicKey.getBytes(Charset.defaultCharset())));
            RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
            User user = new User(id,name, InetAddress.getByName(request.getRemoteAddr()), pubKey);
            userRepository.save(user);
            log.info("Successfully saved new user: {}", user);
            return user.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("exception related to KeyFactory: {}", e.getMessage());
            return "ERROR";
        } catch (InvalidKeySpecException e) {
            log.error("exception related to public key generation: {}", e.getMessage());
            return "ERROR";
        } catch (IOException e) {
            log.error("IOException: {}", e.getMessage());
            return "ERROR";
        }

    }
}
