package dasturlash.uz.services;

import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.entities.ProfileEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileDTO profile) {
        Optional<ProfileEntity> existingProfile = profileRepository.findByUsername(profile.getUsername());
        if (existingProfile.isPresent()) {
            throw new AppBadException("Username already exists");
        }


     /*   for (ProfileRole role : profile.getRoleList()) {
            if (role != ProfileRole.ROLE_MODERATOR && role != ProfileRole.ROLE_PUBLISHER) {
                throw new AppBadException("Only MODERATOR or PUBLISHER roles are allowed");
            }
        }
*/
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profile.getName());
        entity.setSurname(profile.getSurname());
        entity.setUsername(profile.getUsername());
        entity.setPassword(String.valueOf(profile.getPassword())); // Parolni ochiq saqlash (xavfsiz emas)
        entity.setVisible(true);
        entity.setStatus(ProfileStatus.ACTIVE);

        profileRepository.save(entity);

        // DTO'ni qaytaramiz
        profile.setId(entity.getId());
        return profile;
    }

    public ProfileDTO update(Integer id, ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("Profile not found");
        }

        ProfileEntity entity = optional.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUsername(dto.getUsername());
       /* entity.setPassword(String.valueOf(dto.getPassword())); */// Parolni ochiq ko‘rinishda o‘zgartiradi


        profileRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public Boolean delete(Integer id) {
        if (!profileRepository.existsById(id)) {
            throw new AppBadException("Profile not found");
        }
        profileRepository.deleteById(id);
        return true;
    }

    public List<ProfileDTO> getAll() {
        List<ProfileEntity> entities = (List<ProfileEntity>) profileRepository.findAll();
        return entities.stream().map(entity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setUsername(entity.getUsername());
            return dto;
        }).toList();
    }
}
