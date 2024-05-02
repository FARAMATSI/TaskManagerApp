package zw.co.afrosoft.Repositories.Security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.afrosoft.entities.Security.CustomUser;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser,String> {
}
