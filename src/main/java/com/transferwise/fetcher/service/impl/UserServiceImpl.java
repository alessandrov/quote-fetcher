package com.transferwise.fetcher.service.impl;

import com.transferwise.fetcher.entity.User;
import com.transferwise.fetcher.service.UserService;
import com.transferwise.fetcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserRepository repository;

    @Override
    public User find(Long id) {
        User result = null;

        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            result = user.get();
        }

        return result;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User update(User entity) {
        return repository.save(entity);
    }

}
