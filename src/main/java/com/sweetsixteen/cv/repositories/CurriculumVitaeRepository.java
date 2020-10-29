package com.sweetsixteen.cv.repositories;

import com.sweetsixteen.cv.models.CurriculumVitae;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CurriculumVitaeRepository extends MongoRepository<CurriculumVitae, String> {
    @Query("{ competences: { $size: ?0 , $in: [?1]} }")
    List<CurriculumVitae> findAllWithCompetencesSizeGreaterThen(int count, List<String> competences);


    @Query("{'title': {$regex: ?0, $options: 'i'}," +
            " 'about': {$regex: ?1, $options: 'i'}, " +
            "'location': {$regex: ?2, $options: 'i'}}")
    List<CurriculumVitae> findAllByTitleAndAboutAndLocationAllIgnoreCase(String title, String about, String location);
}
