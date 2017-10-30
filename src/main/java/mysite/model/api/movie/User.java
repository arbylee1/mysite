package mysite.model.api.movie;

public class User {
    private String email;
    private String name;
    private String username;
    private String password;
    private String major;
    private String interests;
    private boolean isAdmin;
    private boolean isBanned;

    public User(String email, String name, String username, String password, String major,
                String interests, boolean isAdmin, boolean isBanned) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.major = major;
        this.interests = interests;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
    }
    public User(String email, String name, String major, String interests, boolean isAdmin, boolean isBanned) {
        this.email = email;
        this.name = name;
        this.username = "";
        this.password = "";
        this.major = major;
        this.interests = interests;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
    }
    public User(String username, boolean isAdmin, boolean isBanned) {
        this(null, null, username, null, null, null, isAdmin ,isBanned);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMajor() {
        return major;
    }

    public String getInterests() {
        return interests;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isBanned() {
        return isBanned;
    }
}
