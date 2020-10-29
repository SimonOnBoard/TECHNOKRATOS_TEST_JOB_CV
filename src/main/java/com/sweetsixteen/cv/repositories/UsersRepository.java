package com.sweetsixteen.cv.repositories;

import com.sweetsixteen.cv.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User,String> {
}
