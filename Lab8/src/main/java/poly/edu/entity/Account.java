package poly.edu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "Accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    private String username;

    private String password;
    private String fullname;
    private String email;
    private Boolean admin; // Cột admin từ database

    // Phương thức kiểm tra admin
    public boolean isAdmin() {
        return Boolean.TRUE.equals(admin);
    }
}
