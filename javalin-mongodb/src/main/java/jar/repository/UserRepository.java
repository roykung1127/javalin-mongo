package jar.repository;

import java.util.List;
import jar.entity.User;

public interface UserRepository {
    public List<User> findAll();
    public void create(User user);
}


