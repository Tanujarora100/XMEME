package com.crio.starter.repository;

import com.crio.starter.entity.Meme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface XMemeRepository extends MongoRepository<Meme, String> {
    boolean existsBynameAndURLAndCaption(String name, String URL, String caption);


   Page<Meme> findAll(Pageable pageable);
}
