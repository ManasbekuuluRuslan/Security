package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.UserResponse;
import peaksoft.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
    boolean existsByEmail(String email);
    @Query("select new peaksoft.dto.UserResponse" +
            " (u.id,u.firstName,u.lastName,u.email,u.role) from User u")
    List<UserResponse> getAllUsers();
    Optional <UserResponse> findUserById(Long id);
}