package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poly.edu.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, String> {

    // Tìm theo username
    @Query("SELECT a FROM Account a WHERE a.username = ?1")
    Account findByUsername(String username);

    // Kiểm tra đăng nhập
    @Query("SELECT a FROM Account a WHERE a.username = ?1 AND a.password = ?2")
    Account findByUsernameAndPassword(String username, String password);
}
