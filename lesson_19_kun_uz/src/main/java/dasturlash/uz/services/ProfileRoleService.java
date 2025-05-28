package dasturlash.uz.services;

import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.repositories.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProfileRoleService {
    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    public ProfileRoleService(ProfileRoleRepository profileRoleRepository) {
        this.profileRoleRepository = profileRoleRepository;
    }


    public void create(Integer profileId, List<ProfileRole> rolesList) {
        for (ProfileRole role : rolesList) {

        }

    }

}
