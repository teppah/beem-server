package com.yfy.beem.server.respository;

import com.yfy.beem.server.datamodel.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{
}
