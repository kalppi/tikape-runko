package tikape.keskustelufoorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import tikape.keskustelufoorumi.Auth;
import tikape.keskustelufoorumi.domain.User;

public class UserDao implements IDao<User, Integer>, IPageableDao<User> {
    private Database database;

    public UserDao(Database database) throws SQLException {
        this.database = database;
    }

    @Override
    public User findOne(Integer key) {   
        try {
            Connection c = this.database.getConnection();
            PreparedStatement s = StatementBuilder.findOne(c, "Users", key, Arrays.asList("*"));

            ResultSet rs = s.executeQuery();
            if(!rs.next()) {
                return null;
            }

            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String pwHash = rs.getString("pw_hash");
            Boolean admin = rs.getBoolean("admin");

            User o = new User(id, name, pwHash, admin);

            s.close();
            c.close();

            return o;
        } catch(SQLException e) {
            return null;
        }
    }
    
    @Override
    public User findOneBy(String key, Object value) {
        try {
            Connection c = this.database.getConnection();
            PreparedStatement s = StatementBuilder.findOneBy(c, "Users", key, value, Arrays.asList("*"));

            ResultSet rs = s.executeQuery();
            if(!rs.next()) {
                return null;
            }

            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String pwHash = rs.getString("pw_hash");
            Boolean admin = rs.getBoolean("admin");

            User o = new User(id, name, pwHash, admin);

            s.close();
            c.close();

            return o;
        } catch(SQLException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection c = this.database.getConnection();
            PreparedStatement s = StatementBuilder.findAll(c, "Users", Arrays.asList("*"));

            ResultSet rs = s.executeQuery();
            while(rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String pwHash = rs.getString("pw_hash");
                Boolean admin = rs.getBoolean("admin");

                users.add(new User(id, name, pwHash, admin));
            }

            s.close();
            c.close();

            return users;
        } catch(SQLException e) {
            return users;
        }
    }
    
    @Override
    public List<User> findAll(Integer start, Integer limit) {
        List<User> users = new ArrayList<>();
        
        try {
            Connection c = this.database.getConnection();
            PreparedStatement s = StatementBuilder.findAll(c, "Users", Arrays.asList("*"), start, limit);

            ResultSet rs = s.executeQuery();
            while(rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String pwHash = rs.getString("pw_hash");
                Boolean admin = rs.getBoolean("admin");

                users.add(new User(id, name, pwHash, admin));
            }

            s.close();
            c.close();

            return users;
        } catch(SQLException e) {
            return users;
        }
    }
    
    @Override
    public List<User> findAllIn(Collection<Integer> keys) throws SQLException {
        if(keys.isEmpty()) {
            return new ArrayList();
        }
        
        List<User> users = new ArrayList();
        
        Connection c = this.database.getConnection();
        PreparedStatement s = StatementBuilder.findAllIn(c, "Users", keys, Arrays.asList("*"));
        ResultSet rs = s.executeQuery();
        
        if(rs == null) {
            return new ArrayList();
        }
        
        while(rs.next()) {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String pwHash = rs.getString("pw_hash");
            Boolean admin = rs.getBoolean("admin");

            users.add(new User(id, name, pwHash, admin));
        }
        
        s.close();
        c.close();
        
        return users;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
    
    public void insert(String name, String pw, Boolean admin) throws Exception {
        String pwHash = Auth.hashPassword(pw);
        
        Connection c = this.database.getConnection();
        PreparedStatement s = StatementBuilder.insert(c, "Users", Arrays.asList("name", "pw_hash", "admin"));
        
        s.setObject(1, name);
        s.setObject(2, pwHash);
        s.setObject(3, admin);
        
        s.execute();
    }
}
