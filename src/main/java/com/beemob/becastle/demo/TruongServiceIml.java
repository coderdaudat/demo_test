package com.beemob.becastle.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * Created by truongnguyen on 10/23/18.
 */
@Service
public class TruongServiceIml implements TruongService {

    //key
    //chest-single
    //chests-userId
    //chests-userId-location
    //chest-single-location
    //chest-count

    private final TruongRepository repository;

    @Autowired
    TruongServiceIml(TruongRepository repository) {
        this.repository = repository;
    }

    @Override
    @Caching(
            put= { @CachePut(value= "chest-single", key= "#chest.id", unless="#result == null") },
            evict= {
                @CacheEvict(value= "chests-userId", allEntries= true),
                @CacheEvict(value= "chests-userId-location", allEntries= true),
                @CacheEvict(value= "chest-single-location", allEntries= true),
                @CacheEvict(value= "chest-count", allEntries= true)
            }
    )
    public Truong create(Truong chest) {
        return repository.save(chest);
    }

    @Override
    public Truong findOneById(Long id) {
        return repository.findOneById(id);
    }


}
