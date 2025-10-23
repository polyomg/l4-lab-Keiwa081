package poly.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.edu.dao.AccountDAO;
import poly.edu.entity.Account;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO dao;

    @Override
    public Account findById(String username) {
        return dao.findById(username).orElse(null);
    }

    @Override
    public List<Account> findAll() {
        return dao.findAll();
    }

    @Override
    public Account findByUsernameAndPassword(String username, String password) {
        return dao.findByUsernameAndPassword(username, password);
    }
}
