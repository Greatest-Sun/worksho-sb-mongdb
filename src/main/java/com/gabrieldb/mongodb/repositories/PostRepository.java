package com.gabrieldb.mongodb.repositories;

import com.gabrieldb.mongodb.domain.Post;
import com.gabrieldb.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    public List<Post> findByTitleContainingIgnoreCase(String text);
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    public List<Post> findByTitle(String text);
    @Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    public List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
