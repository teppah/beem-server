package com.yfy.beem.server.respository;

import com.yfy.beem.commons.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{
}
