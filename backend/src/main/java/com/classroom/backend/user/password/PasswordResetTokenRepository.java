package com.classroom.backend.user.password;

import com.classroom.backend.token.Token;
import com.classroom.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    @Query("""
            select t from PasswordResetToken t inner join User u
            on t.user.id = u.id
            where u.id = :id and (t.expired = false or t.revoked = false)
            """)
    List<Token> findAllValidTokenByUser(Long id);

    Optional<PasswordResetToken> findByUser(User user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);

    Optional<PasswordResetToken> findByToken(String token);
}
