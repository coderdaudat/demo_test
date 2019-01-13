package com.beemob.becastle.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by truongnguyen on 12/4/17.
 */
@Repository
public interface TruongRepository extends CrudRepository<Truong, Long> {

    Truong save(Truong saved);

    Truong findOneById(Long id);

}
