package com.juangutierrez.service.impl;

import com.juangutierrez.document.Person;
import com.juangutierrez.repository.PersonRepository;
import com.juangutierrez.service.PersonService;
import io.reactivex.rxjava3.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public Single<Person> save(Person person) {
        return repository.save(person);
    }

    @Override
    public Single<Person> update(Person person) {
        return repository.findById(person.getId())
            .switchIfEmpty(Single.error(RuntimeException::new))
            .flatMap(v -> repository.save(person));
    }

    @Override
    public Flowable<Person> findAll() {
        return repository.findAll()
            .doOnError(throwable -> System.out.println(String.format("Error %s", throwable.getMessage())))
            .filter(person -> person.getStatus() == 1);
    }

    @Override
    public Maybe<Person> getId(String id) {
        return repository.findById(id)
            .switchIfEmpty(Maybe.error(RuntimeException::new));
    }

    @Override
    public Completable delete(String id) {
        return repository.findById(id)
            .switchIfEmpty(Single.error(RuntimeException::new))
            .flatMapCompletable(x -> repository.deleteById(id));
    }
}
