package com.sweetsixteen.cv.repositories;

import com.sweetsixteen.cv.models.CurriculumVitae;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurriculumVitaeRepository extends MongoRepository<CurriculumVitae, String> {

}
