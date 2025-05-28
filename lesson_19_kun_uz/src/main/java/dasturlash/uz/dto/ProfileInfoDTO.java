package dasturlash.uz.dto;

import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileInfoDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;

    private List<ProfileRole> roleList;
}
