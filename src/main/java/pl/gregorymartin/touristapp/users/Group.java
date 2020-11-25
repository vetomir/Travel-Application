package pl.gregorymartin.touristapp.users;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int size;

    @ManyToOne
    private User user;


    public Group() {

    }

    long getId() {
        return id;
    }

    void setId(final long id) {
        this.id = id;
    }

    int getSize() {
        return size;
    }

    void setSize(final int size) {
        this.size = size;
    }

    User getUser() {
        return user;
    }

    void setUser(final User user) {
        this.user = user;
    }
}
