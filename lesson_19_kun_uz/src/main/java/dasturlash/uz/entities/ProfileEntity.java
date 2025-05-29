package dasturlash.uz.entities;

import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = " username", nullable = false)
    private String username;
    //email / phone
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "visible", nullable = false)
    private Boolean visible = true;

    @Column(name = "photo_id")
    private String photoId;

    @Enumerated(EnumType.STRING)
    private ProfileStatus status = ProfileStatus.ACTIVE;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "profile_roles", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "role")
    private List<ProfileRole> roleList;
    private LocalDateTime createdDate;

}
