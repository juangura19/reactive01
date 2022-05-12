package com.juangutierrez.api;

import com.juangutierrez.document.Person;
import com.juangutierrez.service.PersonService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonApi {

    @Autowired
    PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flowable<Person> findAll(){
        return personService.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Single<Person> save(@RequestBody Person p){
        return personService.save(p);
    }

    @PutMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Single<Person> update(@RequestBody Person p){
        return personService.update(p);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Maybe<Person> findById(@PathVariable("id") String id){
        return personService.getId(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Completable delete(@PathVariable("id") String id){
        return personService.delete(id);
    }
}
